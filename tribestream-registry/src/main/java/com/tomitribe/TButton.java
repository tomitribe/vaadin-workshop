package com.tomitribe;

import com.porotype.iconfont.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.NativeButton;

public class TButton extends NativeButton {
    public TButton(FontAwesome.Icon icon) {
        super("" + icon);
        setHtmlContentAllowed(true);
    }
}
