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
