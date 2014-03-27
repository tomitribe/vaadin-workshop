package com.tomitribe.tribestream.registry.components;

import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.vaadin.ui.TextField;

public class TSearchField extends TextField {
    public TSearchField(String inputPrompt) {
        addStyleName(TribestreamTheme.StyleNames.SEARCH);
        setInputPrompt(inputPrompt);
    }
}
