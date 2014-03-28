package com.tomitribe.tribestream.registry.views;

import com.porotype.iconfont.FontAwesome;
import com.tomitribe.tribestream.registry.components.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;

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
                        addComponent(new THeading("Resource URL"));
                        addComponent(new TLabel("https://api.twitter.com/1.1/statuses/mentions_timeline.json"));
                        addComponent(new THorizontalLayout() {
                            {
                                addStyleName(StyleNames.WRAPPER);

                                addComponent(new THeading("Parameters"));
                                addComponent(new TLabel("* Required") {
                                    {
                                        addStyleName(StyleNames.REQUIRED);
                                    }
                                });
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
