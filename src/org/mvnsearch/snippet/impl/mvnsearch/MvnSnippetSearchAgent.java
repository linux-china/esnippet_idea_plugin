package org.mvnsearch.snippet.impl.mvnsearch;

import com.caucho.hessian.client.HessianProxyFactory;
import org.apache.commons.lang.StringUtils;
import org.mvnsearch.snippet.Category;
import org.mvnsearch.snippet.Comment;
import org.mvnsearch.snippet.Snippet;
import org.mvnsearch.snippet.SnippetSearchAgent;

import java.util.*;

/**
 * mvnsearch search agent
 *
 * @author linux_china@hotmail.com
 */
public class MvnSnippetSearchAgent extends SnippetSearchAgent {
    private static String serviceUrl = "http://snippet.mvnsearch.org/remoting/snippetService";  //hessian service url
    private SnippetService snippetService;  //snippet service to mvnsearch site
    private List<Category> categories;
    private Map<String, String> languagesData = new HashMap<String, String>();

    /**
     * construct agent
     *
     * @throws Exception hessian connection exception
     */
    public MvnSnippetSearchAgent() throws Exception {
        languagesData.put("100", "Java");
        languagesData.put("101", "Ruby");
        languagesData.put("102", "PHP");
        languagesData.put("103", "C++");
        languagesData.put("104", "C#");
        languagesData.put("105", "CSS");
        languagesData.put("106", "Python");
        languagesData.put("107", "SQL");
        languagesData.put("108", "HTML");
        languagesData.put("109", "XML");
        languagesData.put("110", "Javascript");
        languagesData.put("111", "Text");
        HessianProxyFactory factory = new HessianProxyFactory();
        snippetService = (SnippetService) factory.create(SnippetService.class, serviceUrl, this.getClass().getClassLoader());
    }

    /**
     * get language data
     *
     * @return language data
     */
    public Map<String, String> getLanguagesData() {
        return languagesData;
    }

    /**
     * agent id
     *
     * @return agent id
     */
    public String getId() {
        return "mvs";
    }

    /**
     * get display name for agent
     *
     * @return brief text about the source from where the results were found
     */
    public String getDisplayName() {
        return "mvnsearch snippet repository";
    }

    /**
     * The actual web URL
     *
     * @return url from where the results were found
     */
    public String getHomePageURL() {
        return "http://snippet.mvnsearch.org";
    }

    /**
     * Detailed description of the site providing these snippets.
     *
     * @return agent description
     */
    public String getDescription() {
        return "mvnsearch snippet repository";
    }

    /**
     * Performs the search for given keywords
     *
     * @param keywords - Array of keywords
     * @return Matching araaylist of the ISnippets
     */
    public List<Snippet> query(String[] keywords) {
        List<Snippet> snippets = new ArrayList<Snippet>();
        List<Map<String, String>> infoList = snippetService.findSnippetsByWord(StringUtils.join(keywords, " "));
        for (Map<String, String> info : infoList) {
            snippets.add(convertMapToSnippet(info));
        }
        return snippets;
    }

    /**
     * find snippets according to snippets
     *
     * @param categoryId category id
     * @return snippet list
     */
    public List<Snippet> findSnippetsByCategory(String categoryId) {
        List<Map<String, String>> infoList = snippetService.findSnippetsInCategory(Integer.valueOf(categoryId));
        return convertMapToSnippet(infoList);
    }

    /**
     * find recent added snippets for RSS
     *
     * @return snippets
     */
    public List<Snippet> findRecentAddedSnippets() {
        return convertMapToSnippet(snippetService.findRecentAddedSnippets());
    }

    /**
     * find snippet by id
     *
     * @param snippetId snippet id
     * @return snippet object
     */
    public Snippet findById(String snippetId) {
        Map<String, String> info = snippetService.findMapById(Integer.valueOf(snippetId));
        if (info != null && !info.isEmpty()) {
            return convertMapToSnippet(info);
        }
        return null;
    }

    /**
     * update snippet category
     *
     * @param snippet snippet object
     * @return result
     */
    public boolean updateSnippet(Snippet snippet) {
        MvnSnippet mvnSnippet = (MvnSnippet) snippet;
        Map<String, String> info = new HashMap<String, String>();
        if (snippet.getId() != null) {
            info.put("id", snippet.getId());
        }
        info.put("categoryId", mvnSnippet.getCategoryId());
        info.put("mnemonic", mvnSnippet.getMnemonic());
        info.put("name", mvnSnippet.getTitle());
        info.put("author", mvnSnippet.getAuthor());
        info.put("keywords", mvnSnippet.getKeywords());
        info.put("code", snippet.getCode());
        info.put("description", mvnSnippet.getRawDescription());
        String language = snippet.getLanguage();
        for (Map.Entry<String, String> entry : languagesData.entrySet()) {
            if (entry.getValue().equals(language)) {
                info.put("language", entry.getKey());
            }
        }
        Integer snippetId = snippetService.updateSnippet(info);
        snippet.setId(String.valueOf(snippetId));
        return true;
    }

    /**
     * find snippet comment
     *
     * @param snippetId snippet id
     * @return comment list
     */
    public List<Comment> findComments(String snippetId) {
        List<Map<String, String>> infoList = snippetService.findSnippetComments(Integer.valueOf(snippetId));
        List<Comment> comments = new ArrayList<Comment>();
        for (Map<String, String> info : infoList) {
            comments.add(convertMapToComment(info));
        }
        return comments;
    }

    /**
     * add comment
     *
     * @param comment comment
     * @return result
     */
    public boolean addSnippetComment(String snippetId, Comment comment) {
        Map<String, String> info = new HashMap<String, String>();
        info.put("subject", comment.getSubject());
        info.put("content", comment.getContent());
        info.put("commentator", comment.getAuthor());
        snippetService.addSnippetComment(Integer.valueOf(snippetId), info);
        return true;
    }

    /**
     * get all category list
     *
     * @return category list
     */
    public Collection<Category> findRootCategories() {
//        if (categories != null) return categories;
        this.categories = new ArrayList<Category>();
        List<Map<String, String>> infoList = snippetService.findAllCategories();
        for (Map<String, String> info : infoList) {
            Category category = new Category();
            category.setId(info.get("id"));
            category.setName(info.get("name"));
            categories.add(category);
        }
        return categories;
    }

    /**
     * get code content type
     *
     * @return code content type
     */
    public String getCodeContentType() {
        return "text/plain";
    }

    /**
     * convert map to snippet
     *
     * @param infoList map list
     * @return snippet list
     */
    public List<Snippet> convertMapToSnippet(List<Map<String, String>> infoList) {
        List<Snippet> snippets = new ArrayList<Snippet>();
        for (Map<String, String> info : infoList) {
            snippets.add(convertMapToSnippet(info));
        }
        return snippets;
    }

    /**
     * convert map to comment
     *
     * @param info info
     * @return comment obejct
     */
    private Comment convertMapToComment(Map<String, String> info) {
        Comment comment = new Comment();
        comment.setSubject(info.get("subject"));
        comment.setContent(info.get("content"));
        comment.setAuthor(info.get("commentator"));
        return comment;
    }

    /**
     * convert map info to object
     *
     * @param info info map
     * @return snippet object
     */
    private MvnSnippet convertMapToSnippet(Map<String, String> info) {
        MvnSnippet snippet = new MvnSnippet();
        snippet.setId(info.get("id"));
        snippet.setCategoryId(info.get("categoryId"));
        snippet.setTitle(info.get("name"));
        String language = info.get("language");
        if (language != null) {
            snippet.setLanguage(languagesData.get(language));
        }
        snippet.setCode(info.get("code"));
        snippet.setAuthor(info.get("author"));
        snippet.setKeywords(info.get("keywords"));
        snippet.setMnemonic(info.get("mnemonic"));
        snippet.setRawDescription(info.get("description"));
        return snippet;
    }

    /**
     * snippet can be updated?
     *
     * @return updated mark
     */
    @Override
    public boolean isSnippetUpdated() {
        return true;
    }

    /**
     * construct new snippet
     *
     * @return new snippet
     */
    @Override
    public Snippet constructNewSnippet() {
        return new MvnSnippet();
    }
}
