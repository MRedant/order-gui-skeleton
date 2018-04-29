package com.switchfully.vaadin.ordergui.webapp.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.converter.StringToFloatConverter;
import com.vaadin.data.validator.FloatRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class ItemUpdate extends CustomComponent implements View {

    private OrderGUI orderGUI;
    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private TextField price = new TextField("(€) Price");
    private TextField amountOfStock = new TextField("Amount of stock");
    private Button updateButton = new Button("Update");
    private Button cancelButton = new Button("Cancel");

    private ItemResource itemResource;
    private String itemId = "";
    private Item item = new Item();

    final BeanFieldGroup<Item> binder = new BeanFieldGroup<>(Item.class);

    public ItemUpdate(ItemResource itemResource, OrderGUI orderGUI) {
        this.itemResource = itemResource;
        this.orderGUI = orderGUI;

        BeanFieldGroup.bindFieldsUnbuffered(this.item, this);

        Label newItem = new Label("Update item");
        newItem.setStyleName(ValoTheme.LABEL_H1);

        name.setInputPrompt("The item name");
        name.setWidth("35em");
        name.addValidator(new NullValidator("Can't be empty", false));
        name.setRequired(true);

        description.setInputPrompt("The item description");
        description.setWidth("35em");
        description.setHeight("10em");
        description.addValidator((new NullValidator("Can't be empty", false)));
        description.setRequired(true);

        price.setConverter(new StringToFloatConverter());
        price.setInputPrompt("0.00");
        price.setRequired(true);
        price.addValidator(new FloatRangeValidator("Price has to be above 0", 0.00f, Float.MAX_VALUE));

        amountOfStock.setRequired(true);
        amountOfStock.setInputPrompt("0");

        updateButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        updateButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        updateButton.addClickListener(e -> update());

        cancelButton.addClickListener(e -> orderGUI.getNavigator().navigateTo(orderGUI.getVIEW_ITEMS_ITEMOVERVIEW()));

        HorizontalLayout priceAmount = new HorizontalLayout(price, amountOfStock);
        priceAmount.setSpacing(true);
        HorizontalLayout buttons = new HorizontalLayout(updateButton, cancelButton);
        buttons.setSpacing(true);

        VerticalLayout viewContainer = new VerticalLayout(name, description, priceAmount, buttons);
        viewContainer.setSpacing(true);

        setCompositionRoot(viewContainer);
    }

    private void update() {
        updateItem();
        try {
            binder.commit();
            itemResource.update(item);
            orderGUI.getNavigator().navigateTo(orderGUI.getVIEW_ITEMS_ITEMOVERVIEW());
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
        Notification.show("- UPDATED -", "The item was successfully updated", Notification.Type.HUMANIZED_MESSAGE);
        orderGUI.getNavigator().navigateTo(orderGUI.getVIEW_ITEMS_ITEMOVERVIEW());
    }

    private void updateItem() {
        item.setName(name.getValue());
        item.setDescription(description.getValue());
        item.setPrice(Float.valueOf(price.getValue()));
        item.setAmountOfStock(Integer.valueOf(amountOfStock.getValue()));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (event.getParameters() == null
                || event.getParameters().isEmpty()) {
            Notification.show("¯\\_(ツ)_/¯", "Something went wrong, no item identifier found.", Notification.Type.HUMANIZED_MESSAGE);
        } else {
            itemId = event.getParameters();
            item = itemResource.getItems().stream()
                    .filter(e -> e.getId().equals(itemId))
                    .findFirst().orElse(null);
            populateItemFields();
        }
    }

    private void populateItemFields() {
        name.setValue(item.getName());
        description.setValue(item.getDescription());
        price.setValue(String.valueOf(item.getPrice()));
        amountOfStock.setValue(String.valueOf(item.getAmountOfStock()));
    }
}
