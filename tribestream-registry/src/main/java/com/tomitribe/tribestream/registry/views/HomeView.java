package com.tomitribe.tribestream.registry.views;

import com.porotype.iconfont.FontAwesome;
import com.tomitribe.tribestream.registry.components.*;
import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;

import java.util.List;

import static com.tomitribe.tribestream.registry.TribestreamTheme.StyleNames;
import static com.tomitribe.tribestream.registry.TribestreamTheme.expand;

public class HomeView extends TVerticalLayout implements View {
    private final Navigator navigator;
    private final List<RepositoryDto> repos;

    public HomeView(final Navigator navigator, final List<RepositoryDto> repos) {
        this.navigator = navigator;
        this.repos = repos;
        addStyleName(StyleNames.VIEW);

        Panel content;

        addComponent(new TBreadcrumbTrail(navigator));
        addComponent(new THorizontalLayout() {
            {
                addStyleName(StyleNames.HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);

                TSearchField search;

                addComponent(new THeading("Repositories"));
                addComponent(new MenuBar() {
                    {
                        addStyleName(StyleNames.DROPDOWN);
                        setHtmlContentAllowed(true);

                        //FIXME: add real menu options
                        MenuItem menuItem = addItem("" + FontAwesome.Icon.cog, null);
                        menuItem.addItem("Lorem", null);
                        menuItem.addItem("Ipsum", null);
                    }
                });
                addComponent(new TSpacer());
                addComponent(search = new TSearchField("Search repositories…"));

                expand(search, this);
            }
        });
        addComponent(content = new Panel() {
            {
                addStyleName(StyleNames.CONTENT);

                setContent(new CssLayout() {
                    {
                        addStyleName(TribestreamTheme.StyleNames.REPOSITORY_GRID);

                        for (RepositoryDto repo : repos) {
                            addComponent(new TRepositoryBox(navigator, repo));
                        }
                    }
                });
            }
        });

        expand(content, this);
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
