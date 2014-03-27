package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.model.ServiceDto;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;

import java.util.Random;

public class Resource {
    public static final String SECURE = "secure";
    public static final String VERB = "verb";
    public static final String PATH = "path";
    public static final String SUMMARY = "summary";
    public static final String[] PROPERTIES = {SECURE, VERB, PATH, SUMMARY};
    private final ServiceDto dto;
    private final Navigator navigator;

    public Resource(Navigator navigator, ServiceDto dto) {
        this.navigator = navigator;
        this.dto = dto;
    }

    public boolean isSecure() {
        //FIXME
        return new Random().nextBoolean();
    }

    public String getVerb() {
        return dto.getMethod().getName();
    }

    public Link getPath() {
        //FIXME
        String path = "/path-goes-here-" + new Random().nextInt();

        String current = navigator.getUI().getPage().getLocation().getPath() + "#!" + navigator.getState();
        return new Link(path, new ExternalResource(current + path));
    }

    public String getSummary() {
        //FIXME
        return "First sentence of the documentation goes here.";
    }
}
