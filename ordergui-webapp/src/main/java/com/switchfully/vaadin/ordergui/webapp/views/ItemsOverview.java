package com.switchfully.vaadin.ordergui.webapp.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;

import java.util.Collection;

public class ItemsOverview extends CustomComponent implements View {

    private Collection<Item> itemList;
    private BeanItemContainer<Item> container;
    private Grid itemsGrid;
    private HorizontalLayout overviewLayout;
    private Button editButton;
   private OrderGUI orderGui;

    public ItemsOverview(ItemResource itemResource, OrderGUI orderGui ) {
        editButton = new Button("edit");
        itemList = itemResource.getItems();

        this.orderGui= orderGui;

        container = new BeanItemContainer<Item>(Item.class, itemList);

        GeneratedPropertyContainer editContainer = new GeneratedPropertyContainer(container);
        editContainer.addGeneratedProperty("edit", new PropertyValueGenerator<String>() {
            @Override
            public String getValue(com.vaadin.data.Item item, Object itemId, Object propertyId) {
                return "edit";
            }

            @Override
            public Class<String> getType() {
                return String.class;
            }
        });

        itemsGrid = new Grid(editContainer);
        itemsGrid.setColumns("name", "description", "price", "amountOfStock", "edit");
        itemsGrid.setContainerDataSource(editContainer);
        itemsGrid.setSizeFull();
        itemsGrid.getColumn("edit").setRenderer(
                new ButtonRenderer((ClickableRenderer.RendererClickListener) event -> updateItem((Item) event.getItemId())));
        overviewLayout = new HorizontalLayout(itemsGrid);
        overviewLayout.setSizeFull();
        setCompositionRoot(overviewLayout);
    }

    private void updateItem(Item item) {
//        Notification.show("SAVED!", "item"+item.getId(),Notification.Type.HUMANIZED_MESSAGE);
        orderGui.getNavigator().addView(orderGui.getVIEW_ITEMS_ITEMUPDATE(),new ItemUpdate(item));
        orderGui.getNavigator().navigateTo(orderGui.getVIEW_ITEMS_ITEMUPDATE());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
