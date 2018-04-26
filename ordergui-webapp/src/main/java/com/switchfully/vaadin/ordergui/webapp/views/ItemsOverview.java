package com.switchfully.vaadin.ordergui.webapp.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

import java.util.Collection;

public class ItemsOverview extends CustomComponent implements View {

    public ItemsOverview(ItemResource itemResource) {

        Collection<Item> itemList = itemResource.getItems();
        BeanItemContainer<Item> container = new BeanItemContainer<Item>(Item.class, itemList);
        Grid itemsGrid = new Grid(container);
        itemsGrid.setColumns("name", "description", "price", "amountOfStock");
        itemsGrid.setContainerDataSource(container);
        itemsGrid.setSizeFull();

        HorizontalLayout overviewLayout = new HorizontalLayout(itemsGrid);
        overviewLayout.setSizeFull();
        setCompositionRoot(overviewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
