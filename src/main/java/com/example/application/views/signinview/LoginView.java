package com.example.application.views.signinview;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Route("login")
@PageTitle("Sign In")
public class LoginView extends VerticalLayout {

    private final UserDetailsService userDetailsService;

    public LoginView(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;

        TextField emailField = new TextField("Email");
        PasswordField passwordField = new PasswordField("Password");

        Button loginButton = new Button("Log In", e -> {
            UserDetails userDetails;
            try {
                userDetails = userDetailsService.loadUserByUsername(emailField.getValue());
            } catch (UsernameNotFoundException ex) {
                Notification.show("User not found");
                return;
            }

            // Perform authentication here
            if (userDetails.getPassword().equals(passwordField.getValue())) {
                Notification.show("Login successful");
            } else {
                Notification.show("Incorrect password");
            }
        });

        add(emailField, passwordField, loginButton);
    }
}
