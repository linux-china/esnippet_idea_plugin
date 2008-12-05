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

package org.mvnsearch.snippet.plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;
import org.mvnsearch.snippet.plugin.SnippetProjectComponent;

/**
 * Base class for all other actions.
 *
 * @author Anki R Nelaturu
 */
public abstract class SnippetAction extends AnAction {
    /**
     * get search panel form
     *
     * @param event action event
     * @return search panel form
     */
    @SuppressWarnings({"ConstantConditions"})
    public SearchPanelForm getSearchPanelForm(final AnActionEvent event) {
        final Project project = event.getData(DataKeys.PROJECT);
        final SnippetProjectComponent projectComponent = project.getComponent(SnippetProjectComponent.class);
        return projectComponent.getSearchPanelForm();
    }
}
