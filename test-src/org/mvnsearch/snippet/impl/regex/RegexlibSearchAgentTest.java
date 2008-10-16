package org.mvnsearch.snippet.impl.regex;

import junit.framework.TestCase;
import org.mvnsearch.snippet.Snippet;

import java.util.List;

/**
 * test case for regexlib search
 *
 * @author linux_china@hotmail.com
 */
public class RegexlibSearchAgentTest extends TestCase {
    private RegexlibSearchAgent agent = new RegexlibSearchAgent();

    /**
     * query test method
     *
     * @throws Exception exception
     */
    public void testQuery() throws Exception {
        String[] keyword = new String[]{"email", "validator"};
        List<Snippet> snippets = agent.query(keyword);
        for (Snippet snippet : snippets) {
            System.out.println(snippet.getTitle());
        }
    }
}
