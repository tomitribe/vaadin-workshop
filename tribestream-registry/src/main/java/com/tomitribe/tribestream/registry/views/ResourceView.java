package com.tomitribe.tribestream.registry.views;

import com.porotype.iconfont.FontAwesome;
import com.tomitribe.tribestream.registry.TribestreamTheme;
import com.tomitribe.tribestream.registry.components.*;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Table;

import static com.tomitribe.tribestream.registry.TribestreamTheme.*;

public class ResourceView extends TVerticalLayout implements View {
    private String resourceName;
    private Navigator navigator;

    public ResourceView(String resourceName, Navigator navigator) {
        this.resourceName = resourceName;
        this.navigator = navigator;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addStyleName(StyleNames.VIEW);
        addStyleName(StyleNames.RESOURCE_VIEW);

        THorizontalLayout body;

        addComponent(new TBreadcrumbTrail(navigator));
        addComponent(body = new THorizontalLayout() {
            {
                addStyleName(StyleNames.BODY);
                setDefaultComponentAlignment(Alignment.TOP_LEFT);

                TVerticalLayout main;
                TVerticalLayout side;

                addComponent(main = new TVerticalLayout() {
                    {
                        addStyleName(StyleNames.MAIN_COLUMN);

                        addComponent(new THorizontalLayout() {
                            {
                                addStyleName(StyleNames.HEADER);
                                setWidth(Sizes.FULL);

                                TSpacer spacer;

                                addComponent(new THeading(resourceName));
                                addComponent(spacer = new TSpacer());
                                addComponent(new TButton(FontAwesome.Icon.bolt, "Try me!"));

                                expand(spacer, this);
                            }
                        });

                        //FIXME: inject with real data
                        addComponent(new TLabel("Updated on Thu, 2013-06-20 14:39") {
                            {
                                addStyleName(StyleNames.BYLINE);
                            }
                        });
                        addComponent(new TBodyText(
                                "Returns the 20 most recent mentions (tweets containing a users's @screen_name) for the"
                                        + " authenticating user.\n"
                                        + " \n"
                                        + "The timeline returned is the equivalent of the one seen when you view your"
                                        + " mentions on twitter.com.\n"
                                        + " \n"
                                        + "This method can only return up to 800 tweets.\n"
                                        + " \n"
                                        + "See Working with Timelines for instructions on traversing timelines."
                        ));
                        addComponent(new THeading("Resource URL", StyleNames.H2));
                        addComponent(new TLabel("https://api.twitter.com/1.1/statuses/mentions_timeline.json"));
                        addComponent(new THorizontalLayout() {
                            {
                                addStyleName(StyleNames.WRAPPER);
                                setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
                                setWidth(Sizes.FULL);

                                TSpacer spacer;

                                addComponent(new THeading("Parameters", StyleNames.H2));
                                addComponent(spacer = new TSpacer());
                                addComponent(new TLabel("* Required") {
                                    {
                                        addStyleName(StyleNames.REQUIRED);
                                    }
                                });

                                expand(spacer, this);
                            }
                        });
                        addComponent(new Table() {
                            {
                                BeanItemContainer<Parameter> container;

                                //FIXME
                                setContainerDataSource(
                                        container = new BeanItemContainer<Parameter>(Parameter.class) {
                                            {
                                                addBean(new Parameter("id", "int", "The tweet ID to look for."));
                                                addBean(new Parameter("name", "string",
                                                        "Looks like it's the name or so." +
                                                                "<p><b>Example values:</b> 1234</p>"
                                                ));
                                                addBean(new Parameter("foobar", "date",
                                                        "Here is something else probably."));
                                            }
                                        }
                                );
                                addGeneratedColumn("description", new ColumnGenerator() {
                                    @Override
                                    public Object generateCell(Table source, Object itemId, Object columnId) {
                                        return new TLabel(((Parameter)itemId).getDescription()) {
                                            {
                                                setContentMode(ContentMode.HTML);
                                            }
                                        };
                                    }
                                });

                                setVisibleColumns("name", "type", "description");
                                setColumnExpandRatio("description", 1);
                                setWidth(Sizes.FULL);
                            }
                        });

                    }
                });
                addComponent(side = new TVerticalLayout() {
                    {
                        addStyleName(StyleNames.SIDE_COLUMN);

                        addComponent(new FormLayout() {
                            {
                                setCaption("Additional information");

                                //FIXME: Inject real data
                                addComponent(new TLabel("Rate limited?", "Yes"));
                                addComponent(new TLabel("Requests per rate limit window", "15/user<br>60/app"));
                                addComponent(new TLabel("Authentication", "Required"));
                                addComponent(new TLabel("Response formats", "JSON"));
                                addComponent(new TLabel("HTTP methods", "GET"));
                                addComponent(new TLabel("Resource family", "Statuses"));
                                addComponent(new TLabel("Response object", "<a href=''>Tweets</a>"));
                                addComponent(new TLabel("API version", "1.1"));
                            }
                        });
                    }
                });

                main.setSizeUndefined();
                side.setSizeUndefined();
            }
        });

        body.setSizeUndefined();
        setSizeUndefined();
    }
}
