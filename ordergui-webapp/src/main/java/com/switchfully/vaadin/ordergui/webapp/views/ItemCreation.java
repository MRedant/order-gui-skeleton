package com.switchfully.vaadin.ordergui.webapp.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.converter.StringToDoubleConverter;
import com.vaadin.data.util.converter.StringToFloatConverter;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.FloatRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import static com.vaadin.event.ShortcutAction.*;

public class ItemCreation extends CustomComponent implements View {


    private ItemResource itemResource;

    private VerticalLayout viewContainer;
    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private TextField price = new TextField("€ - Price");
    private TextField amountOfStock = new TextField("Amount of stock");
    private Button createButton = new Button("Create");
    private Button cancelButton = new Button("Cancel");
    private Item item = new Item();
    final BeanFieldGroup<Item> binder = new BeanFieldGroup<>(Item.class);

    //    @Autowired
    public ItemCreation(ItemResource itemResource) {
        this.itemResource = itemResource;

        BeanFieldGroup.bindFieldsUnbuffered(this.item,this);

        Label newItem = new Label("New item");
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
        price.setNullRepresentation("0.00");
        price.setRequired(true);
        price.addValidator(new FloatRangeValidator("Price has to be above 0", 0.00f, Float.MAX_VALUE));

        amountOfStock.setInputPrompt("0");
        amountOfStock.setRequired(true);

        createButton.setClickShortcut(KeyCode.ENTER);
        createButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        createButton.addClickListener(e -> save());

        HorizontalLayout priceAmount = new HorizontalLayout(price, amountOfStock);
        priceAmount.setSpacing(true);
        HorizontalLayout buttons = new HorizontalLayout(createButton, cancelButton);
        buttons.setSpacing(true);

        viewContainer = new VerticalLayout(name, description, priceAmount, buttons);
        viewContainer.setSpacing(true);
        setCompositionRoot(viewContainer);
    }

    private void save() {
        try {
            binder.commit();
            itemResource.create(item);
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
