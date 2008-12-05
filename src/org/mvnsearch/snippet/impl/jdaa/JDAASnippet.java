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

import org.mvnsearch.snippet.Snippet;
import org.mvnsearch.snippet.HttpClientManager;

/**
 * Java developer Almanac 1.4 snippet
 *
 * @author Anki R Nelaturu
 */
public class JDAASnippet extends Snippet {
    public static final String MAIN_URL = "http://javaalmanac.com/egs/";
    private String href;
    private String description;
    private String html;
    private String descriptionLower;

    /**
     * construct snippet
     *
     * @param id          id
     * @param href        href in property file
     * @param description description
     */
    public JDAASnippet(int id, String href, String description) {
        setId(String.valueOf(id));
        setTitle(description);
        this.href = href.toLowerCase();
        this.description = description;
        this.descriptionLower = description.toLowerCase();
        setDetailUrl(MAIN_URL + href);
    }

    /**
     * match the key word
     *
     * @param keywords key word
     * @return matched mark
     */
    public boolean matches(String[] keywords) {
        for (String keyword : keywords) {
            if (href.indexOf(keyword.toLowerCase()) != -1 || descriptionLower.indexOf(keyword.toLowerCase()) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * prepare data
     */
    private void prepareData() {
        if (html == null) { //html empty, visit url to fetch content
            try {
                html = HttpClientManager.fetchUrlContent(getDetailUrl());
                int index = html.indexOf(description + "</h3>");
                html = "<html><body><h3>" + html.substring(index);
                index = html.indexOf("<P><table BORDER=\"0\" CELLPADDING=\"2\" CELLSPACING=\"0\" width=\"600\">");
                if (index == -1) {
                    index = html.indexOf("</pre>", index) + 6;
                }
                html = html.substring(0, index);
                html += "</body></html>";
                setDescription(html);
                parseCodeSnippets();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * parse snippet code, extract pre tag and
     */
    private void parseCodeSnippets() {
        int index = html.indexOf("<pre>");
        if (index == -1) {
            return;
        }
        int end = 0;
        StringBuilder builder = new StringBuilder();
        while (index != -1) {
            end = html.indexOf("</pre>", index);
            builder.append(html.substring(index, end + 6));
            index = html.indexOf("<pre>", end);
        }
        String code = builder.toString();
        //clean html code
        code = code.replaceAll("</?\\w+((\\s+\\w+(\\s*=\\s*(?:\".*?\"|'.*?'|[^'\">\\s]+))?)+\\s*|\\s*)/?>", "");
        setCode(code);
    }

    @Override
    public String getDescription() {
        prepareData();
        return super.getDescription();
    }

    @Override
    public String getCode() {
        prepareData();
        return super.getCode();
    }

    public String toString() {
        return description;
    }

    @Override
    public String getLanguage() {
        return "java";
    }

    /**
     * get content type for snippet
     *
     * @return content type
     */
    @Override
    public String getContentType() {
        return "text/java";
    }
}
