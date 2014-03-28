package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.components.*;
import com.tomitribe.tribestream.registry.model.GroupDto;
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.tomitribe.tribestream.registry.model.ServiceDto;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import org.vaadin.jouni.animator.Disclosure;

import java.util.List;
import java.util.Map;

import static com.tomitribe.tribestream.registry.TribestreamTheme.Sizes;
import static com.tomitribe.tribestream.registry.TribestreamTheme.StyleNames;
import static com.tomitribe.tribestream.registry.TribestreamTheme.expand;

public class RepositoryView extends TVerticalLayout implements View {
    private Navigator navigator;
    private RepositoryDto repo;

    public RepositoryView(final RepositoryDto repo, Navigator navigator) {
        this.repo = repo;
        this.navigator = navigator;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Panel content;

        addComponent(new TBreadcrumbTrail(navigator));
        addComponent(new THorizontalLayout() {
            {
                addStyleName(StyleNames.HEADER);
                setWidth(Sizes.FULL);

                TextField search;

                addComponent(new THeading(repo.getName()));
                addComponent(new TSpacer());
                addComponent(search = new TSearchField("Search " + repo.getName() + "…"));

                expand(search, this);
            }
        });
        addComponent(new TLabel(repo.getDescription()) {
            {
                addStyleName(StyleNames.SUB_HEADER);
                setWidth(Sizes.FULL);
            }
        });
        addComponent(content = new Panel(new TVerticalLayout() {
            {
                for (final Map.Entry<String, GroupDto> entry : repo.getGroupDto().entrySet()) {
                    addComponent(new Disclosure(entry.getKey()) {
                        {
                            setWidth(Sizes.FULL);

                            setContent(new TVerticalLayout() {
                                {
                                    setWidth(Sizes.FULL);

                                    final GroupDto group = entry.getValue();

                                    //FIXME
                                    addComponent(new TLabel("Group description goes here"));

                                    addComponent(new Table() {
                                        {
                                            List<ServiceDto> resources = group.getServiceDtos();

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
                }
            }
        }));

        expand(content, this);

        setSizeFull();
    }

}
