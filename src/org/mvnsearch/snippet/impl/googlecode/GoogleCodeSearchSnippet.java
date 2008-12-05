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
