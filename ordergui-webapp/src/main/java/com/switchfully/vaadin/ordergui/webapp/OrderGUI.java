package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.views.ItemCreation;
import com.switchfully.vaadin.ordergui.webapp.views.ItemUpdate;
import com.switchfully.vaadin.ordergui.webapp.views.ItemsOverview;
import com.switchfully.vaadin.ordergui.webapp.views.LandingPage;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

@SpringUI
@Theme("valo")
public class OrderGUI extends UI {

    public static final String PICTURES_PATH = "./ordergui-webapp/src/main/pictures/";
    private File orderThumb = new File(PICTURES_PATH + "order.png");

    private String VIEW_ITEMS_HOME = "";
    private String VIEW_ITEMS_ITEMOVERVIEW = "itemOverview";
    private String VIEW_ITEMS_ITEMCREATION = "itemCreation";
    private String VIEW_ITEMS_ITEMUPDATE = "itemUpdate";

    private ItemResource itemResource;
    private HorizontalLayout menu;
    private HorizontalLayout viewContainer;
    private VerticalLayout mainLayout;

    private Button order;
    private MenuBar menubar;
    private MenuBar.MenuItem items;
    private MenuBar.MenuItem create;
    private MenuBar.MenuItem overview;

    @Autowired
    public OrderGUI(ItemResource itemResource) {
        this.itemResource = itemResource;
    }

    @Override
    protected void init(VaadinRequest request) {

        createPageLayout();

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView(VIEW_ITEMS_HOME, new LandingPage());
        navigator.addView(VIEW_ITEMS_ITEMOVERVIEW, new ItemsOverview(itemResource, this));
        navigator.addView(VIEW_ITEMS_ITEMCREATION, new ItemCreation(itemResource));
        navigator.addView(VIEW_ITEMS_ITEMUPDATE, new ItemUpdate());

    }

    private void createPageLayout() {
        createMenuBar();

        viewContainer = new HorizontalLayout();
        viewContainer.setSizeFull();

        mainLayout = new VerticalLayout(menu, viewContainer);
        mainLayout.setExpandRatio(viewContainer, 1.0f);
        mainLayout.setSizeFull();

        setContent(mainLayout);
    }

    private void createMenuBar() {
        order = new Button();
        order.setIcon(new FileResource(orderThumb), "order icon");
        order.addStyleName(ValoTheme.MENU_TITLE);
        order.setWidth("250px");
        order.addClickListener(event -> getNavigator().navigateTo(VIEW_ITEMS_HOME));

        menubar = new MenuBar();
        menubar.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        items = menubar.addItem("items", null, null);

        MenuBar.Command overviewCommand = (MenuBar.Command) selectedItem -> getNavigator().navigateTo(VIEW_ITEMS_ITEMOVERVIEW);
        overview = items.addItem("Overview", null, overviewCommand);
        MenuBar.Command createCommand = (MenuBar.Command) selectedItem -> getNavigator().navigateTo(VIEW_ITEMS_ITEMCREATION);
        create = items.addItem("Create", null, createCommand);

        menu = new HorizontalLayout(order, menubar);
        menu.setExpandRatio(menubar, 1.0f);
        menu.addStyleName(ValoTheme.MENU_ROOT);
        menu.setWidth("100%");
        menu.setSpacing(true);
    }

    public String getVIEW_ITEMS_ITEMUPDATE() {
        return VIEW_ITEMS_ITEMUPDATE;
    }
}