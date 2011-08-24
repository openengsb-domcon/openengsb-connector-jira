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

package org.openengsb.connector.jira.internal.misc;

import java.util.HashMap;

import org.openengsb.domain.issue.models.Field;

/**
 * field converter from OpenEngSB field to Jira field,
 * see http://docs.atlassian.com/jira/latest/constant-values.html
 */
public final class FieldConverter {

    private static HashMap<Field, String> issueMap;

    private FieldConverter() {

    }

    public static String fromIssueField(Field issueField) {
        if (issueMap == null) {
            initMap();
        }
        return issueMap.get(issueField);
    }

    private static void initMap() {
        issueMap = new HashMap<Field, String>();
        issueMap.put(Field.SUMMARY, "summary");
        issueMap.put(Field.DESCRIPTION, "description");
        issueMap.put(Field.OWNER, "owner");
        issueMap.put(Field.REPORTER, "reporter");
        issueMap.put(Field.STATUS, "status");
        issueMap.put(Field.COMPONENT, "components");
    }

}
