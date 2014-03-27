package com.tomitribe.tribestream.registry.components;

import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.vaadin.navigator.Navigator;

public class TBreadcrumbTrail extends THorizontalLayout {
    public TBreadcrumbTrail(Navigator navigator) {
        addStyleName(TribestreamTheme.StyleNames.BREADCRUMB_TRAIL);
        String state = navigator.getState();
        if (state.isEmpty()) {
            addComponent(new TLabel("Home"));
        } else {
            String path = navigator.getUI().getPage().getLocation().getPath();
            addComponent(new TBreadcrumb("Home", path));
            String[] tokens = state.split("/");
            path += "#!";
            TBreadcrumb last = null;
            for (String token : tokens) {
                if (token.length() > 0) {
                    path += token;
                    addComponent(last = new TBreadcrumb(token, path));
                    path += '/';
                }
            }
            if (last != null) {
                removeComponent(last);
                addComponent(new TLabel(last.getCaption()));
            }
        }
    }
}
