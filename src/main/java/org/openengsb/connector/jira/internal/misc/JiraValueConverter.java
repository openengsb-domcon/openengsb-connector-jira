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

import org.openengsb.domain.issue.Field;
import org.openengsb.domain.issue.IssueAttribute;
import org.openengsb.domain.issue.Priority;
import org.openengsb.domain.issue.Status;
import org.openengsb.domain.issue.Type;

/**
 *
 */
public final class JiraValueConverter {


    private JiraValueConverter() {

    }

    public static String convert(IssueAttribute type) {
        if (type.getClass().equals(Priority.class)) {
            return PriorityConverter.fromIssuePriority((Priority) type);
        }
        if (type.getClass().equals(Status.class)) {
            return StatusConverter.fromIssueStatus((Status) type);
        }
        if (type.getClass().equals(Type.class)) {
            return TypeConverter.fromIssueType((Type) type);
        }
        if (type.getClass().equals(Field.class)) {
            return FieldConverter.fromIssueField((Field) type);
        }
        return null;
    }

    public static String convert(String type) {
        type = type.toUpperCase();
        try {
            return convert(Field.valueOf(type));
        } catch (IllegalArgumentException ignore) { //ignore
        }
        try {
            return convert(Type.valueOf(type));
        } catch (IllegalArgumentException ignore) { //ignore
        }
        try {
            return convert(Priority.valueOf(type));
        } catch (IllegalArgumentException ignore) { //ignore
        }
        try {
            return convert(Status.valueOf(type));
        } catch (IllegalArgumentException ignore) { //ignore
        }
        return null;
    }

}
