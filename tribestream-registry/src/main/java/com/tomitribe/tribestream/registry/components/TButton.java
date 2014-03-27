package com.tomitribe.tribestream.registry.components;

import com.porotype.iconfont.FontAwesome;
import com.vaadin.ui.NativeButton;

public class TButton extends NativeButton {
    public TButton(FontAwesome.Icon icon) {
        super("" + icon);
        setHtmlContentAllowed(true);
    }
}
