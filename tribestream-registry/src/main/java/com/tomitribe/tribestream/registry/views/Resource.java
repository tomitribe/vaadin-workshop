package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.model.ServiceDto;

import java.util.Random;

public class Resource {
    private final ServiceDto dto;

    public Resource(ServiceDto dto) {
        this.dto = dto;
    }

    public boolean isSecure() {
        //FIXME
        return new Random().nextBoolean();
    }

    public String getVerb() {
        return dto.getMethod().getName();
    }

    public String getPath() {
        //FIXME
        return dto.getMethod().getHref();
    }

    public String getSummary() {
        //FIXME
        return dto.getMethod().getDoc().toString();
    }
}
