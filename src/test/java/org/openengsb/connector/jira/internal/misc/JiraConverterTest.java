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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;
import org.openengsb.domain.issue.models.Field;
import org.openengsb.domain.issue.models.Priority;
import org.openengsb.domain.issue.models.Status;
import org.openengsb.domain.issue.models.Type;

/**
 *
 */
public class JiraConverterTest {

    @Test
    public void testConvertAllPriorities() {
        assertThat(JiraValueConverter.convert(Priority.HIGH),
            is(PriorityConverter.fromIssuePriority(Priority.HIGH)));
        assertThat(JiraValueConverter.convert(Priority.IMMEDIATE),
            is(PriorityConverter.fromIssuePriority(Priority.IMMEDIATE)));
        assertThat(JiraValueConverter.convert(Priority.LOW),
            is(PriorityConverter.fromIssuePriority(Priority.LOW)));
        assertThat(JiraValueConverter.convert(Priority.NONE),
            is(PriorityConverter.fromIssuePriority(Priority.NONE)));
        assertThat(JiraValueConverter.convert(Priority.NORMAL),
            is(PriorityConverter.fromIssuePriority(Priority.NORMAL)));
        assertThat(JiraValueConverter.convert(Priority.URGEND),
            is(PriorityConverter.fromIssuePriority(Priority.URGEND)));
    }

    @Test
    public void testConvertAllStates() {
        assertThat(JiraValueConverter.convert(Status.UNASSIGNED),
            is(StatusConverter.fromIssueStatus(Status.UNASSIGNED)));
        assertThat(JiraValueConverter.convert(Status.CLOSED),
            is(StatusConverter.fromIssueStatus(Status.CLOSED)));
        assertThat(JiraValueConverter.convert(Status.NEW), is(StatusConverter.fromIssueStatus(Status.NEW)));
    }

    @Test
    public void testConvertAllTypes() {
        assertThat(JiraValueConverter.convert(Type.BUG), is(TypeConverter.fromIssueType(Type.BUG)));
        assertThat(JiraValueConverter.convert(Type.TASK), is(TypeConverter.fromIssueType(Type.TASK)));
        assertThat(JiraValueConverter.convert(Type.IMPROVEMENT),
            is(TypeConverter.fromIssueType(Type.IMPROVEMENT)));
        assertThat(JiraValueConverter.convert(Type.NEW_FEATURE),
            is(TypeConverter.fromIssueType(Type.NEW_FEATURE)));
    }

    @Test
    public void testConvertAllFields() {
        assertThat(JiraValueConverter.convert(Field.TYPE), is(FieldConverter.fromIssueField(Field.TYPE)));
        assertThat(JiraValueConverter.convert(Field.DESCRIPTION),
            is(FieldConverter.fromIssueField(Field.DESCRIPTION)));
        assertThat(JiraValueConverter.convert(Field.OWNER), is(FieldConverter.fromIssueField(Field.OWNER)));
        assertThat(JiraValueConverter.convert(Field.PRIORITY),
            is(FieldConverter.fromIssueField(Field.PRIORITY)));
        assertThat(JiraValueConverter.convert(Field.REPORTER),
            is(FieldConverter.fromIssueField(Field.REPORTER)));
        assertThat(JiraValueConverter.convert(Field.STATUS),
            is(FieldConverter.fromIssueField(Field.STATUS)));
        assertThat(JiraValueConverter.convert(Field.SUMMARY),
            is(FieldConverter.fromIssueField(Field.SUMMARY)));
    }

    @Test
    public void testConvertFromString() {
        assertThat(JiraValueConverter.convert("type"), is(FieldConverter.fromIssueField(Field.TYPE)));
        assertThat(JiraValueConverter.convert("Bug"), is(TypeConverter.fromIssueType(Type.BUG)));
        assertThat(JiraValueConverter.convert("NEW"), is(StatusConverter.fromIssueStatus(Status.NEW)));
        assertThat(JiraValueConverter.convert("hIgH"), is(PriorityConverter.fromIssuePriority(Priority.HIGH)));
    }

    @Test
    public void testConvertFromTypeCodeToTypeAsName() {
        assertThat(TypeConverter.fromCode("1"), is("Bug"));
        assertThat(TypeConverter.fromCode("2"), is("New Feature"));
        assertThat(TypeConverter.fromCode("3"), is("Task"));
        assertThat(TypeConverter.fromCode("4"), is("Improvement"));
    }
}
