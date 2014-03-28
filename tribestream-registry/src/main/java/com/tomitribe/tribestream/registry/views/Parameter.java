package com.tomitribe.tribestream.registry.views;

public class Parameter {
    private final String name;
    private final String type;
    private final String description;

    public Parameter(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
