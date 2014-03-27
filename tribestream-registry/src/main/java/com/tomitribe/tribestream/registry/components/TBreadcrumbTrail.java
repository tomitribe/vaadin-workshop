package com.tomitribe.tribestream.registry.components;

import com.vaadin.navigator.Navigator;

public class TBreadcrumbTrail extends THorizontalLayout {
    public TBreadcrumbTrail(Navigator navigator) {
        addStyleName(TribestreamTheme.BREADCRUMB_TRAIL);
        if (navigator.getState().isEmpty()) {
            addComponent(new TLabel("Home"));
        } else {
            addComponent(new TBreadcrumb(navigator, "", "Home"));
            addComponent(new TLabel(navigator.getState()));
        }
    }
}
