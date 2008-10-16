package org.mvnsearch.snippet.plugin.ui.tree;

import org.mvnsearch.snippet.SnippetSearchAgent;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Anki R Nelaturu
 */
public class SearchAgentNode extends DefaultMutableTreeNode {
    private SnippetSearchAgent agent;

    public SearchAgentNode(SnippetSearchAgent agent, int matchesCount) {
        super(agent.getDisplayName() + " [ " + matchesCount + " Matche(s) ]");
        this.agent = agent;
    }

    public SnippetSearchAgent getAgent() {
        return agent;
    }
}
