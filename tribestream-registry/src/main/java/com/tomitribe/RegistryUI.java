package com.tomitribe;

import com.porotype.iconfont.FontAwesome;
import com.tomitribe.components.TribestreamTheme;
import com.tomitribe.views.HomeView;
import com.tomitribe.views.RepositoryView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Theme(TribestreamTheme.THEME_NAME)
@SuppressWarnings("serial")
public class RegistryUI extends UI {
    private Repository[] repos = new Repository[]{
            new Repository(TribestreamTheme.Icons.TOMITRIBE_MARK, "Facebook 1.0",
                    "Connect to the social network with the Graph API.",
                    new Random().nextInt(Integer.MAX_VALUE)),
            new Repository(TribestreamTheme.Icons.TOMITRIBE_MARK, "Twitter 1.1",
                    "Access user data, geo, statuses and more with Twitter's REST API.",
                    new Random().nextInt(Integer.MAX_VALUE)),

    };
    private Map<String, Repository> repoMap = new HashMap<String, Repository>() {
        {
            for (Repository repo : repos) {
                put(repo.getTitle(), repo);
            }
        }
    };


    @Override
    protected void init(VaadinRequest request) {
        FontAwesome.load();
        Navigator navigator = new Navigator(this, this);
        navigator.addView("", new HomeView(navigator, repos));
        navigator.addProvider(new ViewProvider() {
            @Override
            public String getViewName(String viewAndParameters) {
                return viewAndParameters;
            }

            @Override
            public View getView(String viewName) {
                return new RepositoryView(repoMap.get(
                         viewName.contains("/") ? viewName.substring(0, viewName.indexOf('/')) : viewName));
            }
        });
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = RegistryUI.class, widgetset = "com.tomitribe.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
}
