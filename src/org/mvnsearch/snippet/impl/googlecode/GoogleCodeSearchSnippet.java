package org.mvnsearch.snippet.impl.googlecode;

import org.mvnsearch.snippet.Snippet;
import org.mvnsearch.snippet.HttpClientManager;

/**
 * google code search snippet
 *
 * @author linux_china@hotmail.com
 */
public class GoogleCodeSearchSnippet extends Snippet {
    /**
     * construct empty snippet
     */
    public GoogleCodeSearchSnippet() {

    }

    /**
     * construct google code search snippet
     *
     * @param id    id
     * @param title title
     */
    public GoogleCodeSearchSnippet(String id, String title) {
        setId(id);
        setDetailUrl(id);
        setTitle(title);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
        setDetailUrl(id);
    }

    @Override
    public String getCode() {
        if (super.getCode() == null)
            parseCode();
        return super.getCode();
    }

    /**
     * parse code
     */
    private void parseCode() {
        try {
            String pageContent = HttpClientManager.fetchUrlContent(getId());
            int start = pageContent.indexOf("<div id=\"code\">") + 15;
            int end = pageContent.indexOf("</div>", start);
            String code = pageContent.substring(start, end);
            setCode(code.replaceAll("</?\\w+((\\s+\\w+(\\s*=\\s*(?:\".*?\"|'.*?'|[^'\">\\s]+))?)+\\s*|\\s*)/?>", ""));
        } catch (Exception e) {

        }
    }

    @Override
    public String getLanguage() {
        return "java";
    }
}
