//package com.switchfully.vaadin.ordergui.webapp.components;
//
//import com.switchfully.vaadin.ordergui.interfaces.items.Item;
//import com.vaadin.event.MouseEvents;
//import com.vaadin.ui.CustomComponent;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.Panel;
//import com.vaadin.ui.VerticalLayout;
//
//import java.util.function.Consumer;
//
//public class ItemResult extends CustomComponent {
//
//    private Item item;
//    private final Panel panel;
//
//    public ItemResult(Item item) {
//        this.item = item;
//
//        VerticalLayout main = new VerticalLayout();
//
//        ItemDetails itemDetails = new ItemDetails(this.item);
//        itemDetails.setWidth("100%");
//
//        HorizontalLayout horizontalLayout = new HorizontalLayout(itemDetails);
//        horizontalLayout.setExpandRatio(itemDetails, 1.0f);
//        horizontalLayout.setWidth("100%");
//
//        main.addComponent(horizontalLayout);
//
//        panel = new Panel(main);
//        panel.setData(this.item);
//
//        setCompositionRoot(panel);
//    }
//
//    public void addClickListener (MouseEvents.ClickListener listener){
//        panel.addClickListener(listener);
//    }
//
//    public Item getItem() {
//        return item;
//    }
//}
