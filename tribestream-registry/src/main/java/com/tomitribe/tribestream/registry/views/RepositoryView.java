package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.components.TBreadcrumbTrail;
import com.tomitribe.tribestream.registry.components.THeading;
import com.tomitribe.tribestream.registry.components.THorizontalLayout;
import com.tomitribe.tribestream.registry.components.TLabel;
import com.tomitribe.tribestream.registry.components.TSearchField;
import com.tomitribe.tribestream.registry.components.TSpacer;
import com.tomitribe.tribestream.registry.components.TVerticalLayout;
import com.tomitribe.tribestream.registry.model.GroupDto;
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.tomitribe.tribestream.registry.model.ServiceDto;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.vaadin.jouni.animator.Disclosure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.tomitribe.tribestream.registry.TribestreamTheme.*;

public class RepositoryView extends TVerticalLayout implements View {
    private Navigator navigator;
    private TSearchField search;
    private TVerticalLayout contentLayout = new TVerticalLayout();

    private RepositoryDto repo;
    private List<GroupDto> filteredGroups;

    public RepositoryView(final RepositoryDto repo, Navigator navigator) {
        this.repo = repo;
        this.navigator = navigator;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addStyleName(StyleNames.VIEW);

        Panel content;

        addComponent(new TBreadcrumbTrail(navigator));
        addComponent(new THorizontalLayout() {
            {
                addStyleName(StyleNames.HEADER);
                setWidth(Sizes.FULL);

                addComponent(new THeading(repo.getName()));
                addComponent(new TSpacer());
                addComponent(search = new TSearchField("Search " + repo.getName() + "…"));

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
                        filteredGroups = new ArrayList<GroupDto>(repo.getGroupDto().values());
                        CollectionUtils.filter(filteredGroups, new Predicate<GroupDto>() {
                            @Override
                            public boolean evaluate(final GroupDto groupDto) {
                                final String content = groupDto.getName() + " " +
                                        groupDto.getDescription();

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
                        refresh(filteredGroups);
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
        addComponent(new TLabel(repo.getDescription()) {
            {
                addStyleName(StyleNames.SUB_HEADER);
                setWidth(Sizes.FULL);
            }
        });
        addComponent(content = new Panel() {
            {
                addStyleName(StyleNames.CONTENT);

                setContent(contentLayout);
                resetSearch();
            }
        });

        expand(content, this);

        setSizeFull();
    }

    private void resetSearch() {
        search.getTextField().setValue("");
        refresh(this.repo.getGroupDto().values());
    }

    private void refresh(final Collection<GroupDto> list) {
        contentLayout.removeAllComponents();

        for (final GroupDto dto : list) {
            final Disclosure disclosure;
            contentLayout.addComponent(disclosure = new Disclosure(dto.getName()) {
                {
                    setWidth(Sizes.FULL);

                    setContent(new TVerticalLayout() {
                        {
                            setWidth(Sizes.FULL);

                            addComponent(new TLabel(dto.getDescription()));

                            addComponent(new Table() {
                                {
                                    List<ServiceDto> resources = dto.getServiceDtos();

                                    setWidth(Sizes.FULL);
                                    setHeight(Sizes.tableHeight(resources.size()));

                                    setContainerDataSource(new ResourceContainer(navigator, resources));
                                    addGeneratedColumn("path", new ColumnGenerator() {
                                        @Override
                                        public Object generateCell(
                                                Table source, Object itemId, Object columnId) {
                                            Resource resource = (Resource) itemId;
                                            String path = resource.getPath();
                                            String current = navigator.getUI().getPage().getLocation().getPath()
                                                    + "#!" + navigator.getState();
                                            return new Link(path, new ExternalResource(current + path));
                                        }
                                    });
                                    setVisibleColumns(Resource.PROPERTIES);
                                    setColumnExpandRatio(Resource.SUMMARY, 1);
                                }
                            });
                        }
                    });
                }
            });
            disclosure.open();
        }
    }

}
