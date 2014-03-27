package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.components.*;
import com.tomitribe.tribestream.registry.model.GroupDto;
import com.tomitribe.tribestream.registry.model.RepositoryDto;
import com.tomitribe.tribestream.registry.model.ServiceDto;
import com.tomitribe.wadl.api.Resource;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.jouni.animator.Disclosure;

import java.util.List;
import java.util.Map;

import static com.tomitribe.tribestream.registry.TribestreamTheme.Sizes;
import static com.tomitribe.tribestream.registry.TribestreamTheme.StyleNames;

public class RepositoryView extends TVerticalLayout implements View {
    private Navigator navigator;
    private RepositoryDto repo;

    public RepositoryView(final RepositoryDto repo, Navigator navigator) {
        this.repo = repo;
        this.navigator = navigator;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(new TBreadcrumbTrail(navigator));
        addComponent(new THorizontalLayout() {
            {
                addStyleName(StyleNames.HEADER);
                setWidth(Sizes.FULL);

                TextField search;

                addComponent(new TLabel(repo.getName()) {
                    {
                        addStyleName(StyleNames.H1);
                    }
                });
                addComponent(new TSpacer());
                addComponent(search = new TSearchField("Search " + repo.getName() + "…"));

                search.setWidth(Sizes.FULL);
                setExpandRatio(search, 1);
            }
        });
        addComponent(new TLabel(repo.getDescription()) {
            {
                addStyleName(StyleNames.SUB_HEADER);
                setWidth(Sizes.FULL);
            }
        });
        addComponent(new TVerticalLayout() {
            {
                for (final Map.Entry<String, GroupDto> entry : repo.getGroups().entrySet()) {
                    addComponent(new Disclosure(entry.getKey()) {
                        {
                            setWidth(Sizes.FULL);

                            setContent(new TVerticalLayout() {
                                {
                                    setWidth(Sizes.FULL);

                                    final GroupDto group = entry.getValue();

                                    addComponent(new TLabel(group.getDescription()));
                                    addComponent(new Table() {
                                        {
                                            List<ServiceDto> resources = group.getServiceDtos();

                                            setWidth(Sizes.FULL);
                                            setHeight(Sizes.tableHeight(resources.size()));

                                            setContainerDataSource(new ResourceContainer(resources));
                                            setVisibleColumns("secure", "verb", "path", "summary");
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            }
        });
    }

}
