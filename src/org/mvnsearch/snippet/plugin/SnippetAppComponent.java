package org.mvnsearch.snippet.plugin;

import com.caucho.hessian.client.HessianProxyFactory;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.*;
import com.intellij.openapi.util.text.StringUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.mvnsearch.snippet.plugin.ui.RepositoryInfoForm;
import org.mvnsearch.snippet.impl.mvnsearch.SnippetService;

import javax.swing.*;

/**
 * snippet application component
 *
 * @author linux_china@hotmail.com
 */
public class SnippetAppComponent implements ApplicationComponent, Configurable, JDOMExternalizable {
    private SnippetService snippetService;
    public String serviceUrl;  //service url
    public String userName;    //user name
    private RepositoryInfoForm form = new RepositoryInfoForm();

    /**
     * get snippet project component
     *
     * @return Snippet App Component
     */
    public static SnippetAppComponent getInstance() {
        return ApplicationManager.getApplication().getComponent(SnippetAppComponent.class);
    }

    /**
     * get snippet service  interface
     *
     * @return snippet service interface
     */
    public SnippetService getSnippetService() {
        if (snippetService == null) {
            initSnippetService();
        }
        return snippetService;
    }


    public void initComponent() {
        initSnippetService();
    }

    /**
     * create snippet service proxy
     */
    public void initSnippetService() {
        try {
            if (StringUtil.isEmpty(serviceUrl)) {
                serviceUrl = "http://snippet.mvnsearch.org/remoting/snippetService";
            }
            HessianProxyFactory factory = new HessianProxyFactory();
            snippetService = (SnippetService) factory.create(SnippetService.class, serviceUrl, this.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dispose component
     */
    public void disposeComponent() {

    }

    @NotNull
    public String getComponentName() {
        return "SnippetAppComponent";
    }

    /**
     * get display name
     *
     * @return display name
     */
    public String getDisplayName() {
        return "Snippet Repository";
    }

    /**
     * get icon for configuration
     *
     * @return icon
     */
    public Icon getIcon() {
        return IconLoader.findIcon("/org/mvnsearch/snippet/plugin/icons/repository.png");
    }

    /**
     * get help topic id
     *
     * @return help topic id
     */
    public String getHelpTopic() {
        return null;
    }

    /**
     * get repository configuration form panel
     *
     * @return form panel
     */
    public JComponent createComponent() {
        if (StringUtil.isEmpty(serviceUrl)) {
            serviceUrl = "http://snippet.mvnsearch.org/remoting/snippetService";
        }
        form.fillInfo(serviceUrl, userName);
        return form.getRootPanel();
    }

    /**
     * get modified mark
     *
     * @return modified mark
     */
    public boolean isModified() {
        return true;
    }

    /**
     * apply setting
     *
     * @throws ConfigurationException exception
     */
    public void apply() throws ConfigurationException {
        this.serviceUrl = form.getServiceUrl();
        this.userName = form.getUserName();
    }

    /**
     * reset logic
     */
    public void reset() {

    }

    public void disposeUIResources() {

    }

    /**
     * reader external configuration
     *
     * @param element element
     * @throws InvalidDataException exception
     */
    public void readExternal(Element element) throws InvalidDataException {
        DefaultJDOMExternalizer.readExternal(this, element);
    }

    /**
     * writer exteranl configuration
     *
     * @param element element
     * @throws WriteExternalException exception
     */
    public void writeExternal(Element element) throws WriteExternalException {
        DefaultJDOMExternalizer.writeExternal(this, element);
    }
}
