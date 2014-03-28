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
            String path = navigator.getUI().getPage().getLocation().getPath() + "#!";
            addComponent(new TBreadcrumb("Home", path));

            int slash = state.indexOf("/");

            if (slash == -1) {
                addComponent(new TLabel(state));
            } else {
                String repo = state.substring(0, slash);
                String resource = state.substring(slash);

                addComponent(new TBreadcrumb(repo, path + repo));
                addComponent(new TLabel(resource));
            }
        }
    }
}
