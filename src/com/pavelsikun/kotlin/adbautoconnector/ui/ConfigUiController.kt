package com.pavelsikun.kotlin.adbautoconnector.ui

import com.pavelsikun.kotlin.adbautoconnector.logic.openConfig
import com.pavelsikun.kotlin.adbautoconnector.logic.saveConfig
import com.pavelsikun.kotlin.adbautoconnector.logic.isValidIp
import java.awt.event.ActionEvent
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

/**
 * Created by mrbimc on 05.07.15.
 */
class ConfigUiController(val window: Configurator) {

    val config = openConfig()

    class IpListener(val field: JTextField, val window: Configurator) : DocumentListener {
        override fun changedUpdate(e: DocumentEvent) = react()
        override fun removeUpdate(e: DocumentEvent) = react()
        override fun insertUpdate(e: DocumentEvent) = react()

        fun react() {
            var isValidIp = field.getText().isValidIp()
            val button = window.getApplyChangesButton()
            if (isValidIp) {
                button.setEnabled(true)
                button.setText("Apply changes")
            } else {
                button.setText("invalid data :(")
                button.setEnabled(false)
            }
        }
    }

    init {
        window.getConnectionModeComboBox().setSelectedIndex(config.mode)
        window.getSingleIpTextField().setText(config.single)
        window.getFromIpTextField().setText(config.rangeFrom)
        window.getToIpTextField().setText(config.rangeTo)

        connectionModeSelected(config.mode)
        window.getApplyChangesButton().setEnabled(false)

        window.getConnectionModeComboBox().addItemListener {
            config.mode = window.getConnectionModeComboBox().getSelectedIndex()
            connectionModeSelected(config.mode)
        }

        window.getSingleIpTextField().getDocument().addDocumentListener(IpListener(window.getSingleIpTextField(), window))
        window.getToIpTextField().getDocument().addDocumentListener(IpListener(window.getToIpTextField(), window))
        window.getFromIpTextField().getDocument().addDocumentListener(IpListener(window.getFromIpTextField(), window))

        window.getApplyChangesButton().addActionListener { applyChanges() }
    }


    private fun connectionModeSelected(index: Int): Unit = when (index) {
        0 -> setModeAllIps()
        1 -> setModeSingleIp()
        2 -> setModeRangeIps()
    }

    private fun setModeAllIps() {
        setSingleIpVisibility(false)
        setRangeIpsVisibility(false)

        window.getApplyChangesButton().setEnabled(true)
        window.getApplyChangesButton().setText("Apply changes")
    }

    private fun setModeSingleIp() {
        setSingleIpVisibility(true)
        setRangeIpsVisibility(false)

        val shouldBeEnabled = window.getSingleIpTextField().getText().isValidIp()
        window.getApplyChangesButton().setEnabled(shouldBeEnabled)
        if(shouldBeEnabled) window.getApplyChangesButton().setText("Apply changes")
    }

    private fun setModeRangeIps() {
        setSingleIpVisibility(false)
        setRangeIpsVisibility(true)

        val shouldBeEnabled = window.getFromIpTextField().getText().isValidIp()
                && window.getToIpTextField().getText().isValidIp()

        window.getApplyChangesButton().setEnabled(shouldBeEnabled)
        if(shouldBeEnabled) window.getApplyChangesButton().setText("Apply changes")
    }

    private fun setRangeIpsVisibility(visibility: Boolean) {
        window.getFromIpTextField().setVisible(visibility)
        window.getToIpTextField().setVisible(visibility)

        window.getToIpLabel().setVisible(visibility)
        window.getFromIpLabel().setVisible(visibility)
    }

    private fun setSingleIpVisibility(visibility: Boolean) {
        window.getSingleIpTextField().setVisible(visibility)
        window.getSingleIpLabel().setVisible(visibility)
    }

    private fun applyChanges(): Unit = when(config.mode) {
        0 -> saveConfig(config)
        1 -> {
            config.single = window.getSingleIpTextField().getText()
            saveConfig(config)
        }
        2 -> {
            config.rangeFrom = window.getFromIpTextField().getText()
            config.rangeTo = window.getToIpTextField().getText()
            saveConfig(config)
        }
    }
}

