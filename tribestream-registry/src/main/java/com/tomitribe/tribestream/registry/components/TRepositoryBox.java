package com.tomitribe.tribestream.registry.components;

import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.tomitribe.tribestream.registry.views.RepositoryView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CssLayout;

public class TRepositoryBox extends TVerticalLayout {
    public TRepositoryBox(final Navigator navigator, final RepositoryDto repo) {
        addStyleName(TribestreamTheme.REPOSITORY_BOX);
        setWidth(TribestreamTheme.Sizes.UNDEFINED);

        addComponent(new CssLayout() {
            {
                addStyleName(TribestreamTheme.REPOSITORY_HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);

                addComponent(new TLabel(repo.getName()) {
                    {
                        addStyleName(TribestreamTheme.REPOSITORY_TITLE);
                    }
                });
            }
        });
        addComponent(new TLabel(repo.getDescription()) {
            {
                addStyleName(TribestreamTheme.REPOSITORY_DESCRIPTION);
            }
        });
        addComponent(new TLabel(repo.numberOfResources() + " resources") {
            {
                addStyleName(TribestreamTheme.REPOSITORY_SIZE);
            }
        });

        addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                String title = repo.getName();
                navigator.addView(title, new RepositoryView(repo));
                navigator.addView(title, new RepositoryView(repo, navigator));
                navigator.navigateTo(title);
            }
        });
    }
}
