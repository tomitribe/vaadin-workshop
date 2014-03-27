package com.tomitribe.tribestream.registry;

import com.porotype.iconfont.FontAwesome;
import com.tomitribe.tribestream.registry.components.TribestreamTheme;
<<<<<<< HEAD
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.tomitribe.tribestream.registry.model.generator.Descriptions;
import com.tomitribe.tribestream.registry.model.generator.FluentIterable;
import com.tomitribe.tribestream.registry.model.generator.Names;
import com.tomitribe.tribestream.registry.model.generator.Randoms;
import com.tomitribe.tribestream.registry.views.HomeView;
import com.tomitribe.tribestream.registry.views.RepositoryView;
import com.tomitribe.wadl.api.Application;
=======
import com.tomitribe.tribestream.registry.views.HomeView;
import com.tomitribe.tribestream.registry.views.RepositoryView;
>>>>>>> FETCH_HEAD
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
<<<<<<< HEAD
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
>>>>>>> FETCH_HEAD

@Theme(TribestreamTheme.THEME_NAME)
@SuppressWarnings("serial")
public class RegistryUI extends UI {
<<<<<<< HEAD
    private List<RepositoryDto> repoList;
    private Map<String, RepositoryDto> repoMap;
=======
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
>>>>>>> FETCH_HEAD


    @Override
    protected void init(VaadinRequest request) {
        FontAwesome.load();
<<<<<<< HEAD

        initData();

        Navigator navigator = new Navigator(this, this);
        navigator.addView("", new HomeView(navigator, repoList));
=======
        final Navigator navigator = new Navigator(this, this);
        navigator.addView("", new HomeView(navigator, repos));
>>>>>>> FETCH_HEAD
        navigator.addProvider(new ViewProvider() {
            @Override
            public String getViewName(String viewAndParameters) {
                return viewAndParameters;
            }

            @Override
            public View getView(String viewName) {
                return new RepositoryView(repoMap.get(
<<<<<<< HEAD
                        viewName.contains("/") ? viewName.substring(0, viewName.indexOf('/')) : viewName));
=======
                         viewName.contains("/") ? viewName.substring(0, viewName.indexOf('/')) : viewName), navigator);
>>>>>>> FETCH_HEAD
            }
        });
    }

<<<<<<< HEAD
    private void initData() {
        repoList = getRepoList();

        repoMap = new HashMap<String, RepositoryDto>();
        for (RepositoryDto repositoryDto : repoList) {
            repoMap.put(repositoryDto.getName(), repositoryDto);
        }
    }

    public List<RepositoryDto> getRepoList() {
        List<RepositoryDto> list = new ArrayList<RepositoryDto>();

        for (String application : FluentIterable.fluent(Names.random()).limit(20)) {
            list.add(new RepositoryDto(getTwitterApplicationWithName(), RepositoryDto.RepositoryType.LOCAL)
                    .withName(camelCase(application, "-"))
                    .withDescription(Randoms.selection(Descriptions.LIST)));
        }

        return list;
    }

    public Application getTwitterApplicationWithName() {
        try {
            final JAXBContext ctx = JAXBContext.newInstance(Application.class);
            final Unmarshaller unmarshaller = ctx.createUnmarshaller();
            return (Application) unmarshaller.unmarshal(this.getClass().getResource("/expected-twitter.wadl"));

        } catch (JAXBException e) {
            return null;
        }
    }

    public static String camelCase(final String string, final String delimiter) {
        final StringBuilder sb = new StringBuilder();
        final String[] strings = string.split(delimiter);

        for (final String s : strings) {
            final int l = sb.length();
            sb.append(s);
            sb.setCharAt(l, Character.toUpperCase(sb.charAt(l)));
        }
        return sb.toString();
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = RegistryUI.class, widgetset = "com.tomitribe.tribestream" +
            ".registry.AppWidgetSet")
=======
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = RegistryUI.class, widgetset = "com.tomitribe.tribestream.registry.AppWidgetSet")
>>>>>>> FETCH_HEAD
    public static class Servlet extends VaadinServlet {
    }
}
