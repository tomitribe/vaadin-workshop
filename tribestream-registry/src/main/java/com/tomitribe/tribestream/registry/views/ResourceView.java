package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.components.TBreadcrumbTrail;
import com.tomitribe.tribestream.registry.components.TVerticalLayout;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Panel;

public class ResourceView extends TVerticalLayout implements View {
    private Navigator navigator;

    public ResourceView(String resourceName, Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(new TBreadcrumbTrail(navigator));

        Panel content;

        addComponent(content = new Panel());

        content.setSizeFull();
        setExpandRatio(content, 1);

        setSizeFull();
    }
}