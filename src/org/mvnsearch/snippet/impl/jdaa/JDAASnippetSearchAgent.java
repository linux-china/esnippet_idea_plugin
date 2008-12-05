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

package org.mvnsearch.snippet.impl.jdaa;

import org.mvnsearch.snippet.Category;
import org.mvnsearch.snippet.Comment;
import org.mvnsearch.snippet.Snippet;
import org.mvnsearch.snippet.SnippetSearchAgent;

import java.util.*;

/**
 * Java developer Almanac 1.4 snippet search agent
 *
 * @author Anki R Nelaturu
 */
public class JDAASnippetSearchAgent extends SnippetSearchAgent {
    private List<JDAASnippet> snippetList = new ArrayList<JDAASnippet>(); //snippet list
    private Map<String, Category> categoryMap = new HashMap<String, Category>(); //category map

    /**
     * java Almanac search agent
     *
     * @throws Exception exception
     */
    public JDAASnippetSearchAgent() throws Exception {
        Properties p = new Properties();
        p.load(JDAASnippetSearchAgent.class.getResourceAsStream("/org/mvnsearch/snippet/impl/jdaa/snippets.properties"));
        int count = Integer.parseInt(p.getProperty("snippets.count"));
        for (int i = 0; i < count; i++) {
            String href = p.getProperty("snippet.href." + i);
            JDAASnippet jdaSnippet = new JDAASnippet(i, href, p.getProperty("snippet.description." + i));
            snippetList.add(jdaSnippet);
            //fill category
            if (href.contains("/")) {
                String javaPackage = href.substring(0, href.indexOf("/"));
                if (!categoryMap.containsKey(javaPackage)) {
                    categoryMap.put(javaPackage, new Category(javaPackage, javaPackage));
                }
            }
        }
    }

    /**
     * get agent description
     *
     * @return description
     */
    public String getDescription() {
        return "This website supplements the book The Java Developers Almanac 1.4. All the code examples from the book are made available here for you to copy and paste into your programs.";
    }

    /**
     * get home page url
     *
     * @return home page url
     */
    public String getHomePageURL() {
        return "http://javaalmanac.com/";
    }

    /**
     * get display name
     *
     * @return display name
     */
    public String getDisplayName() {
        return "The Java Developers Almanac 1.4";
    }

    /**
     * get agent id
     *
     * @return agent id
     */
    public String getId() {
        return "Almanac";
    }

    /**
     * Performs the search for given keywords
     *
     * @param keywords - Array of keywords
     * @return Matching araaylist of the ISnippets
     */
    public List<Snippet> query(String[] keywords) {
        List<Snippet> matchingList = new ArrayList<Snippet>();
        for (JDAASnippet url : snippetList) {
            if (url.matches(keywords)) {
                matchingList.add(url);
            }
        }
        return matchingList;
    }

    /**
     * find snippets according to snippets
     *
     * @param categoryId category id
     * @return snippet list
     */
    public List<Snippet> findSnippetsByCategory(String categoryId) {
        return query(new String[]{categoryId + "/"});
    }

    /**
     * find recent added snippets for RSS
     *
     * @return snippets
     */
    public List<Snippet> findRecentAddedSnippets() {
        return null;
    }

    /**
     * find snippet by id
     *
     * @param snippetId snippet id
     * @return snippet object
     */
    public Snippet findById(String snippetId) {
        return null;
    }

    /**
     * update snippet category
     *
     * @param snippet snippet object
     * @return result
     */
    public boolean updateSnippet(Snippet snippet) {
        return false;
    }

    /**
     * find snippet comment
     *
     * @param snippetId snippet id
     * @return comment list
     */
    public List<Comment> findComments(String snippetId) {
        return null;
    }

    /**
     * add snippet comment
     *
     * @param snippetId snippet id
     * @param comment   comment
     * @return result
     */
    public boolean addSnippetComment(String snippetId, Comment comment) {
        return false;
    }

    /**
     * get category list
     *
     * @return category list
     */
    public Collection<Category> findRootCategories() {
        return categoryMap.values();
    }

    /**
     * get code content type
     *
     * @return code content type
     */
    public String getCodeContentType() {
        return "text/java";
    }

}
