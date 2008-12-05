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

import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import org.mvnsearch.snippet.plugin.SnippetProjectComponent;

/**
 * Pastes the current snippet into current editor (if opened)
 *
 * @author Anki R Nelaturu
 */
public class PasteAction extends EditorAction {
    public PasteAction() {
        super(new EditorWriteActionHandler() {
            /**
             * get snippet code and past it into editor
             * @param editor    editor
             * @param dataContext datacontext
             */
            @SuppressWarnings({"ConstantConditions"})
            public void executeWriteAction(Editor editor, DataContext dataContext) {
                final Project project = (Project) dataContext.getData(DataConstants.PROJECT);
                final SnippetProjectComponent projectComponent = project.getComponent(SnippetProjectComponent.class);
                SearchPanelForm searchPanel = projectComponent.getSearchPanelForm();
                String currentSnippet = searchPanel.getCurrentSnippetCode();
                if (editor != null && StringUtil.isNotEmpty(currentSnippet)) {
                    CaretModel caretModel = editor.getCaretModel();
                    com.intellij.openapi.editor.Document document = editor.getDocument();
                    int offset = caretModel.getOffset();
                    document.insertString(offset, currentSnippet);
                }
            }
        });
    }
}
