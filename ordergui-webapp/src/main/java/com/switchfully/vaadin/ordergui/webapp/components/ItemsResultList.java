//package com.switchfully.vaadin.ordergui.webapp.components;
//
//import com.switchfully.vaadin.ordergui.interfaces.items.Item;
//import com.vaadin.ui.CustomComponent;
//import com.vaadin.ui.VerticalLayout;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Consumer;
//
//public class ItemsResultList extends CustomComponent {
//
//    private VerticalLayout list;
//    private List<Item> items = new ArrayList<>();
//    private List<Consumer<Item>> itemClickListeners = new ArrayList<>();
//
//    public ItemsResultList() {
//        list = new VerticalLayout();
//
//        setCompositionRoot(list);
//    }
//
//    public void setItems(List<Item> items){
//        this.items = items;
//        refreshList();
//    }
//
//    private void refreshList() {
//        this.list.removeAllComponents();
//        this.items
//                .stream()
//                .map(ItemResult::new)
//                .forEach(component -> {
//                    component.addClickListener(event -> ItemsResultList.this.itemClickListeners
//                    .forEach(icl -> icl.accept(component.getItem())));
//                });
//    }
//
//    public void addItemClickListener(Consumer<Item> itemClickListener){
//        this.itemClickListeners.add(itemClickListener);
//    }
//
//}
