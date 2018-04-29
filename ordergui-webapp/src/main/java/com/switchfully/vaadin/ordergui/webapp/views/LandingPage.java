package com.switchfully.vaadin.ordergui.webapp.views;

import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class LandingPage extends CustomComponent implements View {

    private final GridLayout grid;
    private final VerticalLayout loginWindow = new VerticalLayout();
    private final HorizontalLayout mainWindow = new HorizontalLayout();
    private final TextField loginField;
    private final PasswordField passwordField;
    private final Panel loginPanel = new Panel("Login");
    private final Button loginButton;
    private final OrderGUI orderGUI;

    public LandingPage(OrderGUI orderGUI) {

        this.orderGUI = orderGUI;

        grid = new GridLayout(3, 3);
        Label loginLabel = new Label("Username:");
        grid.addComponent(loginLabel, 0, 0);
        Label passwordLabel = new Label("Password:");
        grid.addComponent(passwordLabel, 0, 1);
        grid.setComponentAlignment(loginLabel, Alignment.BOTTOM_LEFT);
        grid.setComponentAlignment(passwordLabel, Alignment.BOTTOM_LEFT);
        grid.setSpacing(true);
        grid.setMargin(true);
        loginWindow.addComponent(grid);

        loginField = new TextField();
        loginField.setRequired(true);
        grid.addComponent(loginField, 1, 0);

        passwordField = new PasswordField();
        passwordField.setRequired(true);
        grid.addComponent(passwordField, 1, 1);

        loginButton = new Button("Login");
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        loginButton.addClickListener(e -> login());
        grid.addComponent(loginButton, 1, 2);

        loginWindow.setSpacing(true);

        loginPanel.setWidth("405px");
        loginPanel.setContent(loginWindow);

        mainWindow.addComponent(loginPanel);
        mainWindow.setSizeFull();
        mainWindow.setSpacing(true);
        mainWindow.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);

        setCompositionRoot(mainWindow);

    }

    private void login() {
        //todo : do actual logging in
        Notification.show("Logged in", "Welcome back " + loginField.getValue() + " !", Notification.Type.HUMANIZED_MESSAGE);
        orderGUI.setLogOutVisible(loginField.getValue());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
