package com.tomitribe.tribestream.registry;

import com.porotype.iconfont.FontAwesome;
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.tomitribe.tribestream.registry.model.generator.Descriptions;
import com.tomitribe.tribestream.registry.model.generator.FluentIterable;
import com.tomitribe.tribestream.registry.model.generator.Names;
import com.tomitribe.tribestream.registry.model.generator.Randoms;
import com.tomitribe.tribestream.registry.views.HomeView;
import com.tomitribe.tribestream.registry.views.RepositoryView;
import com.tomitribe.tribestream.registry.views.ResourceView;
import com.tomitribe.wadl.api.Application;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Theme(TribestreamTheme.THEME_NAME)
@SuppressWarnings("serial")
public class RegistryUI extends UI {
    private List<RepositoryDto> repoList;
    private Map<String, RepositoryDto> repoMap;

    public RegistryUI() {
        initData();
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

    @Override
    protected void init(VaadinRequest request) {
        FontAwesome.load();

        final Navigator navigator = new Navigator(this, this);
        final HomeView homeView = new HomeView(navigator, repoList);

        navigator.addView("", homeView);
        navigator.addProvider(new ViewProvider() {
            @Override
            public String getViewName(String viewAndParameters) {
                return viewAndParameters;
            }

            @Override
            public View getView(String viewName) {
                RepositoryDto repo;
                if (viewName.contains("/")) {
                    int slash = viewName.indexOf('/');
                    String repoName = viewName.substring(0, slash);
                    repo = repoMap.get(repoName);
                    String resourceName = viewName.substring(slash + 1);
                    if (resourceName.length() != 0) {
                        return new ResourceView(resourceName, navigator);
                    }
                } else {
                    repo = repoMap.get(viewName);
                }
                return repo == null ? null : new RepositoryView(repo, navigator);
            }
        });
    }

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

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = RegistryUI.class,
            widgetset = "com.tomitribe.tribestream.registry.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
}
