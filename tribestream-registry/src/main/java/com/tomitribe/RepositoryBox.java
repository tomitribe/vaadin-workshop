package com.tomitribe;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

public class RepositoryBox extends TVerticalLayout {
    public RepositoryBox(final Repository repo) {
        addStyleName(TribestreamTheme.REPOSITORY_BOX);
        setWidth(TribestreamTheme.Size.UNDEFINED);

        addComponent(new CssLayout() {
            {
                addStyleName(TribestreamTheme.REPOSITORY_HEADER);

                addComponent(new Image(null, repo.getIcon()) {
                    {
                        addStyleName(TribestreamTheme.REPOSITORY_ICON);
                    }
                });
                addComponent(new TLabel(repo.getTitle()) {
                    {
                        addStyleName(TribestreamTheme.REPOSITORY_TITLE);
                    }
                });
            }
        });
        addComponent(new TLabel(repo.getDescription()) {
            {
                addStyleName(TribestreamTheme.REPOSITORY_DESCRIPTION);
            }
        });
        addComponent(new TLabel(repo.getSize() + " resources") {
            {
                addStyleName(TribestreamTheme.REPOSITORY_SIZE);
            }
        });
    }
}
