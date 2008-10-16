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
