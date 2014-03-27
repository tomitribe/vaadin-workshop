package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.*;
import com.tomitribe.tribestream.registry.components.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;

public class RepositoryView extends TVerticalLayout implements View {
    private Navigator navigator;
    private Repository repo;

    public RepositoryView(final Repository repo, Navigator navigator) {
        this.repo = repo;
        this.navigator = navigator;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(new TBreadcrumbTrail(navigator));
        addComponent(new THorizontalLayout() {
            {
                addStyleName(TribestreamTheme.HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);

                TextField search;

                addComponent(new TLabel(repo.getTitle()) {
                    {
                        addStyleName(TribestreamTheme.H1);
                    }
                });
                addComponent(new TSpacer());
                addComponent(search = new TSearchField("Search " + repo.getTitle() + "…"));

                search.setWidth(TribestreamTheme.Sizes.FULL);
                setExpandRatio(search, 1);
            }
        });
        addComponent(new TLabel(repo.getDescription()) {
            {
                addStyleName(TribestreamTheme.SUB_HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);
            }
        });
        addComponent(new CssLayout() {
            {
            }
        });
    }
}
