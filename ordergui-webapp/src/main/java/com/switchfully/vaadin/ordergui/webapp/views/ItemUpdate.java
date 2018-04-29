package com.switchfully.vaadin.ordergui.webapp.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemUpdate extends CustomComponent implements View {

    private Item item;

//    private ItemResource itemResource;

//    @Autowired
    public ItemUpdate() {
//        this.itemResource = itemResource;

//        setCompositionRoot(new Label("This is the item you clicked : \n"+item.getName()));

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        Item = event.getParameters()
    }
}
