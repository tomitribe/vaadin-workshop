package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.components.TBreadcrumbTrail;
import com.tomitribe.tribestream.registry.components.THorizontalLayout;
import com.tomitribe.tribestream.registry.components.TLabel;
import com.tomitribe.tribestream.registry.components.TSearchField;
import com.tomitribe.tribestream.registry.components.TSpacer;
import com.tomitribe.tribestream.registry.components.TVerticalLayout;
import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.tomitribe.tribestream.registry.model.GroupDto;
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.util.Map;

public class RepositoryView extends TVerticalLayout implements View {
    private Navigator navigator;
    private RepositoryDto repo;

    public RepositoryView(final RepositoryDto repo, Navigator navigator) {
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

                addComponent(new TLabel(repo.getName()) {
                    {
                        addStyleName(TribestreamTheme.H1);
                    }
                });
                addComponent(new TSpacer());
                addComponent(search = new TSearchField("Search " + repo.getName() + "…"));

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
        addComponent(new TVerticalLayout() {
            {
                for (Map.Entry<String, GroupDto> entry : repo.getGroups().entrySet()) {
                    addComponent(new TLabel(entry.getKey()));
                }
            }
        });
    }

}
