package com.switchfully.vaadin.ordergui.webapp.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class ItemUpdate extends CustomComponent implements View {

    public ItemUpdate() {
        setCompositionRoot(new Label("This is ItemUpdate"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
