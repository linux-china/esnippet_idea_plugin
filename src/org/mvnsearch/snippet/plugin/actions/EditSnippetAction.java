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

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import org.mvnsearch.snippet.Snippet;
import org.mvnsearch.snippet.plugin.ui.SnippetEditForm;

/**
 * edit snippet action
 *
 * @author linux_china@hotmail.com
 */
public class EditSnippetAction extends SnippetAction {
    SnippetEditForm editForm = new SnippetEditForm();

    @Override
    public void update(AnActionEvent event) {
        try {
            SearchPanelForm searchForm = getSearchPanelForm(event);
            event.getPresentation().setEnabled(searchForm.getSelectedAgent().isSnippetUpdated());
        } catch (Exception e) {

        }
    }

    /**
     * create or save snippet
     *
     * @param event event
     */
    public void actionPerformed(final AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        final SearchPanelForm searchPanelForm = getSearchPanelForm(event);
        final Snippet currentSnippet = searchPanelForm.getCurrentSnippet();
        editForm.fillInfo(searchPanelForm.getSelectedAgent(), currentSnippet);
        //new snippet for edit
        final Snippet newSnippet = currentSnippet == null ? searchPanelForm.getSelectedAgent().constructNewSnippet() : currentSnippet;
        final DialogBuilder builder = new DialogBuilder(project);
        builder.setTitle("Snippet Edit(All Field Required)");
        builder.setCenterPanel(editForm.getRootPanel());
        builder.setOkActionEnabled(true);
        builder.setOkOperation(new Runnable() {
            public void run() {
                if (editForm.isInvalid()) {
                    builder.setTitle("Please fill requried fields!");
                    return;
                }
                builder.getDialogWrapper().close(1);
                editForm.fillSnippet(newSnippet);
                searchPanelForm.getSelectedAgent().updateSnippet(newSnippet);
                getSearchPanelForm(event).refreshSnippetInfo();
            }
        });
        builder.showModal(true);
    }
}