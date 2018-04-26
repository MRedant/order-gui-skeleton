package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.views.ItemCreation;
import com.switchfully.vaadin.ordergui.webapp.views.ItemUpdate;
import com.switchfully.vaadin.ordergui.webapp.views.ItemsOverview;
import com.vaadin.annotations.Theme;
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
    private String VIEW_ITEMS_VIEWITEMS = "viewItems";
    private String VIEW_ITEMS_ITEMCREATION = "itemCreation";
    private String VIEW_ITEMS_ITEMUPDATE = "itemUpdate";

    private Label title;
    private Button viewItems;
    private Button itemCreation;
    private Button itemUpdate;
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

        title = new Label("Item Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);
        title.setWidth("250px");

        viewItems = new Button("View Items", e -> getNavigator().navigateTo("viewItems"));
        viewItems.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        viewItems.setWidth("150px");

        itemCreation = new Button("Create Item", e -> getNavigator().navigateTo("itemCreation"));
        itemCreation.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        itemCreation.setWidth("150px");

        itemUpdate = new Button("Update Item", e -> getNavigator().navigateTo("itemUpdate"));
        itemUpdate.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        itemUpdate.setWidth("150px");

        menu = new HorizontalLayout(title, viewItems, itemCreation, itemUpdate);
        menu.setExpandRatio(itemUpdate, 1.0f);
        menu.addStyleName(ValoTheme.MENU_ROOT);
        menu.setHeight("41px");
        menu.setWidth("100%");
        menu.setSpacing(true);

        viewContainer = new HorizontalLayout();
        viewContainer.setSizeFull();

        mainLayout = new VerticalLayout(menu, viewContainer);
        mainLayout.setExpandRatio(viewContainer, 1.0f);
        mainLayout.setSizeFull();

        setContent(mainLayout);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView(VIEW_ITEMS_HOME, new ItemsOverview(itemResource));
        navigator.addView(VIEW_ITEMS_VIEWITEMS, new ItemsOverview(itemResource));
        navigator.addView(VIEW_ITEMS_ITEMCREATION, ItemCreation.class);
        navigator.addView(VIEW_ITEMS_ITEMUPDATE, ItemUpdate.class);

    }

}