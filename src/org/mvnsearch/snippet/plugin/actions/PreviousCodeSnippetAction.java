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
