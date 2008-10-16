package org.mvnsearch.snippet.plugin.ui;

import org.mvnsearch.snippet.Category;
import org.mvnsearch.snippet.Snippet;
import org.mvnsearch.snippet.SnippetSearchAgent;
import org.mvnsearch.snippet.impl.mvnsearch.MvnSnippet;
import org.mvnsearch.snippet.impl.mvnsearch.MvnSnippetSearchAgent;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.util.Collection;

/**
 * snippet editor form
 *
 * @author linux_china@hotmail.com
 */
public class SnippetEditForm {
    private JPanel rootPanel;
    private JTextField nameField;
    private JTextField mnemonicField;
    private JTextField authorField;
    private JTextField keywordsField;
    private JTabbedPane tabbedPane1;
    private JTextArea codeTextArea;
    private JTextArea descTextArea;
    private JComboBox categoryCombo;
    private JComboBox languageCombo;

    /**
     * get root panel
     *
     * @return root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * fill infomation
     *
     * @param agent   agent
     * @param snippet snippet object
     */
    public void fillInfo(SnippetSearchAgent agent, Snippet snippet) {
        categoryCombo.removeAllItems();
        Collection<Category> rootCategories = agent.findRootCategories();
        for (Category category : rootCategories) {
            categoryCombo.addItem(category);
        }
        if (agent instanceof MvnSnippetSearchAgent) {
            MvnSnippetSearchAgent mvnAgent = (MvnSnippetSearchAgent) agent;
            languageCombo.removeAllItems();
            for (String language : mvnAgent.getLanguagesData().values()) {
                languageCombo.addItem(language);
            }
        }
        if (snippet != null) {
            nameField.setText(snippet.getTitle());
            authorField.setText(snippet.getAuthor());
            codeTextArea.setText(snippet.getCode());
            descTextArea.setText(snippet.getDescription());
            if (snippet instanceof MvnSnippet) {
                MvnSnippet mvnSnippet = (MvnSnippet) snippet;
                mnemonicField.setText(mvnSnippet.getMnemonic());
                keywordsField.setText(mvnSnippet.getKeywords());
                for (Category rootCategory : rootCategories) {
                    if (rootCategory.getId().equals(mvnSnippet.getCategoryId())) {
                        categoryCombo.setSelectedItem(rootCategory);
                        break;
                    }
                }
                descTextArea.setText(mvnSnippet.getRawDescription());
            }
            languageCombo.setSelectedItem(snippet.getLanguage());
        } else {
            nameField.setText(null);
            codeTextArea.setText(null);
            descTextArea.setText(null);
            mnemonicField.setText(null);
            keywordsField.setText(null);
        }
    }

    /**
     * is invalid
     *
     * @return invalid mark
     */
    public boolean isInvalid() {
        if (StringUtils.isEmpty(nameField.getText())) {
            nameField.requestFocus();
            return true;
        }
        if (StringUtils.isEmpty(authorField.getText())) {
            authorField.requestFocus();
            return true;
        }
        if (StringUtils.isEmpty(codeTextArea.getText())) {
            codeTextArea.requestFocus();
            return true;
        }
        if (StringUtils.isEmpty(mnemonicField.getText())) {
            mnemonicField.requestFocus();
            return true;
        }
        if (StringUtils.isEmpty(keywordsField.getText())) {
            keywordsField.requestFocus();
            return true;
        }
        if (StringUtils.isEmpty(descTextArea.getText())) {
            descTextArea.requestFocus();
            return true;
        }
        if (languageCombo.getSelectedIndex() < 0) {
            languageCombo.requestFocus();
            return true;
        }
        if (categoryCombo.getSelectedIndex() < 0) {
            categoryCombo.requestFocus();
            return true;
        }
        return false;
    }

    /**
     * fill snippet
     *
     * @param snippet snippet
     */
    public void fillSnippet(Snippet snippet) {
        snippet.setTitle(nameField.getText());
        snippet.setAuthor(authorField.getText());
        snippet.setCode(codeTextArea.getText());
        snippet.setLanguage((String) languageCombo.getSelectedItem());
        if (snippet instanceof MvnSnippet) {
            MvnSnippet mvnSnippet = (MvnSnippet) snippet;
            mvnSnippet.setKeywords(keywordsField.getText());
            mvnSnippet.setMnemonic(mnemonicField.getText());
            mvnSnippet.setRawDescription(descTextArea.getText());
            Category category = (Category) categoryCombo.getSelectedItem();
            mvnSnippet.setCategoryId(category.getId());
        } else {
            snippet.setDescription(descTextArea.getText());
        }
    }
}