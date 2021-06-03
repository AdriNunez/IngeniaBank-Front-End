package com.example.application.views.prestamos;

import com.example.application.backend.model.Cuenta;
import com.example.application.backend.service.CuentaService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

@Route(value = "Prestamos", layout = MainView.class)
@PageTitle("Pide tu préstamo con facilidad")
public class PrestamosView  extends VerticalLayout {
    CuentaService cuentaService;


    public PrestamosView(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
       // TEXTO Y HR
        add(new H1("Préstamos IngeniaBank"));
        add(new Hr());
        add(new H3("Rellena el formulario a continuación :"));

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        FormLayout columnLayout = new FormLayout();
        columnLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2),
                new FormLayout.ResponsiveStep("40em", 3));
        TextField cantidad = new TextField();
        cantidad.setLabel("Cantidad");
        Select<String> duracion = new Select<>();
        duracion.setItems("6 Meses", "1 Año", "2 Años");
        duracion.setPlaceholder("Duración");


        // SELECT INGRESO
        Select<Cuenta> cuentaIngreso = new Select<>();
        cuentaIngreso.setLabel("Cuenta de Ingreso");
        List<Cuenta> cuentaList = cuentaService.findAll();
        cuentaIngreso.setItemLabelGenerator(Cuenta::getTipocuenta);
        cuentaIngreso.setItems(cuentaList);

        // SELECT COBRO
        Select<Cuenta> cuentaCobro = new Select<>();
        cuentaCobro.setLabel("Cuenta de Cobro");
        cuentaCobro.setItemLabelGenerator(Cuenta::getTipocuenta);
        cuentaCobro.setItems(cuentaList);


        Select<String> tipo = new Select<>();
        tipo.setItems("Tipo fijo : 5%"); //ARREGLAR CUANDO esté getTipo()
        tipo.setPlaceholder("Tipo");

        TextField website = new TextField();
        website.setPlaceholder("Tipo de interés");

        columnLayout.add(cantidad, cuentaIngreso,cuentaCobro,duracion, tipo);
        // You can set the desired column span for the components individually.
        columnLayout.setColspan(tipo, 2);
        // Or just set it as you add them.

        horizontalLayout.add(columnLayout);
        horizontalLayout.setPadding(true);
        horizontalLayout.setSpacing(true);
        horizontalLayout.getElement().getStyle().set("text-align","center");
        horizontalLayout.getElement().getStyle().set("position","centered");
        horizontalLayout.getElement().getStyle().set("width","100%");

        add(horizontalLayout);

        ProgressBar progressBar = new ProgressBar();
        progressBar.setValue(0.345);
        add(progressBar);

    }

    
}
