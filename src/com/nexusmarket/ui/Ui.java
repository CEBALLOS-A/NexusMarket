package com.nexusmarket.ui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/** Small helpers shared by the windows. */
final class Ui {

    private Ui() {
    }

    static void addRow(JPanel panel, int row, String label, JComponent field) {
        GridBagConstraints l = new GridBagConstraints();
        l.gridx = 0;
        l.gridy = row;
        l.anchor = GridBagConstraints.WEST;
        l.insets = new Insets(4, 4, 4, 10);
        panel.add(new JLabel(label), l);

        GridBagConstraints f = new GridBagConstraints();
        f.gridx = 1;
        f.gridy = row;
        f.weightx = 1;
        f.fill = GridBagConstraints.HORIZONTAL;
        f.insets = new Insets(4, 0, 4, 4);
        panel.add(field, f);
    }

    static void addButtonRow(JPanel panel, int row, JButton... buttons) {
        JPanel box = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 0));
        for (JButton button : buttons) {
            box.add(button);
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 4, 4, 4);
        panel.add(box, c);
    }

    static JTextField readOnly(String text) {
        JTextField field = new JTextField(text);
        field.setEditable(false);
        return field;
    }

    static String text(JPasswordField field) {
        return new String(field.getPassword());
    }

    static void error(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "NexusMarket", JOptionPane.ERROR_MESSAGE);
    }

    static void info(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "NexusMarket", JOptionPane.INFORMATION_MESSAGE);
    }
}
