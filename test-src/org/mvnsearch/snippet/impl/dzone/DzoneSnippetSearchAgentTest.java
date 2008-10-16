package org.mvnsearch.snippet.impl.dzone;

import junit.framework.TestCase;

/**
 * test case for dzone agent
 *
 * @author linux_china@hotmail.com
 */
public class DzoneSnippetSearchAgentTest extends TestCase {
    DzoneSnippetSearchAgent agent = new DzoneSnippetSearchAgent();

    public void testQuery() {
        String[] keywords = new String[]{"java"};
        agent.query(keywords);
    }
}
