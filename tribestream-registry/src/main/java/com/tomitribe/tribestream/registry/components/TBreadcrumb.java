package com.tomitribe.tribestream.registry.components;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeButton;

import java.net.URL;

public class TBreadcrumb extends Link {
    public TBreadcrumb(String caption, String path) {
        super(caption, new ExternalResource(path));
        addStyleName(TribestreamTheme.BREADCRUMB);
    }
}
