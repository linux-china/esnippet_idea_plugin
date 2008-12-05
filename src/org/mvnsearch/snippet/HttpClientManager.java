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

package org.mvnsearch.snippet;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.IOException;

/**
 * http client manager
 *
 * @author linux_china@hotmail.com
 */
public class HttpClientManager {

    /**
     * fetch url content
     *
     * @param url url
     * @return url内容
     * @throws java.io.IOException io exception
     */
    public static String fetchUrlContent(String url) throws IOException {
        //构建http client
        HttpClient httpClient = createHttpClient();
        GetMethod getMethod = new GetMethod(url);
        //执行调用
        httpClient.executeMethod(getMethod);
        //获取反馈
        return getMethod.getResponseBodyAsString();
    }

    /**
     * create http client instance
     *
     * @return HttpClient object
     */
    private static HttpClient createHttpClient() {
        HttpClient client = new HttpClient();     //HttpClient create
        HttpClientParams clientParams = client.getParams();
        clientParams.setParameter("http.socket.timeout", 10000); //10 seconds for socket waiting
        clientParams.setParameter("http.connection.timeout", 10000); //10 seconds http connection creation
        clientParams.setParameter("http.connection-manager.timeout", 3000L); //3 seconds waiting to get connection from http connection manager
        clientParams.setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler()); // if failed, try 3
        return client;
    }
}
