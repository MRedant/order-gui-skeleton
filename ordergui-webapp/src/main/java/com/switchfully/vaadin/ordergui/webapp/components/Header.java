//package com.switchfully.vaadin.ordergui.webapp.components;
//
//
//import com.vaadin.ui.*;
//import com.vaadin.ui.themes.ValoTheme;
//
//public class Header extends CustomComponent {
//
//    private final MenuBar menu = new MenuBar();
//    private final HorizontalLayout header = new HorizontalLayout();
//    private MenuBar.MenuItem items;
//    private MenuBar.MenuItem customers;
//    private MenuBar.MenuItem orders;
//
//
//    private final MenuBar.Command menuCommand = selectedItem
//            -> Notification.show("Action " + selectedItem.getText()
//            , Notification.Type.TRAY_NOTIFICATION);
//
//
//    public Header() {
//
//        header.setWidth("100%");
//        menu.setWidth("100%");
//        menu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
//
//        items = menu.addItem("Items", null, null);
//        customers = menu.addItem("Customers", null, null);
//        orders = menu.addItem("Orders", null, null);
//
//        header.addComponent(menu);
//        setCompositionRoot(header);
//    }
//
//
//}
