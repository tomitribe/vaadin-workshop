package com.tomitribe.tribestream.registry.model;

import com.tomitribe.wadl.api.Application;
import com.tomitribe.wadl.api.Method;
import com.tomitribe.wadl.api.Resource;
import com.tomitribe.wadl.api.Resources;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RepositoryDto extends AbstractEnrichDto {

    private Application application;
    private Map<String, GroupDto> groupDto;
    private RepositoryType type;

    private String name;
    private String description;

    public RepositoryDto() {

    }

    public RepositoryDto(final Application application, final RepositoryType type) {
        super(application.getAny());
        this.application = application;
        this.type = type;

        extractGroups();
    }

    public Application getApplication() {
        return application;
    }

    public RepositoryType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        if (description == null || description.trim().length() == 0) {
            // try do read doc tags
            description = getStringDoc(application.getDoc());
        }
        return description;
    }

    public Map<String, GroupDto> getGroups() {
        if (groupDto == null) {
            groupDto = new HashMap<String, GroupDto>();
        }
        return groupDto;
    }

    private void extractGroups() {
        for (Resources resources : application.getResources()) {
            extractGroups(resources);
        }
    }

    private void extractGroups(final Resources resources) {
        final Deque<Resource> deque = new ArrayDeque<Resource>();
        for (Resource resource : resources.getResource()) {
            deque.push(resource);
            try {
                extractServices(resources, deque);

            } finally {
                deque.pop();
            }
        }
    }

    private void extractServices(final Resources resources, final Deque<Resource> deque) {
        final Resource resource = deque.peek();
        for (Serializable serializable : resource.getMethodOrResource()) {
            if (serializable instanceof Resource) {
                final Resource s = (Resource) serializable;
                deque.push(s);
                try {
                    extractServices(resources, deque);

                } finally {
                    deque.pop();
                }

            } else if (serializable instanceof Method) {
                final Method m = (Method) serializable;
                final ServiceDto dto = new ServiceDto(m, ((ArrayDeque) deque).clone());

                for (String category : dto.categories.getCategory()) {
                    // group already created?
                    GroupDto gp = getGroups().get(category);
                    if (gp == null) {
                        gp = new GroupDto(category, application, resources);
                        getGroups().put(category, gp);
                    }
                    gp.getServiceDtos().add(dto);
                    dto.setGroup(gp);
                }

            }
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("groupDto", groupDto.values().toArray())
                .toString();
    }

    public int numberOfResources() {
        int nb = 0;
        for (GroupDto dto : groupDto.values()) {
            nb += dto.getServiceDtos().size();
        }
        return nb;
    }

    // build
    public RepositoryDto withName(final String name) {
        this.name = name;
        return this;
    }

    public RepositoryDto withDescription(final String description) {
        this.description = description;
        return this;
    }

    public static enum RepositoryType {
        LOCAL, DEPLOYED
    }
}
