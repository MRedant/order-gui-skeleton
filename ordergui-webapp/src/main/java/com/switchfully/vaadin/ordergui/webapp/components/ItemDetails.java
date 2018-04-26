//package com.switchfully.vaadin.ordergui.webapp.components;
//
//import com.switchfully.vaadin.ordergui.interfaces.items.Item;
//import com.vaadin.ui.CustomComponent;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.Label;
//
//
//public class ItemDetails extends CustomComponent {
//
//    private Item item;
//
//    public ItemDetails(Item item) {
//        this.item = item;
//
//        Label name = new Label(item.getName());
//        Label description = new Label(item.getDescription());
//        Label price = new Label(String.valueOf(item.getPrice()));
//        Label amountOfStock = new Label(String.valueOf(item.getAmountOfStock()));
//
//        HorizontalLayout main = new HorizontalLayout(name, description, price, amountOfStock);
//
//    }
//}
