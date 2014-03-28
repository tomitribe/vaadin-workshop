package com.tomitribe.tribestream.registry.components;

import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.vaadin.ui.Component;

import static com.tomitribe.tribestream.registry.TribestreamTheme.Sizes;
import static com.tomitribe.tribestream.registry.TribestreamTheme.StyleNames;

public class TBodyText extends TLabel {
    public TBodyText(String value) {
        super(value);
        addStyleName(StyleNames.BODY_TEXT);
        setWidth("40em");
    }
}
