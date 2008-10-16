package org.mvnsearch.snippet.plugin.ui;

import org.mvnsearch.snippet.plugin.util.SnippetMnemonicCompleter;

import javax.swing.*;

/**
 * snippet file creation form with auto completion for mnemonic
 *
 * @author linux_china@hotmail.com
 */
public class SnippetFileCreationForm {
    private JTextField mnemonicField;
    private JTextField fileNameField;
    private JPanel rootPanel;

    /**
     * construct snippet creation form, initialize auto completion
     */
    public SnippetFileCreationForm() {
        new SnippetMnemonicCompleter(mnemonicField);
    }

    /**
     * get root panel
     *
     * @return root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * get mnemonic
     *
     * @return mnemonic
     */
    public String getMnemonic() {
        return mnemonicField.getText();
    }

    /**
     * get file name
     *
     * @return file name
     */
    public String getFileName() {
        return fileNameField.getText();
    }
}
