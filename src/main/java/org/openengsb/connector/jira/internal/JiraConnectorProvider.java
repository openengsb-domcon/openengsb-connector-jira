package org.openengsb.connector.jira.internal;

import org.openengsb.core.api.descriptor.ServiceDescriptor;
import org.openengsb.core.api.descriptor.ServiceDescriptor.Builder;
import org.openengsb.core.common.AbstractConnectorProvider;

public class JiraConnectorProvider extends AbstractConnectorProvider {

    @Override
    public ServiceDescriptor getDescriptor() {
        Builder builder = ServiceDescriptor.builder(strings);
        builder.id(this.id);
        builder.name("service.name").description("service.description");

        builder.attribute(
            builder.newAttribute().id("jira.user").name("jira.user.name").description("jira.user.description").build());
        builder.attribute(builder.newAttribute().id("jira.password").name("jira.password.name")
            .description("jira.password.description").defaultValue("").asPassword().build());
        builder.attribute(
            builder.newAttribute().id("jira.project").name("jira.project.name").description("jira.project.description")
                .defaultValue("").required().build());
        builder.attribute(
            builder.newAttribute().id("jira.uri").name("jira.uri.name").description("jira.uri.description")
                .defaultValue("").required().build());

        return builder.build();
    }

}
