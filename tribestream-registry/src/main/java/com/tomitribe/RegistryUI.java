package com.tomitribe;

import com.porotype.iconfont.FontAwesome;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

@Theme(TribestreamTheme.THEME_NAME)
@SuppressWarnings("serial")
public class RegistryUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        FontAwesome.load();
        Navigator navigator = new Navigator(this, this);
        navigator.addView("", new HomeView());
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = RegistryUI.class, widgetset = "com.tomitribe.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
}
