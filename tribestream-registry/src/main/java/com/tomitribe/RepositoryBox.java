package com.tomitribe;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;

public class RepositoryBox extends TVerticalLayout {
    public RepositoryBox(final Repository repo) {
        addStyleName(TribestreamTheme.REPOSITORY_BOX);
        setWidth(TribestreamTheme.Sizes.UNDEFINED);

        CssLayout header;

        addComponent(header = new CssLayout() {
            {
                addStyleName(TribestreamTheme.REPOSITORY_HEADER);
                setWidth(TribestreamTheme.Sizes.FULL);

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
