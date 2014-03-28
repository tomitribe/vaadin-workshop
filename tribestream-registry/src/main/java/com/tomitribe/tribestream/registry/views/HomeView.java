package com.tomitribe.tribestream.registry.views;

import com.porotype.iconfont.FontAwesome;
import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.tomitribe.tribestream.registry.components.TBreadcrumbTrail;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.List;

import static com.tomitribe.tribestream.registry.TribestreamTheme.StyleNames;
import static com.tomitribe.tribestream.registry.TribestreamTheme.expand;

public class HomeView extends TVerticalLayout implements View {

    private final Navigator navigator;
    private CssLayout contentLayout;
    private TSearchField search;

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
                search.getTextField().addShortcutListener(new AbstractField.FocusShortcut(
                        search.getTextField(), ShortcutAction.KeyCode.S, ShortcutAction.ModifierKey.ALT));

                search.getTextField().addShortcutListener(new ShortcutListener(null, ShortcutAction.KeyCode.ESCAPE,
                        null) {
                    @Override
                    public void handleAction(Object sender, Object target) {
                        resetSearch();
                    }
                });

                search.getTextField().addTextChangeListener(new FieldEvents.TextChangeListener() {
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
                        refresh(filteredRepos);
                    }
                });
                search.getReset().addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        resetSearch();
                    }
                });
            }
        });

        addComponent(content = new Panel(contentLayout) {
            {
                addStyleName(StyleNames.CONTENT);
            }
        });
        resetSearch();

        expand(content, this);
        setSizeFull();
    }

    private void resetSearch() {
        search.getTextField().setValue("");
        refresh(repos);
    }

    private void refresh(final List<RepositoryDto> list) {
        contentLayout.removeAllComponents();
        for (RepositoryDto repo : list) {
            contentLayout.addComponent(new TRepositoryBox(navigator, repo));
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
