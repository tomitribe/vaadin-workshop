package com.tomitribe.components;

import com.vaadin.ui.TextField;

class TSearchField extends TextField {
    public TSearchField(String inputPrompt) {
        addStyleName(TribestreamTheme.SEARCH);
        setInputPrompt(inputPrompt);
    }
}
