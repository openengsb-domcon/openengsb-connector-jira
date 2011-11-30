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

import java.util.Map;

import org.openengsb.core.api.Connector;
import org.openengsb.core.common.AbstractConnectorInstanceFactory;
import org.openengsb.domain.issue.IssueDomainEvents;

public class JiraServiceInstanceFactory extends AbstractConnectorInstanceFactory<JiraService> {
    
    private IssueDomainEvents issueEvents;

    @Override
    public Connector createNewInstance(String id) {
        JiraService service = new JiraService(id);
        service.setIssueEvents(issueEvents);
        return service;
    }

    @Override
    public void doApplyAttributes(JiraService instance, Map<String, String> attributes) {
        instance.setJiraUser(attributes.get("jira.user"));
        instance.setJiraPassword(attributes.get("jira.password"));

        instance.getSoapSession().setJiraURI(attributes.get("jira.uri"));
        instance.setProjectKey(attributes.get("jira.project"));
    }
    
    public void setIssueEvents(IssueDomainEvents issueEvents) {
        this.issueEvents = issueEvents;
    }
}
