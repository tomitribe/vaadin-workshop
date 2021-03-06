package com.tomitribe.tribestream.registry.model;

import com.tomitribe.wadl.api.Application;
import com.tomitribe.wadl.api.Resources;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class GroupDto extends AbstractDto {

    private String name;
    private final Application application;
    private final Resources resources;

    private List<ServiceDto> serviceDtos;
    private String description;

    public GroupDto() {
        this(null, null, null);
    }

    public GroupDto(final String name, final Application application, final Resources resources) {
        this.name = name;
        this.application = application;
        this.resources = resources;
    }

    public List<ServiceDto> getServiceDtos() {
        if (serviceDtos == null) {
            serviceDtos = new ArrayList<ServiceDto>();
        }
        return serviceDtos;
    }

    public Application getApplication() {
        return application;
    }

    public Resources getResources() {
        return resources;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("name", name)
                .append("resourcesBase", resources.getBase())
                .append("serviceDtos", serviceDtos)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupDto)) return false;

        final GroupDto groupDto = (GroupDto) o;

        if (application != null ? !application.equals(groupDto.application) : groupDto.application != null)
            return false;
        if (!name.equals(groupDto.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (application != null ? application.hashCode() : 0);
        return result;
    }

    public String getDescription() {
        if (description == null || description.trim().length() == 0) {
            // try do read doc tags
            description = getStringDoc(resources.getDoc());
        }
        return description;
    }
}
