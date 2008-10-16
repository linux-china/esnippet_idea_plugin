package org.mvnsearch.snippet.plugin.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
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
        Project project = event.getData(DataKeys.PROJECT);
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