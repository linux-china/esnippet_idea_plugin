package org.mvnsearch.snippet;

import java.util.Collection;
import java.util.List;

/**
 * snippet search agent
 *
 * @author Anki R Nelaturu
 */
public abstract class SnippetSearchAgent {
    /**
     * agent id
     *
     * @return agent id
     */
    public abstract String getId();

    /**
     * Just a convenient method to display the source of the search in the results tree of iSnippet.
     *
     * @return brief text about the source from where the results were found
     */
    public abstract String getDisplayName();

    /**
     * The actual web URL
     *
     * @return url from where the results were found
     */
    public abstract String getHomePageURL();

    /**
     * Detailed description of the site providing these snippets.
     *
     * @return agent description
     */

    public abstract String getDescription();

    /**
     * Performs the search for given keywords
     *
     * @param keywords - Array of keywords
     * @return Matching araaylist of the ISnippets
     */
    public abstract List<Snippet> query(String[] keywords);

    /**
     * find snippets according to snippets
     *
     * @param categoryId category id
     * @return snippet list
     */
    public abstract List<Snippet> findSnippetsByCategory(String categoryId);

    /**
     * find recent added snippets for RSS
     *
     * @return snippets
     */
    public abstract List<Snippet> findRecentAddedSnippets();

    /**
     * find snippet by id
     *
     * @param snippetId snippet id
     * @return snippet object
     */
    public abstract Snippet findById(String snippetId);

    /**
     * update snippet category
     *
     * @param snippet snippet object
     * @return result
     */
    public abstract boolean updateSnippet(Snippet snippet);

    /**
     * find snippet comment
     *
     * @param snippetId snippet id
     * @return comment list
     */
    public abstract List<Comment> findComments(String snippetId);

    /**
     * add comment
     *
     * @param snippetId snippet id
     * @param comment   comment
     * @return result
     */
    public abstract boolean addSnippetComment(String snippetId, Comment comment);

    /**
     * get category list
     *
     * @return category list
     */
    public abstract Collection<Category> findRootCategories();

    /**
     * get content type for code     text/plain, text/html
     *
     * @return content type for code
     */
    public abstract String getCodeContentType();

    @Override
    public String toString() {
        return "[" + getId() + "]" + getDisplayName();
    }

    /**
     * snippet can be updated?
     *
     * @return updated mark
     */
    public boolean isSnippetUpdated() {
        return false;
    }

    /**
     * construct new snippet
     *
     * @return new snippet
     */
    public Snippet constructNewSnippet() {
        return new Snippet();
    }
}
