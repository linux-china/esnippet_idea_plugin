package org.mvnsearch.snippet;

import com.intellij.openapi.ui.Messages;
import org.mvnsearch.snippet.impl.jdaa.JDAASnippetSearchAgent;
import org.mvnsearch.snippet.impl.mvnsearch.MvnSnippetSearchAgent;
import org.mvnsearch.snippet.impl.googlecode.GoogleJavaCodeSearchAgent;
import org.mvnsearch.snippet.impl.regex.RegexlibSearchAgent;
import org.mvnsearch.snippet.impl.dzone.DzoneSnippetSearchAgent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * snippet search agent factory
 *
 * @author Anki R Nelaturu
 */
public class SnippetSearchAgentsFactory {
    private Map<String, SnippetSearchAgent> searchAgents;   //all agents
    private static SnippetSearchAgentsFactory factory;  //singleton instance

    /**
     * get search agents factory
     *
     * @return singleton instance
     */
    public static SnippetSearchAgentsFactory getInstance() {
        if (factory == null) {
            factory = new SnippetSearchAgentsFactory();
        }
        return factory;
    }

    /**
     * register all agents
     */
    private SnippetSearchAgentsFactory() {
        searchAgents = new HashMap<String, SnippetSearchAgent>();
        try {
            JDAASnippetSearchAgent jdaSnippetSearchAgent = new JDAASnippetSearchAgent();
            searchAgents.put(jdaSnippetSearchAgent.getId(), jdaSnippetSearchAgent);
            MvnSnippetSearchAgent mvnAgent = new MvnSnippetSearchAgent();
            searchAgents.put(mvnAgent.getId(), mvnAgent);
            GoogleJavaCodeSearchAgent googleJavaAgent = new GoogleJavaCodeSearchAgent();
            searchAgents.put(googleJavaAgent.getId(), googleJavaAgent);
            RegexlibSearchAgent regexAgent = new RegexlibSearchAgent();
            searchAgents.put(regexAgent.getId(), regexAgent);
            DzoneSnippetSearchAgent dzoneAgent = new DzoneSnippetSearchAgent();
            searchAgents.put(dzoneAgent.getId(), dzoneAgent);
        } catch (Exception ex) {
            Messages.showErrorDialog(ex.getMessage(), "Failed to create search agents");
        }
    }

    /**
     * agent list
     *
     * @return agent list
     */
    public Collection<SnippetSearchAgent> getSnippetManagers() {
        return searchAgents.values();
    }

    /**
     * agent list
     *
     * @param id agent id
     * @return agent object
     */
    public SnippetSearchAgent findAgent(String id) {
        return searchAgents.get(id);
    }

}
