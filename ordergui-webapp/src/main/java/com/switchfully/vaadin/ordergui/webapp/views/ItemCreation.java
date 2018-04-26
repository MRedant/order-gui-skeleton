package com.switchfully.vaadin.ordergui.webapp.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class ItemCreation extends CustomComponent implements View {

    public ItemCreation() {
        setCompositionRoot(new Label("This is ItemCreation"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
