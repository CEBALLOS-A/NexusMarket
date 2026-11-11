package com.nexusmarket.infrastructure.adapters.ui;

import com.nexusmarket.domain.model.EmployeeType;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Graphical entry point: login, registration and the panel for each role.
 * A demo administrator is created so the user management can be tried out.
 */
public class NexusApp {

    private static final String DEMO_ADMIN_ID = "1";
    private static final String DEMO_ADMIN_PASSWORD = "Admin#2026";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // the default look and feel is used
        }
        AppContext ctx = new AppContext();
        ctx.authService.register(Integer.parseInt(DEMO_ADMIN_ID), "Ana Admin", "ana@nexus.com",
                EmployeeType.ADMINISTRATOR, null, DEMO_ADMIN_PASSWORD, DEMO_ADMIN_PASSWORD);
        SwingUtilities.invokeLater(() -> new LoginFrame(ctx).setVisible(true));
    }
}
