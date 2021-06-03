package com.example.application.views.prestamos;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "Prestamos", layout = MainView.class)
@PageTitle("Pide tu préstamo con facilidad")
public class PrestamosView  extends HorizontalLayout {

    public PrestamosView() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        FormLayout columnLayout = new FormLayout();
// Setting the desired responsive steps for the columns in the layout
        columnLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2),
                new FormLayout.ResponsiveStep("40em", 3));
        TextField cantidad = new TextField();
        cantidad.setPlaceholder("First Name");
        Select<String> duracion = new Select<>();
        duracion.setItems("6 Meses", "1 Año", "2 Años");
        duracion.setPlaceholder("Duración");
        TextField email = new TextField();
        email.setPlaceholder("Email");
        TextField nickname = new TextField();
        nickname.setPlaceholder("Username");
        TextField website = new TextField();
        website.setPlaceholder("Link to personal website");
        TextField description = new TextField();
        description.setPlaceholder("Enter a short description about yourself");
        columnLayout.add(cantidad,  duracion, email, website);
        // You can set the desired column span for the components individually.
        columnLayout.setColspan(website, 2);
        // Or just set it as you add them.
        columnLayout.add(description, 3);
        horizontalLayout.add(columnLayout);
        add(horizontalLayout);


    }
}
