package org.mvnsearch.snippet.plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

/**
 * snippet plugin description
 *
 * @author Anki R Nelaturu
 */
public class AboutAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Messages.showInfoMessage("<html><body><center><h2>eSnippet Pro</h2>" +
                "<br>Your search agent to find the right code sample/snippet and main site is http://snippet.mvnsearch.org</center></body></html>", "eSnippet");
    }
}
