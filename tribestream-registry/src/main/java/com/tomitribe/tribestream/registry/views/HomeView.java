package com.tomitribe.tribestream.registry.views;

import com.porotype.iconfont.FontAwesome;
import com.tomitribe.tribestream.registry.components.*;
import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.List;

import static com.tomitribe.tribestream.registry.TribestreamTheme.expand;

public class HomeView extends TVerticalLayout implements View {

    private final Navigator navigator;
    private final List<RepositoryDto> repos;
    private List<RepositoryDto> filteredRepos;
    private CssLayout contentLayout;

    public HomeView(final Navigator navigator, final List<RepositoryDto> repos) {
        this.navigator = navigator;
        this.repos = repos;

        final Panel content;

        contentLayout = new CssLayout() {
            {
                addStyleName(TribestreamTheme.StyleNames.REPOSITORY_GRID);
            }
        };

        addComponent(new TBreadcrumbTrail(navigator));
        addComponent(new THorizontalLayout() {
            {
                addStyleName(TribestreamTheme.StyleNames.HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);

                TextField search;

                addComponent(new THeading("Repositories"));
                addComponent(new TButton(FontAwesome.Icon.cog) {
                    {
                        addStyleName(TribestreamTheme.StyleNames.OPTIONS);
                    }
                });
                addComponent(new TSpacer());
                addComponent(search = new TSearchField("Search repositories…"));

                expand(search, this);
                search.addShortcutListener(new AbstractField.FocusShortcut(
                        search, ShortcutAction.KeyCode.S, ShortcutAction.ModifierKey.ALT));
                search.addTextChangeListener(new FieldEvents.TextChangeListener() {
                    @Override
                    public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                        final String searchFor = textChangeEvent.getText();

                        // search in the List
                        filteredRepos = new ArrayList<RepositoryDto>(repos);
                        CollectionUtils.filter(filteredRepos, new Predicate<RepositoryDto>() {
                            @Override
                            public boolean evaluate(final RepositoryDto repositoryDto) {
                                final String content = repositoryDto.getName() + " " +
                                        repositoryDto.getDescription();

                                for (final String string : searchFor.split(" +")) {
                                    if (string.startsWith("-")) {
                                        final String exclude = string.substring(1);
                                        if (exclude.length() > 0 && content.contains(exclude)) return false;

                                    } else {
                                        if (!content.contains(string)) return false;
                                    }
                                }

                                return true;
                            }
                        });

                        // refresh
                        refresh(contentLayout, filteredRepos);
                    }
                });
            }
        });

        addComponent(content = new Panel(contentLayout));
        refresh(contentLayout, repos);

        expand(content, this);
        setSizeFull();
    }

    private void refresh(final CssLayout layout, final List<RepositoryDto> list) {
        layout.removeAllComponents();
        for (RepositoryDto repo : list) {
            layout.addComponent(new TRepositoryBox(navigator, repo));
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
