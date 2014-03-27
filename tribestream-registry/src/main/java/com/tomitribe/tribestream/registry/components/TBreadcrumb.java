package com.tomitribe.tribestream.registry.components;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.NativeButton;

public class TBreadcrumb extends NativeButton {
    public TBreadcrumb(final Navigator navigator, final String view, String caption) {
        super(caption);
        addStyleName(TribestreamTheme.Icons.BREADCRUMB);

        addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo(view);
            }
        });
    }
}
