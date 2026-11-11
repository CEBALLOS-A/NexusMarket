package com.nexusmarket.infrastructure.adapters.ui;

import com.nexusmarket.domain.model.EmployeeType;
import com.nexusmarket.domain.model.ProductStatus;
import com.nexusmarket.domain.model.UserStatus;
import com.nexusmarket.domain.model.Buyer;
import com.nexusmarket.domain.model.Product;
import com.nexusmarket.domain.model.User;
import com.nexusmarket.domain.service.AuthService;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

public class MainFrame extends JFrame {

    private static final String[] PRODUCT_COLUMNS = {"ID", "Name", "Type", "Status"};
    private static final String[] USER_COLUMNS = {"ID", "Full name", "Email", "Role", "Status"};

    private final AppContext ctx;
    private final User user;
    private final boolean canManageProducts;
    private final boolean isAdministrator;
    private final DefaultTableModel productModel = readOnlyModel(PRODUCT_COLUMNS);
    private final DefaultTableModel userModel = readOnlyModel(USER_COLUMNS);
    private final JLabel welcome = new JLabel();

    MainFrame(AppContext ctx, User user) {
        super("NexusMarket");
        this.ctx = ctx;
        this.user = user;
        EmployeeType role = user.getRole();
        isAdministrator = role == EmployeeType.ADMINISTRATOR;
        boolean isSupervisor = role == EmployeeType.SUPERVISOR;
        canManageProducts = role == EmployeeType.SELLER || isAdministrator || isSupervisor;

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("My profile", buildProfileTab());
        tabs.addTab("Products", buildProductsTab());
        if (isAdministrator || isSupervisor) {
            tabs.addTab("Users", buildUsersTab());
        }

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(8, 12, 8, 12));
        welcome.setText(welcomeText());
        header.add(welcome, BorderLayout.WEST);
        JButton logout = new JButton("Log out");
        logout.addActionListener(e -> {
            new LoginFrame(ctx).setVisible(true);
            dispose();
        });
        JPanel headerActions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton database = new JButton("Database connections");
        database.addActionListener(e -> testDatabaseConnections());
        headerActions.add(database);
        headerActions.add(logout);
        header.add(headerActions, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 540);
        setLocationRelativeTo(null);
    }

    private String welcomeText() {
        return "Welcome, " + user.getFullName() + " (" + user.getRole() + ")";
    }

    // ---------------------------------------------------------------- profile

    private JComponent buildProfileTab() {
        JPanel details = new JPanel(new GridBagLayout());
        details.setBorder(BorderFactory.createTitledBorder("Account details"));
        JTextField name = new JTextField(user.getFullName(), 24);
        JTextField email = new JTextField(user.getEmail());
        boolean isBuyer = user instanceof Buyer;
        JTextField address = new JTextField(isBuyer ? ((Buyer) user).getMainAddress() : "");

        int row = 0;
        Ui.addRow(details, row++, "ID", Ui.readOnly(String.valueOf(user.getId())));
        Ui.addRow(details, row++, "Role", Ui.readOnly(user.getRole().toString()));
        Ui.addRow(details, row++, "Status", Ui.readOnly(user.getStatus().toString()));
        Ui.addRow(details, row++, "Full name", name);
        Ui.addRow(details, row++, "Email", email);
        if (isBuyer) {
            Ui.addRow(details, row++, "Main address", address);
        }
        JButton save = new JButton("Save changes");
        save.addActionListener(e -> {
            try {
                ctx.authService.updateProfile(user.getId(), name.getText(), email.getText());
                if (isBuyer) {
                    ctx.buyerService.updateAddress(user.getId(), address.getText().trim());
                }
                welcome.setText(welcomeText());
                ctx.databaseService.updateUser(user);
                Ui.info(this, "Your profile was updated in the application and databases.");
            } catch (Exception ex) {
                Ui.error(this, ex.getMessage());
            }
        });
        Ui.addButtonRow(details, row, save);

        JPanel security = new JPanel(new GridBagLayout());
        security.setBorder(BorderFactory.createTitledBorder("Change password"));
        JPasswordField current = new JPasswordField(24);
        JPasswordField newPassword = new JPasswordField(24);
        JPasswordField confirm = new JPasswordField(24);
        Ui.addRow(security, 0, "Current password", current);
        Ui.addRow(security, 1, "New password", newPassword);
        Ui.addRow(security, 2, "Confirm new password", confirm);
        Ui.addRow(security, 3, "", new JLabel("<html><body style='width:300px'><small>" + AuthService.PASSWORD_RULES + "</small></body></html>"));
        JButton change = new JButton("Change password");
        change.addActionListener(e -> {
            try {
                ctx.authService.changePassword(user.getId(), Ui.text(current),
                        Ui.text(newPassword), Ui.text(confirm));
                current.setText("");
                newPassword.setText("");
                confirm.setText("");
                Ui.info(this, "Your password was changed.");
            } catch (IllegalArgumentException ex) {
                Ui.error(this, ex.getMessage());
            }
        });
        Ui.addButtonRow(security, 4, change);

        JPanel page = new JPanel();
        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));
        page.setBorder(new EmptyBorder(12, 12, 12, 12));
        page.add(details);
        page.add(javax.swing.Box.createVerticalStrut(10));
        page.add(security);
        return new JScrollPane(page);
    }

    // ---------------------------------------------------------------- products

    private JComponent buildProductsTab() {
        JTable table = createTable(productModel);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if (canManageProducts) {
            JButton add = new JButton("Add product");
            JButton rename = new JButton("Rename");
            JButton publish = new JButton("Publish");
            JButton suspend = new JButton("Suspend");
            JButton discontinue = new JButton("Discontinue");
            add.addActionListener(e -> addProduct());
            rename.addActionListener(e -> renameProduct(table));
            publish.addActionListener(e -> {
                int id = selectedId(table, "product");
                if (id >= 0) {
                    ctx.productService.publishProduct(id);
                    persistProductUpdate(id);
                    refreshProducts();
                }
            });
            suspend.addActionListener(e -> {
                int id = selectedId(table, "product");
                if (id >= 0) {
                    ctx.productService.suspendProduct(id);
                    persistProductUpdate(id);
                    refreshProducts();
                }
            });
            discontinue.addActionListener(e -> {
                int id = selectedId(table, "product");
                if (id >= 0) {
                    ctx.productService.discontinueProduct(id);
                    persistProductUpdate(id);
                    refreshProducts();
                }
            });
            buttons.add(add);
            buttons.add(rename);
            buttons.add(publish);
            buttons.add(suspend);
            buttons.add(discontinue);
            JButton saveDatabase = new JButton("Save to databases");
            saveDatabase.addActionListener(e -> saveSelectedProduct(table));
            JButton updateDatabase = new JButton("Update in databases");
            updateDatabase.addActionListener(e -> updateSelectedProduct(table));
            buttons.add(saveDatabase);
            buttons.add(updateDatabase);
        }
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> refreshProducts());
        buttons.add(refresh);

        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        refreshProducts();
        return panel;
    }

    private void refreshProducts() {
        productModel.setRowCount(0);
        for (Product product : ctx.productService.listProducts()) {
            if (canManageProducts || product.getStatus() == ProductStatus.PUBLISHED) {
                productModel.addRow(new Object[]{product.getId(), product.getName(),
                        product.getProductType(), product.getStatus().toString()});
            }
        }
    }

    private void addProduct() {
        JTextField name = new JTextField(20);
        JTextField type = new JTextField(20);
        JPanel form = new JPanel(new GridBagLayout());
        Ui.addRow(form, 0, "Name", name);
        Ui.addRow(form, 1, "Type", type);
        while (JOptionPane.showConfirmDialog(this, form, "Add product",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
            if (name.getText().trim().isEmpty() || type.getText().trim().isEmpty()) {
                Ui.error(this, "The name and the type are required.");
                continue;
            }
            Product product = new Product(name.getText().trim(), type.getText().trim(), ProductStatus.DRAFT);
            ctx.sellerService.registerProduct(product);
            try {
                ctx.databaseService.saveProduct(product);
                Ui.info(this, "Product created and saved to both databases with ID " + product.getId() + ".");
            } catch (Exception ex) {
                Ui.error(this, "Product created locally, but database save failed: " + ex.getMessage());
            }
            refreshProducts();
            return;
        }
    }

    private void renameProduct(JTable table) {
        int id = selectedId(table, "product");
        if (id < 0) {
            return;
        }
        String newName = JOptionPane.showInputDialog(this, "New name:", ctx.productService.findProduct(id).getName());
        if (newName == null) {
            return;
        }
        if (newName.trim().isEmpty()) {
            Ui.error(this, "The name is required.");
            return;
        }
        ctx.productService.updateProduct(id, newName.trim());
        persistProductUpdate(id);
        refreshProducts();
    }

    // ---------------------------------------------------------------- users

    private JComponent buildUsersTab() {
        JTable table = createTable(userModel);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if (isAdministrator) {
            JButton create = new JButton("Create user");
            create.addActionListener(e -> createUser());
            buttons.add(create);
        }
        JButton status = new JButton("Change status");
        status.addActionListener(e -> changeStatus(table));
        buttons.add(status);
        if (isAdministrator) {
            JButton role = new JButton("Assign role");
            role.addActionListener(e -> assignRole(table));
            buttons.add(role);
        }
        JButton saveDatabase = new JButton("Save to databases");
        saveDatabase.addActionListener(e -> saveSelectedUser(table));
        JButton updateDatabase = new JButton("Update in databases");
        updateDatabase.addActionListener(e -> updateSelectedUser(table));
        buttons.add(saveDatabase);
        buttons.add(updateDatabase);
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> refreshUsers());
        buttons.add(refresh);

        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        refreshUsers();
        return panel;
    }

    private void refreshUsers() {
        userModel.setRowCount(0);
        for (User u : ctx.userService.listUsers()) {
            userModel.addRow(new Object[]{u.getId(), u.getFullName(), u.getEmail(),
                    u.getRole().toString(), u.getStatus().toString()});
        }
    }

    private void createUser() {
        JTextField id = new JTextField(20);
        JTextField name = new JTextField(20);
        JTextField email = new JTextField(20);
        JComboBox<EmployeeType> type = new JComboBox<>(EmployeeType.values());
        JTextField address = new JTextField(20);
        JPasswordField password = new JPasswordField(20);
        JPasswordField confirm = new JPasswordField(20);
        type.addActionListener(e -> address.setEnabled(type.getSelectedItem() == EmployeeType.BUYER));
        address.setEnabled(type.getSelectedItem() == EmployeeType.BUYER);

        JPanel form = new JPanel(new GridBagLayout());
        Ui.addRow(form, 0, "ID", id);
        Ui.addRow(form, 1, "Full name", name);
        Ui.addRow(form, 2, "Email", email);
        Ui.addRow(form, 3, "Account type", type);
        Ui.addRow(form, 4, "Main address", address);
        Ui.addRow(form, 5, "Password", password);
        Ui.addRow(form, 6, "Confirm password", confirm);
        Ui.addRow(form, 7, "", new JLabel("<html><body style='width:300px'><small>" + AuthService.PASSWORD_RULES + "</small></body></html>"));

        while (JOptionPane.showConfirmDialog(this, form, "Create user",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
            final User created;
            try {
                created = ctx.authService.register(AuthService.parseId(id.getText()), name.getText(), email.getText(),
                        (EmployeeType) type.getSelectedItem(), address.getText(),
                        Ui.text(password), Ui.text(confirm));
            } catch (IllegalArgumentException ex) {
                Ui.error(this, ex.getMessage());
                continue;
            }
            refreshUsers();
            try {
                ctx.databaseService.saveUser(created);
                Ui.info(this, "User created and saved to SQL Server and MongoDB.");
            } catch (Exception dbError) {
                Ui.error(this, "User created in this session, but database synchronization failed: "
                        + dbError.getMessage() + ". Select the user and retry with Save to databases.");
            }
            return;
        }
    }

    private void changeStatus(JTable table) {
        int id = selectedId(table, "user");
        if (id < 0) {
            return;
        }
        if (id == user.getId()) {
            Ui.error(this, "You cannot change the status of your own account.");
            return;
        }
        JComboBox<UserStatus> combo = new JComboBox<>(UserStatus.values());
        combo.setSelectedItem(ctx.userService.findUser(id).getStatus());
        if (JOptionPane.showConfirmDialog(this, combo, "Change status",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
            try {
                ctx.userService.changeStatus(id, (UserStatus) combo.getSelectedItem());
                ctx.databaseService.updateUser(ctx.userService.findUser(id));
                refreshUsers();
            } catch (Exception ex) {
                Ui.error(this, "User changed locally, but database update failed: " + ex.getMessage());
            }
        }
    }

    private void assignRole(JTable table) {
        int id = selectedId(table, "user");
        if (id < 0) {
            return;
        }
        if (id == user.getId()) {
            Ui.error(this, "You cannot change the role of your own account.");
            return;
        }
        JComboBox<EmployeeType> combo = new JComboBox<>(EmployeeType.values());
        combo.setSelectedItem(ctx.userService.findUser(id).getRole());
        if (JOptionPane.showConfirmDialog(this, combo, "Assign role",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
            try {
                ctx.userService.assignRole(id, (EmployeeType) combo.getSelectedItem());
                ctx.databaseService.updateUser(ctx.userService.findUser(id));
                refreshUsers();
            } catch (Exception ex) {
                Ui.error(this, "User changed locally, but database update failed: " + ex.getMessage());
            }
        }
    }

    // ---------------------------------------------------------------- database

    private void testDatabaseConnections() {
        String sql = ctx.databaseService.testSqlServer();
        String mongo = ctx.databaseService.testMongoDb();
        JOptionPane.showMessageDialog(this, sql + "\\n\\n" + mongo,
                "Database connections", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveSelectedUser(JTable table) {
        int id = selectedId(table, "user");
        if (id < 0) return;
        try {
            ctx.databaseService.saveUser(ctx.userService.findUser(id));
            Ui.info(this, "User saved to SQL Server and MongoDB.");
        } catch (Exception ex) {
            Ui.error(this, "Database save failed: " + ex.getMessage());
        }
    }

    private void updateSelectedUser(JTable table) {
        int id = selectedId(table, "user");
        if (id < 0) return;
        try {
            ctx.databaseService.updateUser(ctx.userService.findUser(id));
            Ui.info(this, "User updated in SQL Server and MongoDB.");
        } catch (Exception ex) {
            Ui.error(this, "Database update failed: " + ex.getMessage());
        }
    }

    private void saveSelectedProduct(JTable table) {
        int id = selectedId(table, "product");
        if (id < 0) return;
        try {
            ctx.databaseService.saveProduct(ctx.productService.findProduct(id));
            Ui.info(this, "Product saved to SQL Server and MongoDB.");
        } catch (Exception ex) {
            Ui.error(this, "Database save failed: " + ex.getMessage());
        }
    }

    private void updateSelectedProduct(JTable table) {
        int id = selectedId(table, "product");
        if (id < 0) return;
        try {
            ctx.databaseService.updateProduct(ctx.productService.findProduct(id));
            Ui.info(this, "Product updated in SQL Server and MongoDB.");
        } catch (Exception ex) {
            Ui.error(this, "Database update failed: " + ex.getMessage());
        }
    }

    private void persistProductUpdate(int id) {
        try {
            ctx.databaseService.updateProduct(ctx.productService.findProduct(id));
        } catch (Exception ex) {
            Ui.error(this, "The product changed in the application, but database update failed: " + ex.getMessage());
        }
    }

    // ---------------------------------------------------------------- helpers

    private int selectedId(JTable table, String noun) {
        int row = table.getSelectedRow();
        if (row < 0) {
            Ui.info(this, "Select a " + noun + " first.");
            return -1;
        }
        return (Integer) table.getValueAt(row, 0);
    }

    private static JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(24);
        return table;
    }

    private static DefaultTableModel readOnlyModel(String[] columns) {
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? Integer.class : String.class;
            }
        };
    }
}
