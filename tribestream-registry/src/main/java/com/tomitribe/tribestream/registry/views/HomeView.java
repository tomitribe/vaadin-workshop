package com.tomitribe.tribestream.registry.views;

import com.porotype.iconfont.FontAwesome;
import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.tomitribe.tribestream.registry.components.TBreadcrumbTrail;
import com.tomitribe.tribestream.registry.components.TButton;
import com.tomitribe.tribestream.registry.components.THeading;
import com.tomitribe.tribestream.registry.components.THorizontalLayout;
import com.tomitribe.tribestream.registry.components.TRepositoryBox;
import com.tomitribe.tribestream.registry.components.TSearchField;
import com.tomitribe.tribestream.registry.components.TSpacer;
import com.tomitribe.tribestream.registry.components.TVerticalLayout;
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.List;

import static com.tomitribe.tribestream.registry.TribestreamTheme.StyleNames;
import static com.tomitribe.tribestream.registry.TribestreamTheme.expand;

public class HomeView extends TVerticalLayout implements View {

    private final Navigator navigator;
    private CssLayout contentLayout;
    private TextField search;

    private final List<RepositoryDto> repos;
    private List<RepositoryDto> filteredRepos;


    public HomeView(final Navigator navigator, final List<RepositoryDto> repos) {
        this.navigator = navigator;
        this.repos = repos;
        addStyleName(StyleNames.VIEW);

        final Panel content;

        contentLayout = new CssLayout() {
            {
                addStyleName(TribestreamTheme.StyleNames.REPOSITORY_GRID);
            }
        };

        addComponent(new TBreadcrumbTrail(navigator));
        addComponent(new THorizontalLayout() {
            {
                addStyleName(StyleNames.HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);

                TSearchField search;

                addComponent(new THeading("Repositories"));
                addComponent(new MenuBar() {
                    {
                        addStyleName(StyleNames.DROPDOWN);
                        setHtmlContentAllowed(true);

                        //FIXME: add real menu options
                        MenuItem menuItem = addItem("" + FontAwesome.Icon.cog, null);
                        menuItem.addItem("Lorem", null);
                        menuItem.addItem("Ipsum", null);
                    }
                });
                addComponent(new TSpacer());
                addComponent(search = new TSearchField("Search repositories…"));

                expand(search, this);
                search.addShortcutListener(new AbstractField.FocusShortcut(
                        search, ShortcutAction.KeyCode.S, ShortcutAction.ModifierKey.ALT));

                search.addShortcutListener(new ShortcutListener(null, ShortcutAction.KeyCode.ESCAPE, null) {
                    @Override
                    public void handleAction(Object sender, Object target) {
                        resetSearch();
                    }
                });

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

        expand(content, this);
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
