package com.tomitribe.tribestream.registry.components;

import com.vaadin.ui.TextField;

public class TSearchField extends TextField {
    public TSearchField(String inputPrompt) {
        addStyleName(TribestreamTheme.SEARCH);
        setInputPrompt(inputPrompt);
    }
}