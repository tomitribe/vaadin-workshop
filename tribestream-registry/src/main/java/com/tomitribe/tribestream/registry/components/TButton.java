package com.tomitribe.tribestream.registry.components;

import com.porotype.iconfont.FontAwesome;
import com.vaadin.ui.NativeButton;

public class TButton extends NativeButton {
    {
        setHtmlContentAllowed(true);
    }

    public TButton(FontAwesome.Icon icon) {
        super("" + icon);
    }

    public TButton(FontAwesome.Icon icon, String caption) {
        super(icon + " " + caption);
    }
}
