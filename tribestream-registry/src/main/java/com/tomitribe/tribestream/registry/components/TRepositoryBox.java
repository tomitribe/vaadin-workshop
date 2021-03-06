package com.tomitribe.tribestream.registry.components;

import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.tomitribe.tribestream.registry.views.RepositoryView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CssLayout;

import static com.tomitribe.tribestream.registry.TribestreamTheme.expand;

public class TRepositoryBox extends TVerticalLayout {
    public TRepositoryBox(final Navigator navigator, final RepositoryDto repo) {
        addStyleName(TribestreamTheme.StyleNames.REPOSITORY_BOX);
        setWidth("200px");
        setHeight("200px");

        TLabel description;

        addComponent(new CssLayout() {
            {
                addStyleName(TribestreamTheme.StyleNames.REPOSITORY_HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);

                addComponent(new TLabel(repo.getName()) {
                    {
                        addStyleName(TribestreamTheme.StyleNames.REPOSITORY_TITLE);
                    }
                });
            }
        });
        addComponent(description = new TLabel(repo.getDescription()) {
            {
                addStyleName(TribestreamTheme.StyleNames.REPOSITORY_DESCRIPTION);
            }
        });
        addComponent(new TLabel(repo.getNumberOfResources() + " resources") {
            {
                addStyleName(TribestreamTheme.StyleNames.REPOSITORY_SIZE);
            }
        });

        description.setSizeFull();
        expand(description, this);

        addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                String title = repo.getName();
                navigator.addView(title, new RepositoryView(repo, navigator));
                navigator.navigateTo(title);
            }
        });
    }
}
