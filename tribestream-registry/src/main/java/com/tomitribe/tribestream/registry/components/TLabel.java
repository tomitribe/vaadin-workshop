package com.tomitribe.tribestream.registry.components;

import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class TLabel extends Label {
    {
        setWidth(TribestreamTheme.Sizes.UNDEFINED);
        setContentMode(ContentMode.HTML);
    }

    public TLabel(String value) {
        super(value);
    }

    public TLabel(String caption, String value) {
        this(value);
        setCaption(caption);
    }
}
