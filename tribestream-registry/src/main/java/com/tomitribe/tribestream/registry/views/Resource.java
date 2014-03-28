package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.model.ServiceDto;

public class Resource {
    public static final String SECURE = "secure";
    public static final String VERB = "verb";
    public static final String PATH = "path";
    public static final String SUMMARY = "summary";
    public static final String[] PROPERTIES = {SECURE, VERB, PATH, SUMMARY};
    private final ServiceDto dto;

    public Resource(ServiceDto dto) {
        this.dto = dto;
    }

    public boolean isSecure() {
        return dto.getRolesAllowed() != null;
    }

    public String getVerb() {
        return dto.getMethod().getName();
    }

    public String getPath() {
        return dto.getPath();
    }

    public String getSummary() {
        return dto.getStringDoc(dto.getMethod());
    }

}
