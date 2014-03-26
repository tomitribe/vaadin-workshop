package com.tomitribe;

import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;

public class TRepositoryBox extends TVerticalLayout {
    public TRepositoryBox(final Navigator navigator, final Repository repo) {
        addStyleName(TribestreamTheme.REPOSITORY_BOX);
        setWidth(TribestreamTheme.Sizes.UNDEFINED);

        addComponent(new CssLayout() {
            {
                addStyleName(TribestreamTheme.REPOSITORY_HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);

                addComponent(new TLabel(repo.getTitle()) {
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
        addComponent(new TLabel(repo.getSize() + " resources") {
            {
                addStyleName(TribestreamTheme.REPOSITORY_SIZE);
            }
        });

        addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                String title = repo.getTitle();
                navigator.addView(title, new RepositoryView(repo));
                navigator.navigateTo(title);
            }
        });
    }
}
