package com.tomitribe.components;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.NativeButton;

public class TBreadcrumb extends NativeButton {
    public TBreadcrumb(final Navigator navigator, final String view, String caption) {
        super(caption);
        addStyleName(TribestreamTheme.BREADCRUMB);

        addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo(view);
            }
        });
    }
}
