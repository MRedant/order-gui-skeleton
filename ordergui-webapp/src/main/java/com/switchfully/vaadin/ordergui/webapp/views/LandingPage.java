package com.switchfully.vaadin.ordergui.webapp.views;

import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.security.access.prepost.PreAuthorize;

public class LandingPage extends CustomComponent implements View {

    private final GridLayout grid;
    private final VerticalLayout splashImageHolder = new VerticalLayout();
    private final VerticalLayout loginWindow = new VerticalLayout();
    private final VerticalLayout mainWindow = new VerticalLayout();
    private final TextField loginField;
    private final PasswordField passwordField;
    private final Panel loginPanel = new Panel("Login");
    private final Button loginButton;
    private final OrderGUI orderGUI;
    private final Label loginLabel;
    private final Label passwordLabel;

    public LandingPage(OrderGUI orderGUI) {

        this.orderGUI = orderGUI;

        Image splashImage = new Image("",
                new FileResource(orderGUI.getOrderSplash()));
        splashImageHolder.addComponent(splashImage);
        splashImageHolder.setComponentAlignment(splashImage, Alignment.MIDDLE_CENTER);
        splashImageHolder.setWidth("405px");

        grid = new GridLayout(3, 3);
        grid.setSpacing(true);
        grid.setMargin(true);

        loginLabel = new Label("Username:");
        grid.addComponent(loginLabel, 0, 0);
        grid.setComponentAlignment(loginLabel, Alignment.BOTTOM_LEFT);

        passwordLabel = new Label("Password:");
        grid.addComponent(passwordLabel, 0, 1);
        grid.setComponentAlignment(passwordLabel, Alignment.BOTTOM_LEFT);

        loginWindow.addComponent(grid);

        loginField = new TextField();
        loginField.setRequired(true);
        loginField.setCursorPosition(0);
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

        mainWindow.addComponent(splashImageHolder);
        mainWindow.addComponent(loginPanel);
        mainWindow.setSizeFull();
        mainWindow.setSpacing(true);
        mainWindow.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        mainWindow.setComponentAlignment(splashImageHolder, Alignment.MIDDLE_CENTER);

        setCompositionRoot(mainWindow);

    }

    private void login() {
        //todo : do actual logging in
        Notification.show(
                "Logged in", "Welcome back " + loginField.getValue() + " !"
                , Notification.Type.HUMANIZED_MESSAGE);
        orderGUI.setLogOutVisible(loginField.getValue());
        orderGUI.getNavigator().navigateTo(orderGUI.getVIEW_ITEMS_ITEMOVERVIEW());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
