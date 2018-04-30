package com.switchfully.vaadin.ordergui.webapp.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.switchfully.vaadin.ordergui.webapp.components.Converters;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.converter.StringToFloatConverter;
import com.vaadin.data.validator.FloatRangeValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.vaadin.event.ShortcutAction.KeyCode;
public class ItemCreation extends CustomComponent implements View {

    private final OrderGUI orderGUI;
    private ItemResource itemResource;
    private Item item = new Item();
    final BeanFieldGroup<Item> binder = new BeanFieldGroup<>(Item.class);

    private TextField name = new TextField("Name");
    private TextArea description = new TextArea("Description");
    private TextField price = new TextField("Price");
    private TextField amountOfStock = new TextField("Amount of stock");
    private Label newItem;
    private Button createButton = new Button("Create");
    private Button cancelButton = new Button("Cancel");


    public ItemCreation(ItemResource itemResource, OrderGUI orderGUI) {
        this.orderGUI = orderGUI;
        this.itemResource = itemResource;

        BeanFieldGroup.bindFieldsUnbuffered(this.item,this);

        newItem = new Label("New item");
        newItem.setStyleName(ValoTheme.LABEL_H2);

        name.setInputPrompt("The item name");
        name.setWidth("35em");
        name.setCursorPosition(0);
        name.addValidator(
                new NullValidator("Can't be empty", false));
        name.setRequired(true);

        description.setInputPrompt("The item description");
        description.setWidth("35em");
        description.setHeight("10em");
        description.addValidator((
                new NullValidator("Can't be empty", false)));
        description.setRequired(true);

        price.setConverter(Converters.getFloatConverter());
        price.setInputPrompt("0.00");
        price.setNullRepresentation("0.00");
        price.setRequired(true);
        price.setIcon(FontAwesome.EUR);
        price.addValidator(
                new FloatRangeValidator("Price has to be above 0", 0.00f, Float.MAX_VALUE));

        amountOfStock.setRequired(true);
        amountOfStock.setConverter(Converters.getIntegerConverter());
        amountOfStock.setValue("0");
        amountOfStock.setInputPrompt("0");
        amountOfStock.setNullRepresentation("0");
        amountOfStock.addValidator(
                new IntegerRangeValidator("Please enter a correct number", 0, Integer.MAX_VALUE));

        createButton.setClickShortcut(KeyCode.ENTER);
        createButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        createButton.addClickListener(e -> save());

        cancelButton.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        cancelButton.addClickListener(e -> orderGUI
                .getNavigator()
                .navigateTo(orderGUI.getVIEW_ITEMS_ITEMOVERVIEW()));

        HorizontalLayout priceAmount = new HorizontalLayout(price, amountOfStock);
        priceAmount.setSpacing(true);
        HorizontalLayout buttons = new HorizontalLayout(createButton, cancelButton);
        buttons.setSpacing(true);

        VerticalLayout viewContainer = new VerticalLayout(newItem, name, description, priceAmount, buttons);
        viewContainer.setSpacing(true);
        clearAllFields();
        setCompositionRoot(viewContainer);
    }

    private void save() {
        try {
            binder.commit();
            itemResource.create(item);
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
        Notification.show(
                "- SAVED -", "The item was created successfully",Notification.Type.HUMANIZED_MESSAGE);
        clearAllFields();
    }

    private void clearAllFields(){
        name.clear();
        description.clear();
        price.clear();
        amountOfStock.clear();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
