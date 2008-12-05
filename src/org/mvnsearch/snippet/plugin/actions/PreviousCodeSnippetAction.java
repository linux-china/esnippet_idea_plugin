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

/**
 * Navigates to previous code snippet in the current result page.
 *
 * @author Anki R Nelaturu
 */
public class PreviousCodeSnippetAction extends SnippetAction {
    /**
     * step into previous snippet
     *
     * @param e event
     */
    public void actionPerformed(AnActionEvent e) {
        getSearchPanelForm(e).previousCodeSnippet();
    }

    /**
     * update button status
     *
     * @param event event
     */
    public void update(final AnActionEvent event) {
        try {
            event.getPresentation().setEnabled(getSearchPanelForm(event).hasPrevious());
        } catch (Exception e1) {
        }
    }
}
