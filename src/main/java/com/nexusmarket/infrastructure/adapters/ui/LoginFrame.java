package com.nexusmarket.infrastructure.adapters.ui;

import com.nexusmarket.domain.model.EmployeeType;
import com.nexusmarket.domain.model.User;
import com.nexusmarket.domain.service.AuthService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

public class LoginFrame extends JFrame {

    private static final String LOGIN = "login";
    private static final String REGISTER = "register";

    private final AppContext ctx;
    private final CardLayout cards = new CardLayout();
    private final JPanel root = new JPanel(cards);

    private final JTextField loginId = new JTextField(20);
    private final JPasswordField loginPassword = new JPasswordField(20);
    private final JButton loginButton = new JButton("Log in");

    private final JTextField regId = new JTextField(20);
    private final JTextField regName = new JTextField(20);
    private final JTextField regEmail = new JTextField(20);
    private final JComboBox<EmployeeType> regType =
            new JComboBox<>(new EmployeeType[]{EmployeeType.BUYER, EmployeeType.SELLER});
    private final JTextField regAddress = new JTextField(20);
    private final JPasswordField regPassword = new JPasswordField(20);
    private final JPasswordField regConfirm = new JPasswordField(20);
    private final JButton registerButton = new JButton("Create account");

    LoginFrame(AppContext ctx) {
        super("NexusMarket");
        this.ctx = ctx;
        root.add(buildLoginPanel(), LOGIN);
        root.add(buildRegisterPanel(), REGISTER);
        add(root);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        showCard(LOGIN);
    }

    private JPanel buildLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 28, 20, 28));

        JLabel title = new JLabel("NexusMarket", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        JLabel subtitle = new JLabel("Log in to your account", SwingConstants.CENTER);
        JPanel head = new JPanel(new BorderLayout());
        head.add(title, BorderLayout.NORTH);
        head.add(subtitle, BorderLayout.SOUTH);
        panel.add(head, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        JCheckBox show = new JCheckBox("Show password");
        char echo = loginPassword.getEchoChar();
        show.addActionListener(e -> loginPassword.setEchoChar(show.isSelected() ? (char) 0 : echo));
        Ui.addRow(form, 0, "ID", loginId);
        Ui.addRow(form, 1, "Password", loginPassword);
        Ui.addRow(form, 2, "", show);
        JButton goRegister = new JButton("Create account");
        Ui.addButtonRow(form, 3, goRegister, loginButton);
        panel.add(form, BorderLayout.CENTER);

        loginButton.addActionListener(e -> doLogin());
        goRegister.addActionListener(e -> showCard(REGISTER));
        return panel;
    }

    private JPanel buildRegisterPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 28, 20, 28));

        JLabel title = new JLabel("Create your account", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        panel.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        Ui.addRow(form, 0, "ID", regId);
        Ui.addRow(form, 1, "Full name", regName);
        Ui.addRow(form, 2, "Email", regEmail);
        Ui.addRow(form, 3, "Account type", regType);
        Ui.addRow(form, 4, "Main address", regAddress);
        Ui.addRow(form, 5, "Password", regPassword);
        Ui.addRow(form, 6, "Confirm password", regConfirm);
        JLabel rules = new JLabel("<html><body style='width:240px'><small>Password: " + AuthService.PASSWORD_RULES + "</small></body></html>");
        Ui.addRow(form, 7, "", rules);
        JButton back = new JButton("Back");
        Ui.addButtonRow(form, 8, back, registerButton);
        panel.add(form, BorderLayout.CENTER);

        regType.addActionListener(e -> regAddress.setEnabled(regType.getSelectedItem() == EmployeeType.BUYER));
        registerButton.addActionListener(e -> doRegister());
        back.addActionListener(e -> showCard(LOGIN));
        return panel;
    }

    private void showCard(String name) {
        cards.show(root, name);
        getRootPane().setDefaultButton(LOGIN.equals(name) ? loginButton : registerButton);
        pack();
        setLocationRelativeTo(null);
    }

    private void doLogin() {
        try {
            int id = AuthService.parseId(loginId.getText());
            User user = ctx.authService.login(id, Ui.text(loginPassword));
            loginPassword.setText("");
            new MainFrame(ctx, user).setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            loginPassword.setText("");
            Ui.error(this, ex.getMessage());
        }
    }

    private void doRegister() {
        try {
            int id = AuthService.parseId(regId.getText());
            User created = ctx.authService.selfRegister(id, regName.getText(), regEmail.getText(),
                    (EmployeeType) regType.getSelectedItem(), regAddress.getText(),
                    Ui.text(regPassword), Ui.text(regConfirm));
            try {
                ctx.databaseService.saveUser(created);
                Ui.info(this, "Account created and saved to SQL Server and MongoDB.");
            } catch (Exception dbError) {
                Ui.error(this, "The account was created in this session, but database persistence failed: "
                        + dbError.getMessage() + ". Check the database connections and retry from the administrator Users tab.");
            }
            for (JTextField field : new JTextField[]{regId, regName, regEmail, regAddress, regPassword, regConfirm}) {
                field.setText("");
            }
            showCard(LOGIN);
        } catch (IllegalArgumentException ex) {
            Ui.error(this, ex.getMessage());
        }
    }
}
