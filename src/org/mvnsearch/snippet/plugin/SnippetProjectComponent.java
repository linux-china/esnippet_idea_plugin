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

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;
import org.mvnsearch.snippet.plugin.actions.SearchPanelForm;

/**
 * snippet project component
 *
 * @author linux_china@hotmail.com
 */
public class SnippetProjectComponent implements ProjectComponent {
    private Project project;
    private SearchPanelForm searchPanelForm;

    public SnippetProjectComponent(Project project) {
        this.project = project;
    }

    public void initComponent() {

    }

    public void disposeComponent() {

    }

    @NotNull
    public String getComponentName() {
        return "SnippetProjectComponent";
    }

    /**
     * register snippet search tool window
     */
    public void projectOpened() {
        searchPanelForm = new SearchPanelForm();
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow window = toolWindowManager.registerToolWindow("eSnippet Pro", searchPanelForm.mainPanel, ToolWindowAnchor.BOTTOM);
        window.setIcon(IconLoader.findIcon("/org/mvnsearch/snippet/plugin/icons/logo.png"));
    }

    public void projectClosed() {
        // called when project is being closed
    }

    /**
     * get search panel form
     *
     * @return form
     */
    public SearchPanelForm getSearchPanelForm() {
        return searchPanelForm;
    }
}
