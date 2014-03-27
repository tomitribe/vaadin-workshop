package com.tomitribe.tribestream.registry.components;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.themes.ChameleonTheme;

public class TribestreamTheme {
    public static final String THEME_NAME = "tribestream";

    public static final String H1 = ChameleonTheme.LABEL_H1;
    public static final String SEARCH = ChameleonTheme.TEXTFIELD_SEARCH;

    public static final String OPTIONS = "t-options";

    public static final String HEADER = "t-header";
    public static final String SUB_HEADER = "t-sub-header";

    public static final String REPOSITORY_GRID = "t-repository-grid";
    public static final String REPOSITORY_BOX = "t-repository-box";
    public static final String REPOSITORY_ICON = "t-repository-icon";
    public static final String REPOSITORY_HEADER = "t-repository-header";
    public static final String REPOSITORY_TITLE = "t-repository-title";
    public static final String REPOSITORY_DESCRIPTION = "t-repository-description";
    public static final String REPOSITORY_SIZE = "t-repository-size";

    public static class Icons {
        public static final ThemeResource TOMITRIBE_MARK = new ThemeResource("tomitribe-mark_50x46.png");
    }

    public static class Sizes {
        public static final String UNDEFINED = null;
        public static final String FULL = "100%";
    }
}
