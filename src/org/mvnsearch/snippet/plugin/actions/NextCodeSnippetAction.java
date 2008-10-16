package org.mvnsearch.snippet.plugin.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Navigates to next code snippet.
 *
 * @author Anki R Nelaturu
 */
public class NextCodeSnippetAction extends SnippetAction {
    /**
     * step into next snippet
     *
     * @param e event
     */
    public void actionPerformed(AnActionEvent e) {
        getSearchPanelForm(e).nextCodeSnippet();
    }

    /**
     * update button state
     *
     * @param event event
     */
    public void update(AnActionEvent event) {
        try {
            event.getPresentation().setEnabled(getSearchPanelForm(event).hasNext());
        } catch (Exception e) {

        }
    }
}
