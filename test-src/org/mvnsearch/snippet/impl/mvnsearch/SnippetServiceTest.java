package org.mvnsearch.snippet.impl.mvnsearch;

import junit.framework.TestCase;
import com.caucho.hessian.client.HessianProxyFactory;

import java.util.List;
import java.util.Map;

/**
 * snipet service test case
 *
 * @author linux_china@hotmail.com
 */
public class SnippetServiceTest extends TestCase {
    private String serviceUrl = "http://snippet.mvnsearch.org/remoting/snippetService";  //hessian service url
    private SnippetService snippetService;

    @Override
    protected void setUp() throws Exception {
        HessianProxyFactory factory = new HessianProxyFactory();
        snippetService = (SnippetService) factory.create(SnippetService.class, serviceUrl, this.getClass().getClassLoader());
    }

    public void testFindMnemonicList() {
        List<String> names = snippetService.findMnemonicList("java");
        for (String name : names) {
            System.out.println(name);
        }
    }

    public void testFindSnippets() {
        // Add your code here
    }

    public void testFindSnippetsByMnemonic() {
        // Add your code here
    }

    public void testFindSnippetsByKeyword() {
        List<Map<String, String>> snippetList = snippetService.findSnippetsByWord("java");
        for (Map<String, String> stringStringMap : snippetList) {
            System.out.println(stringStringMap.get("name"));
        }

    }

    public void testFindSnippetById() throws Exception {
        Integer id = 1;
        Map<String, String> snippet = snippetService.findMapById(id);
        System.out.println(snippet.get("name"));
    }

    public void testFindRootCategories() {
        // Add your code here
    }

    public void testUpdateSnippet() {
        // Add your code here
    }
}
