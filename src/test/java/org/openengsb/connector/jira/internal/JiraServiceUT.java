/**
 * Licensed to the Austrian Association for Software Tool Integration (AASTI)
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. The AASTI licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openengsb.connector.jira.internal;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openengsb.core.api.ekb.PersistInterface;
import org.openengsb.core.common.util.ModelUtils;
import org.openengsb.domain.issue.Field;
import org.openengsb.domain.issue.Issue;
import org.openengsb.domain.issue.IssueAttribute;
import org.openengsb.domain.issue.Priority;
import org.openengsb.domain.issue.Status;
import org.openengsb.domain.issue.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JiraServiceUT {
    private static final Logger LOGGER = LoggerFactory.getLogger(JiraServiceUT.class);
    // Login details
    static final String LOGIN_NAME = "admin";
    static final String LOGIN_PASSWORD = "hallo123";

    // Constants for issue creation
    static final String PROJECT_KEY = "HH";

    // Constant for get filter
    private static JiraService jiraClient;
    private static JiraSOAPSession jiraSoapSession;

    /**
     * testing server provided by jira
     */
    private static String baseUrl = "http://localhost:8080/rpc/soap/jirasoapservice-v2?wsdl";
    private static String issueId;

    @BeforeClass
    public static void setUpClass() throws Exception {
        jiraSoapSession = new JiraSOAPSession(baseUrl);
        jiraClient = new JiraService("id");
        jiraClient.setSoapSession(jiraSoapSession);
        jiraClient.setProjectKey(PROJECT_KEY);
        jiraClient.setJiraPassword(LOGIN_PASSWORD);
        jiraClient.setJiraUser(LOGIN_NAME);
        PersistInterface persistInterface = mock(PersistInterface.class);
        jiraClient.setPersistInterface(persistInterface);
        
        testCreateIssue_shouldCreateIssue();
    }

    public static void testCreateIssue_shouldCreateIssue() {
        LOGGER.debug("test to create an issue");
        Issue engsbIssue = createIssue();
        issueId = jiraClient.createIssue(engsbIssue);
        assertNotNull(issueId);
    }

    @Test
    public void testAddComment_shouldAddComment() {
        LOGGER.debug("test to add a command to an issue");
        jiraClient.addComment(issueId, "comment");
    }
    
    @Test
    public void testUpdateIssue_shouldUpdateIssue() {
        HashMap<IssueAttribute, String> changes = new HashMap<IssueAttribute, String>();
        changes.put(Field.COMPONENT, "updComponent");
        changes.put(Field.DESCRIPTION, "updated Description");
        jiraClient.updateIssue(issueId, "commentTest", changes);
    }

    @Test
    public void testMoveAllIssuesFromOneReleaseToAnotherRelease_shouldMoveIssues() {
        jiraClient.moveIssuesFromReleaseToRelease("13203", "11410");
    }

    @Ignore("user has no rights to close a release")
    @Test
    public void testcloseRelease_shouldCloseRelease() {
        jiraClient.closeRelease("Version 2.0");
    }

    @Test
    public void testGenerateReleaseReport_shouldGenerateReport() {
        assertNotNull(jiraClient.generateReleaseReport("Version 2.0"));
    }

    private static Issue createIssue() {
        Issue issue = ModelUtils.createEmptyModelObject(Issue.class);
        issue.setSummary("summary");
        issue.setDescription("description");
        issue.setReporter(LOGIN_NAME);
        issue.setOwner(LOGIN_NAME);
        issue.setPriority(Priority.NONE);
        issue.setStatus(Status.NEW);
        issue.setDueVersion("versionID1");
        issue.setType(Type.BUG);
        return issue;
    }
}
