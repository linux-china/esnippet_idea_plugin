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

package org.mvnsearch.snippet.impl.mvnsearch;

import org.mvnsearch.snippet.Category;

import java.util.List;
import java.util.Map;

/**
 * snippet service supplied by repository
 *
 * @author linux_china@hotmail.com
 */
public interface SnippetService {
    /**
     * render template into code
     *
     * @param mnemonic    mnemonic
     * @param packageName package name
     * @param fileName    file name
     * @param author      author name
     * @return rendered code
     */
    public String renderTemplate(String mnemonic, String packageName, String fileName, String author);

    /**
     * find snippet by mnemonic
     *
     * @param mnemonic mnemonic
     * @return snippet object
     */
    public Map<String, String> findSnippetByMnemonic(String mnemonic);

    /**
     * find mnemonic list according to prefix
     *
     * @param prefix prefix
     * @return mnemonic list, max size is 100
     */
    public List<String> findMnemonicList(String prefix);

    /**
     * find mnemonic list according to prefix
     *
     * @param prefix prefix
     * @return mnemonic list, max size is 100
     */
    public List<String> findMnemonicListWithName(String prefix);
    
    /**
     * @param mnemonicPrefix mnemonic prefix
     * @return snippet list
     */
    public List<Map<String, String>> findSnippetsByMnemonic(String mnemonicPrefix);

    /**
     * @param keyword key word
     * @return snippet list
     */
    public List<Map<String, String>> findSnippetsByWord(String keyword);

    /**
     * find snippet by id
     *
     * @param id snippet id
     * @return snippet object
     */
    public Map<String, String> findMapById(Integer id);

    /**
     * find root category
     *
     * @return category list
     */
    public List<Map<String, String>> findAllCategories();

    /**
     * update snippet
     *
     * @param snippet snippet object
     * @return snippet id
     */
    public Integer updateSnippet(Map<String, String> snippet);

    /**
     * find map in category
     *
     * @param categoryId category id
     * @return snippet service
     */
    public List<Map<String, String>> findSnippetsInCategory(Integer categoryId);

    /**
     * find recent added snippets
     *
     * @return snippet list
     */
    public List<Map<String, String>> findRecentAddedSnippets();

    /**
     * add snippet
     *
     * @param snippetId snippet id
     * @param comment   snippet comment
     */
    public void addSnippetComment(Integer snippetId, Map<String, String> comment);

    /**
     * list snippet comment
     *
     * @param snippetId snippet id
     * @return comment list
     */
    public List<Map<String, String>> findSnippetComments(Integer snippetId);
}
