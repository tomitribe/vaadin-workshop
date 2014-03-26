package com.tomitribe;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;

public class RepositoryView extends TVerticalLayout implements View {
    public RepositoryView(final Repository repo) {
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
        addComponent(new CssLayout() {
            {
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {}
}
