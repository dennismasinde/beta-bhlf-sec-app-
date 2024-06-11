package com.example.application.views.signupview;

import com.example.application.entities.User;
import com.example.application.services.UserService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("SignUpView")
@Route(value = "signup")
@RouteAlias(value = "")
public class SignUpView extends Composite<VerticalLayout> {

    @Autowired
    private UserService userService;

    public SignUpView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField firstNameField = new TextField("First Name");
        TextField lastNameField = new TextField("Last Name");
        DatePicker dobPicker = new DatePicker("Birthday");
        TextField phoneField = new TextField("Phone Number");
        EmailField emailField = new EmailField("Email");
        TextField occupationField = new TextField("Occupation");
        PasswordField passwordField = new PasswordField("Password");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password");
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button("Save");
        Button buttonSecondary = new Button("Cancel");

        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(e -> {
            String password = passwordField.getValue();
            String confirmPassword = confirmPasswordField.getValue();

            if (!password.equals(confirmPassword)) {
                Notification.show("Passwords do not match!");
                return;
            }

            User user = new User();
            user.setFirstName(firstNameField.getValue());
            user.setLastName(lastNameField.getValue());
            user.setDob(dobPicker.getValue());
            user.setPhoneNumber(phoneField.getValue());
            user.setEmail(emailField.getValue());
            user.setOccupation(occupationField.getValue());
            user.setPassword(password);

            userService.registerUser(user);
            Notification.show("User registered successfully!");
        });

        buttonSecondary.addClickListener(e -> {
            firstNameField.clear();
            lastNameField.clear();
            dobPicker.clear();
            phoneField.clear();
            emailField.clear();
            occupationField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
        });

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Sign Up");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");

        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(firstNameField, lastNameField, dobPicker, phoneField, emailField, occupationField, passwordField, confirmPasswordField);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonPrimary, buttonSecondary);
    }
}
