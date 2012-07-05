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

import org.openengsb.core.api.ekb.EKBCommit;
import org.openengsb.core.api.ekb.PersistInterface;
import org.openengsb.domain.issue.Issue;

/**
 * Class that extracts the commiting of models to the persist interface from the jira connector. In that way easier
 * testing is possible.
 */
public class JiraCommitHandler {
    private PersistInterface persistInterface;

    /**
     * Commits a new issue to the persist interface. If the persist interface isn't set, the call is ignored.
     */
    public void commitInsertIssue(Issue issue, EKBCommit commit) {
        if (persistInterface != null) {
            commit.addInsert(issue);
            persistInterface.commit(commit);
        }
    }

    /**
     * Commits an updated issue to the persist interface. If the persist interface isn't set, the call is ignored.
     */
    public void commitUpdateIssue(Issue issue, EKBCommit commit) {
        if (persistInterface != null) {
            commit.addUpdate(issue);
            persistInterface.commit(commit);
        }
    }

    public void setPersistInterface(PersistInterface persistInterface) {
        this.persistInterface = persistInterface;
    }
}
