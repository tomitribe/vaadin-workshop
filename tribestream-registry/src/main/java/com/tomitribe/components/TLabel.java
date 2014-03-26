package com.tomitribe.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class TLabel extends Label {
    public TLabel(String value) {
        super(value);
        setWidth(TribestreamTheme.Sizes.UNDEFINED);
        setContentMode(ContentMode.HTML);
    }
}
