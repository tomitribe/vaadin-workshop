package com.tomitribe.tribestream.registry.views;

import com.tomitribe.tribestream.registry.model.ServiceDto;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;

import java.util.Collection;
import java.util.List;

public class ResourceContainer extends BeanItemContainer<Resource> {
    public ResourceContainer(Navigator navigator, Collection<ServiceDto> dtos) {
        super(Resource.class);
        for (ServiceDto dto : dtos) {
            addBean(new Resource(navigator, dto));
        }
    }
}
