package org.mvnsearch.snippet.plugin.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ide.BrowserUtil;
import org.mvnsearch.snippet.Snippet;
import org.apache.commons.lang.StringUtils;

/**
 * open snippet in browser action
 *
 * @author linux_china@hotmail.com
 */
public class OpenSnippetInBrowserAction extends SnippetAction {
    /**
     * open snippet detail in browser
     *
     * @param event action event
     */
    public void actionPerformed(AnActionEvent event) {
        SearchPanelForm searchPanelForm = getSearchPanelForm(event);
        Snippet snippet = searchPanelForm.getCurrentSnippet();
        //open snippet
        if (snippet != null && StringUtils.isNotEmpty(snippet.getDetailUrl())) {
            BrowserUtil.launchBrowser(snippet.getDetailUrl());
        } else {  //open home site
            BrowserUtil.launchBrowser(searchPanelForm.getSelectedAgent().getHomePageURL());
        }
    }
}
