package com.tomitribe.tribestream.registry.model;

import com.tomitribe.wadl.api.Method;
import com.tomitribe.wadl.api.Param;
import com.tomitribe.wadl.api.Resource;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class ServiceDto extends AbstractEnrichDto {

    private Method method;
    private Deque<Resource> stackOfResource;
    private GroupDto group;

    public ServiceDto() {

    }

    public ServiceDto(final Method method, final ArrayDeque stackOfResource) {
        super(method.getAny());

        this.method = method;
        this.stackOfResource = stackOfResource;
    }

    public Method getMethod() {
        return method;
    }

    public String getPath() {
        final StringBuilder path = new StringBuilder();
        final Iterator<Resource> iterator = stackOfResource.descendingIterator();
        while (iterator.hasNext()) {
            Resource next = iterator.next();
            path.append(next.getPath());
        }
        return path.toString();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("methodId", method.getId());

        for (Param param : method.getRequest().getParam()) {
            builder.append(new ToStringBuilder(param, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append(param.getName())
                    .append(param.getType()));
        }

        for (Resource resource : stackOfResource) {
            builder.append(new ToStringBuilder(resource, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append(resource.getId())
                    .append(resource.getPath()));
        }

        //builder.append(super.toString());

        return builder.toString();
    }

    public void setGroup(final GroupDto group) {
        this.group = group;
    }

    public GroupDto getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceDto)) return false;

        final ServiceDto that = (ServiceDto) o;

        if (that.hashCode() != this.hashCode()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 45 * method.getName().hashCode();

        Iterator<Resource> iterator = stackOfResource.descendingIterator();
        while (iterator.hasNext()) {
            Resource next = iterator.next();
            result = 17 * next.getPath().hashCode();
        }
        result = 17 * result + group.getName().hashCode();
        return result;
    }
}
