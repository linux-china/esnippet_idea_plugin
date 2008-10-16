package org.mvnsearch.snippet.plugin.ui;

import javax.swing.*;

/**
 * repository info form
 *
 * @author linux_china@hotmail.com
 */
public class RepositoryInfoForm {
    private JTextField serviceUrlField;
    private JTextField userNameField;
    private JPanel rootPanel;

    /**
     * get root panel for form
     *
     * @return form panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * fill information
     *
     * @param serviceUrl service url
     * @param userName   user name
     */
    public void fillInfo(String serviceUrl, String userName) {
        serviceUrlField.setText(serviceUrl);
        userNameField.setText(userName);
    }

    /**
     * get service url
     *
     * @return service url
     */
    public String getServiceUrl() {
        return serviceUrlField.getText();
    }

    /**
     * get user name
     *
     * @return user name
     */
    public String getUserName() {
        return userNameField.getText();
    }
}
