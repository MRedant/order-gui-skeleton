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

    private Collection<Item> itemList;
    private BeanItemContainer<Item> container;
    private Grid itemsGrid;
    private HorizontalLayout overviewLayout;

    public ItemsOverview(ItemResource itemResource) {

        itemList = itemResource.getItems();

        container = new BeanItemContainer<Item>(Item.class, itemList);

        itemsGrid = new Grid(container);
        itemsGrid.setColumns("name", "description", "price", "amountOfStock");
        itemsGrid.setContainerDataSource(container);
        itemsGrid.setSizeFull();

        overviewLayout = new HorizontalLayout(itemsGrid);
        overviewLayout.setSizeFull();
        setCompositionRoot(overviewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
