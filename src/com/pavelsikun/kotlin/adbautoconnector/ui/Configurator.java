package com.pavelsikun.kotlin.adbautoconnector.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mrbimc on 05.07.15.
 */
public class Configurator {
    private JPanel container;
    private JComboBox connectionModeComboBox;
    private JLabel singleIpLabel;
    private JTextField singleIpTextField;
    private JTextField fromIpTextField;
    private JTextField toIpTextField;
    private JLabel fromIpLabel;
    private JLabel toIpLabel;
    private JButton applyChangesButton;

    public Configurator() {
        JFrame frame = new JFrame("ADB Autoconnector Configurator");
        frame.setContentPane(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);

        new ConfigUiController(this);
    }

    public JComboBox getConnectionModeComboBox() {
        return connectionModeComboBox;
    }

    public JLabel getSingleIpLabel() {
        return singleIpLabel;
    }

    public JTextField getSingleIpTextField() {
        return singleIpTextField;
    }

    public JTextField getFromIpTextField() {
        return fromIpTextField;
    }

    public JTextField getToIpTextField() {
        return toIpTextField;
    }

    public JLabel getFromIpLabel() {
        return fromIpLabel;
    }

    public JLabel getToIpLabel() {
        return toIpLabel;
    }

    public JButton getApplyChangesButton() {
        return applyChangesButton;
    }
}
