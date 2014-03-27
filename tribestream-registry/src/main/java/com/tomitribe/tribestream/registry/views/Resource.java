package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.model.ServiceDto;
import com.vaadin.navigator.Navigator;

import java.util.Random;

public class Resource {
    public static final String SECURE = "secure";
    public static final String VERB = "verb";
    public static final String PATH = "path";
    public static final String SUMMARY = "summary";
    public static final String[] PROPERTIES = {SECURE, VERB, PATH, SUMMARY};
    private final ServiceDto dto;
    private final Navigator navigator;

    //FIXME
    private String path = "/" + new String[]{"foo", "bar", "baz"}[new Random().nextInt(3)];
    private boolean secure = new Random().nextBoolean();
    private String doc = new String[]{"Foo?", "Bar!", "Baz."}[new Random().nextInt(3)];

    public Resource(Navigator navigator, ServiceDto dto) {
        this.navigator = navigator;
        this.dto = dto;
    }

    public boolean isSecure() {
        //FIXME
        return secure;
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
