package com.tomitribe.tribestream.registry.components;

import com.porotype.iconfont.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;

import static com.tomitribe.tribestream.registry.TribestreamTheme.StyleNames;

public class TSearchField extends com.vaadin.ui.CustomComponent {
    private TButton reset;
    private TextField textField;

    public TSearchField(final String inputPrompt) {
        addStyleName(StyleNames.SEARCH_FIELD);

        setCompositionRoot(new CssLayout() {
            {
                addComponent(new MenuBar() {
                    {
                        addStyleName(StyleNames.DROPDOWN);
                        setHtmlContentAllowed(true);

                        MenuItem menuItem = addItem("" + FontAwesome.Icon.caret_down, null);

                        //FIXME: use real filter options and replace null with Command
                        menuItem.addItem(FontAwesome.Icon.check + " checked", null);
                        menuItem.addItem(FontAwesome.Icon.check_empty + " unchecked", null);

                    }
                });
                addComponent(textField = new TextField() {
                    {
                        addStyleName(StyleNames.SEARCH);
                        setInputPrompt(inputPrompt);
                    }
                });

                addComponent(reset = new TButton(FontAwesome.Icon.remove));
            }
        });
    }

    public TextField getTextField() {
        return textField;
    }

    public TButton getReset() {
        return reset;
    }
}
