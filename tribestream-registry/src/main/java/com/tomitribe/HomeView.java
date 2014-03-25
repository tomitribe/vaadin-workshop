package com.tomitribe;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;

import java.util.Random;

public class HomeView extends TVerticalLayout implements View {

    private final Repository[] repositories = new Repository[]{
            new Repository(new ThemeResource("../reindeer/favicon.ico"), "Facebook 1.0",
                    "Connect to the social network with the Graph API.",
                    new Random().nextInt(Integer.MAX_VALUE)),
            new Repository(new ThemeResource("../reindeer/favicon.ico"), "Twitter 1.1", "Access user data, geo, statuses and more with Twitter's REST API.",
                    new Random().nextInt(Integer.MAX_VALUE)),

    };

    public HomeView() {
        addComponent(new THorizontalLayout() {
            {
                addStyleName(TribestreamTheme.HEADER);

                addComponent(new TLabel("Repositories") {
                    {
                        addStyleName(TribestreamTheme.H1);
                    }
                });
                addComponent(new NativeButton("Options") {
                    {
                        addStyleName(TribestreamTheme.OPTIONS);
                    }
                });
                addComponent(new TextField() {
                    {
                        addStyleName(TribestreamTheme.SEARCH);
                        setInputPrompt("Search repositories…");
                    }
                });
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
