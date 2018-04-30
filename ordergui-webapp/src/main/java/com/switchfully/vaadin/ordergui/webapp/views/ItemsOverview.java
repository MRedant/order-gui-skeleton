package com.switchfully.vaadin.ordergui.webapp.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collection;

public class ItemsOverview extends CustomComponent implements View {

    private OrderGUI orderGui;

    private Collection<Item> itemList;
    private BeanItemContainer<Item> container;

    private Grid itemsGrid;
    private HorizontalLayout searchBar;
    private VerticalLayout overviewLayout;

    private Label itemOverview;
    private TextField searchField;
    private Button clearFilter;
    private Button newItem;

    public ItemsOverview(ItemResource itemResource, OrderGUI orderGui) {
        itemList = itemResource.getItems();
        this.orderGui = orderGui;

        buildGrid();
        setCompositionRoot(overviewLayout);
    }

    private void buildGrid() {
        container = new BeanItemContainer<>(Item.class, itemList);

        GeneratedPropertyContainer editContainer = new GeneratedPropertyContainer(container);
        editContainer.addGeneratedProperty("edit", editButtonGenerator());

        itemsGrid = new Grid(editContainer);
        itemsGrid.setColumns("name", "description", "price", "amountOfStock", "edit");
        itemsGrid.setContainerDataSource(editContainer);
        itemsGrid.setSizeFull();
        itemsGrid.getColumn("edit").setRenderer(
                new ButtonRenderer((ClickableRenderer.RendererClickListener) event -> updateItem((Item) event.getItemId())));

        overviewLayout = new VerticalLayout(buildSearchBar(), itemsGrid);
        overviewLayout.setSpacing(true);
        overviewLayout.setSizeFull();
    }

    private HorizontalLayout buildSearchBar() {
        itemOverview = new Label("Items");
        itemOverview.setStyleName(ValoTheme.LABEL_H2);

        searchField = new TextField("");
        searchField.setInputPrompt("Filter by name");
        searchField.setWidth("300px");
        searchField.addTextChangeListener(e -> {
            container.removeAllContainerFilters();
            container.addContainerFilter(
                    new SimpleStringFilter("name", e.getText(), true, false));
        });
        clearFilter = new Button(FontAwesome.TIMES);
        clearFilter.addClickListener(e -> {
            searchField.clear();
            container.removeAllContainerFilters();
        });
        CssLayout searchfieldAndButton = new CssLayout(searchField, clearFilter);
        searchfieldAndButton.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        newItem = new Button("New Item");
        newItem.addClickListener(e -> orderGui.getNavigator()
                .navigateTo(orderGui.getVIEW_ITEMS_ITEMCREATION()));

        searchBar = new HorizontalLayout(itemOverview, searchfieldAndButton, newItem);
        searchBar.setComponentAlignment(searchfieldAndButton, Alignment.MIDDLE_CENTER);
        searchBar.setComponentAlignment(newItem, Alignment.MIDDLE_RIGHT);
        searchBar.setSpacing(true);
        searchBar.setSizeFull();

        return searchBar;
    }

    private void updateItem(Item item) {
        orderGui.getNavigator()
                .navigateTo(orderGui.getVIEW_ITEMS_ITEMUPDATE() + "/" + item.getId());
    }

    private PropertyValueGenerator<String> editButtonGenerator() {
        return new PropertyValueGenerator<String>() {
            @Override
            public String getValue(com.vaadin.data.Item item, Object itemId, Object propertyId) {
                return "Edit";
            }

            @Override
            public Class<String> getType() {
                return String.class;
            }
        };
    }

    public void refreshGrid() {
        itemsGrid.refreshAllRows();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        refreshGrid();
    }
}
