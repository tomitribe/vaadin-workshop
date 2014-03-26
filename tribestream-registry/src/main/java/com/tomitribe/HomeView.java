package com.tomitribe;

import com.porotype.iconfont.FontAwesome;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;

import java.util.Random;

public class HomeView extends TVerticalLayout implements View {
    private final Repository[] repositories = new Repository[]{
            new Repository(TribestreamTheme.Icons.TOMITRIBE_MARK, "Facebook 1.0",
                    "Connect to the social network with the Graph API.",
                    new Random().nextInt(Integer.MAX_VALUE)),
            new Repository(TribestreamTheme.Icons.TOMITRIBE_MARK, "Twitter 1.1", "Access user data, geo, statuses and more with Twitter's REST API.",
                    new Random().nextInt(Integer.MAX_VALUE)),

    };

    public HomeView() {
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
                addComponent(search = new TextField() {
                    {
                        addStyleName(TribestreamTheme.SEARCH);
                        setInputPrompt("Search repositories…");
                    }
                });

                search.setWidth(TribestreamTheme.Sizes.FULL);
                setExpandRatio(search, 1);
            }
        });
        addComponent(new CssLayout() {
            {
                addStyleName(TribestreamTheme.REPOSITORY_GRID);
                for (Repository repo : repositories) {
                    addComponent(new RepositoryBox(repo));
                }
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
