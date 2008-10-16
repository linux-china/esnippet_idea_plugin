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
