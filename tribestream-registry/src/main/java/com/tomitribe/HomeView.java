package com.tomitribe;

import com.porotype.iconfont.FontAwesome;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;

import java.util.Random;

public class HomeView extends TVerticalLayout implements View {
    public HomeView(final Navigator navigator, final Repository[] repos) {
        addComponent(new THorizontalLayout() {
            {
                addStyleName(TribestreamTheme.HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);

                TextField search;

                addComponent(new TLabel("Repositories") {
                    {
                        addStyleName(TribestreamTheme.H1);
                    }
                });
                addComponent(new TButton(FontAwesome.Icon.cog) {
                    {
                        addStyleName(TribestreamTheme.OPTIONS);
                    }
                });
                addComponent(new TSpacer());
                addComponent(search = new TSearchField("Search repositories…"));

                search.setWidth(TribestreamTheme.Sizes.FULL);
                setExpandRatio(search, 1);
            }
        });
        addComponent(new CssLayout() {
            {
                addStyleName(TribestreamTheme.REPOSITORY_GRID);
                for (Repository repo : repos) {
                    addComponent(new TRepositoryBox(navigator, repo));
                }
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {}
}
