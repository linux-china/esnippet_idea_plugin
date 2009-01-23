package org.mvnsearch.snippet.impl.dzone;

import junit.framework.TestCase;
import org.mvnsearch.snippet.Snippet;

import java.util.List;

/**
 * test case for dzone agent
 *
 * @author linux_china@hotmail.com
 */
public class DzoneSnippetSearchAgentTest extends TestCase {
    DzoneSnippetSearchAgent agent = new DzoneSnippetSearchAgent();

    public void testQuery() {
        String[] keywords = new String[]{"mail"};
        List<Snippet> snippets = agent.query(keywords);
        for (Snippet snippet : snippets) {
            System.out.println(snippet.getTitle());
        }
    }
}
