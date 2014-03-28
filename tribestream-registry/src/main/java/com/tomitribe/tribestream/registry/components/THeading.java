package com.tomitribe.tribestream.registry.components;

import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.vaadin.ui.Component;

import static com.tomitribe.tribestream.registry.TribestreamTheme.StyleNames;

public class THeading extends TLabel {
    public THeading(String value) {
        super(value);
        addStyleName(StyleNames.H1);
    }
}
