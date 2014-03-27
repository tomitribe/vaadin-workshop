package com.tomitribe.tribestream.registry.components;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;

public class TBreadcrumb extends Link {
    public TBreadcrumb(String caption, String path) {
        super(caption, new ExternalResource(path));
        addStyleName(TribestreamTheme.Icons.BREADCRUMB);
    }
}
