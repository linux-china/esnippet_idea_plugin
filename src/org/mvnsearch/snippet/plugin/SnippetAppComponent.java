/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import javax.swing.text.EditorKit;

import jsyntaxpane.syntaxkits.*;

import java.util.Map;
import java.util.HashMap;

/**
 * snippet application component
 *
 * @author linux_china@hotmail.com
 */
public class SnippetAppComponent implements ApplicationComponent {
    private SnippetService snippetService;
    public String serviceUrl;  //service url
    public String userName;    //user name
    private RepositoryInfoForm form = new RepositoryInfoForm();
    private Map<String, EditorKit> editorKits = new HashMap<String, EditorKit>();

    /**
     * get snippet project component
     *
     * @return Snippet App Component
     */
    public static SnippetAppComponent getInstance() {
        return ApplicationManager.getApplication().getComponent(SnippetAppComponent.class);
    }

    /**
     * get editor kit according to content type
     *
     * @param contentType content type
     * @return editor kit
     */
    public EditorKit getEditorKit(String contentType) {
        return editorKits.get(contentType.toLowerCase());
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


    /**
     * init component: editorkit regestration, init snippet service
     */
    public void initComponent() {
        jsyntaxpane.DefaultSyntaxKit.initKit();
        editorKits.put("text/c", new CSyntaxKit());
        editorKits.put("text/cpp", new CppSyntaxKit());
        editorKits.put("text/java", new JavaSyntaxKit());
        editorKits.put("text/groovy", new GroovySyntaxKit());
        editorKits.put("text/javascript", new JavaScriptSyntaxKit());
        editorKits.put("text/sql", new SqlSyntaxKit());
        editorKits.put("text/xml", new XmlSyntaxKit());
        editorKits.put("text/python", new PythonSyntaxKit());
        editorKits.put("text/ruby", new RubySyntaxKit());
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

}
