package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.views.ItemCreation;
import com.switchfully.vaadin.ordergui.webapp.views.ItemUpdate;
import com.switchfully.vaadin.ordergui.webapp.views.ItemsOverview;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class OrderGUI extends UI {

    private String VIEW_ITEMS_HOME = "";
    private String VIEW_ITEMS_ITEMOVERVIEW = "itemOverview";
    private String VIEW_ITEMS_ITEMCREATION = "itemCreation";
    private String VIEW_ITEMS_ITEMUPDATE = "itemUpdate";

    private ItemResource itemResource;
    private HorizontalLayout menu;
    private HorizontalLayout viewContainer;
    private VerticalLayout mainLayout;

    @Autowired
    public OrderGUI(ItemResource itemResource) {
        this.itemResource = itemResource;
    }

    @Override
    protected void init(VaadinRequest request) {

        Button order = new Button("Örder");
        order.addStyleName(ValoTheme.MENU_TITLE);
        order.setWidth("250px");
        order.addClickListener(event -> getNavigator().navigateTo(VIEW_ITEMS_ITEMOVERVIEW));

        MenuBar menubar = new MenuBar();
        MenuBar.MenuItem items = menubar.addItem("items",null,null);
        items.setStyleName(ValoTheme.MENUBAR_BORDERLESS);

        MenuBar.Command createCommand = (MenuBar.Command) selectedItem -> getNavigator().navigateTo(VIEW_ITEMS_ITEMCREATION);
        MenuBar.Command overviewCommand = (MenuBar.Command) selectedItem -> getNavigator().navigateTo(VIEW_ITEMS_ITEMOVERVIEW);
        MenuBar.Command updateCommand = (MenuBar.Command) selectedItem -> getNavigator().navigateTo(VIEW_ITEMS_ITEMUPDATE);


        MenuBar.MenuItem create = items.addItem("Create", null, createCommand);
        MenuBar.MenuItem overview = items.addItem("Overview", null, overviewCommand);
        MenuBar.MenuItem update = items.addItem("Update", null, updateCommand);

        menu = new HorizontalLayout(order,menubar);
        menu.setExpandRatio(menubar, 1.0f);
        menu.addStyleName(ValoTheme.MENU_ROOT);
        menu.setWidth("100%");
        menu.setSpacing(true);

        viewContainer = new HorizontalLayout();
        viewContainer.setSizeFull();

        mainLayout = new VerticalLayout(menu, viewContainer);
        mainLayout.setExpandRatio(viewContainer, 1.0f);
        mainLayout.setSizeFull();

        setContent(mainLayout);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView(VIEW_ITEMS_HOME, new ItemsOverview(itemResource, this));
        navigator.addView(VIEW_ITEMS_ITEMOVERVIEW, new ItemsOverview(itemResource, this));
        navigator.addView(VIEW_ITEMS_ITEMCREATION,new ItemCreation(itemResource));
//        navigator.addView(VIEW_ITEMS_ITEMUPDATE, ItemUpdate(item).class);

    }

    public String getVIEW_ITEMS_ITEMUPDATE() {
        return VIEW_ITEMS_ITEMUPDATE;
    }
}