package org.mvnsearch.snippet.impl.googlecode;

import junit.framework.TestCase;
import org.mvnsearch.snippet.Snippet;

import java.util.List;

/**
 * test case for google code search
 *
 * @author linux_china@hotmail.com
 */
public class GoogleJavaCodeSearchAgentTest extends TestCase {
    GoogleJavaCodeSearchAgent agent = new GoogleJavaCodeSearchAgent();

    /**
     * query test method
     *
     * @throws Exception exception
     */
    public void testQuery() throws Exception {
        List<Snippet> snippets = agent.query(new String[]{"jdbc"});
        System.out.println(snippets.get(0).getAuthor());
    }
}
