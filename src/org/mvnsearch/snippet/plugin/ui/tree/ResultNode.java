package org.mvnsearch.snippet.plugin.ui.tree;

import org.mvnsearch.snippet.Snippet;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * result code with snippet included
 *
 * @author Anki R Nelaturu
 */
public class ResultNode extends DefaultMutableTreeNode {
    private Snippet snippet;

    public ResultNode(Snippet snippet) {
        super(snippet.getTitle());
        this.snippet = snippet;
    }

    public Snippet getSnippet() {
        return snippet;
    }
}
