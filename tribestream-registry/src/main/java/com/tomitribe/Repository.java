package com.tomitribe;

import com.vaadin.server.Resource;

public class Repository {
    private Resource icon;
    private String title;
    private String description;
    private long size;

    public Repository(Resource icon, String title, String description, long size) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.size = size;
    }

    public Resource getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getSize() {
        return size;
    }
}
