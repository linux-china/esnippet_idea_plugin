/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mvnsearch.snippet.impl.googlecode;

import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.mvnsearch.snippet.Category;
import org.mvnsearch.snippet.Comment;
import org.mvnsearch.snippet.Snippet;
import org.mvnsearch.snippet.SnippetSearchAgent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * google java code search agent
 *
 * @author linux_china@hotmail.com
 */
public class GoogleJavaCodeSearchAgent extends SnippetSearchAgent {
    /**
     * agent id
     *
     * @return agent id
     */
    public String getId() {
        return "google";
    }

    /**
     * Just a convenient method to display the source of the search in the results tree of iSnippet.
     *
     * @return brief text about the source from where the results were found
     */
    public String getDisplayName() {
        return "Google Java Code Search Agent";
    }

    /**
     * The actual web URL
     *
     * @return url from where the results were found
     */
    public String getHomePageURL() {
        return "http://www.google.com/codesearch";
    }

    /**
     * Detailed description of the site providing these snippets.
     *
     * @return agent description
     */
    public String getDescription() {
        return "Search code with Google Code Search";
    }

    /**
     * Performs the search for given keywords
     *
     * @param keywords - Array of keywords
     * @return Matching araaylist of the ISnippets
     */
    public List<Snippet> query(String[] keywords) {
        List<Snippet> snippets = new ArrayList<Snippet>();
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            URL feedUrl = new URL("http://www.google.com/codesearch/feeds/search?max-results=20&as_lang=java&q=" + StringUtils.join(keywords, "%20"));
            Document document = saxBuilder.build(feedUrl);
            Element feedElement = document.getRootElement();
            Namespace namespace = feedElement.getNamespace();
            List<Element> entryElements = feedElement.getChildren("entry", namespace);
            for (Element entryElement : entryElements) {
                GoogleCodeSearchSnippet snippet = new GoogleCodeSearchSnippet();
                snippet.setId(entryElement.getChildText("id", namespace));
                snippet.setTitle(entryElement.getChildText("title", namespace));
                snippet.setAuthor(entryElement.getChild("author", namespace).getChildText("name", namespace));
                snippets.add(snippet);
            }
        } catch (Exception e) {

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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * find recent added snippets for RSS
     *
     * @return snippets
     */
    public List<Snippet> findRecentAddedSnippets() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * find snippet by id
     *
     * @param snippetId snippet id
     * @return snippet object
     */
    public Snippet findById(String snippetId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * update snippet category
     *
     * @param snippet snippet object
     * @return result
     */
    public boolean updateSnippet(Snippet snippet) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * find snippet comment
     *
     * @param snippetId snippet id
     * @return comment list
     */
    public List<Comment> findComments(String snippetId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * add comment
     *
     * @param snippetId snippet id
     * @param comment   comment
     * @return result
     */
    public boolean addSnippetComment(String snippetId, Comment comment) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * get category list
     *
     * @return category list
     */
    public Collection<Category> findRootCategories() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * get content type for code     text/plain, text/html
     *
     * @return content type for code
     */
    public String getCodeContentType() {
        return "text/java";
    }

}
