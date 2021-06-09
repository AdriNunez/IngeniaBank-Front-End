package com.example.application.views.prestamos;

import com.example.application.backend.model.Prestamo;
import com.example.application.backend.service.CuentaService;
import com.example.application.backend.service.PrestamoService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuotaSimulPreview extends Dialog {

    CuentaService cuentaService;
    PrestamoService prestamoService;
    Prestamo prestamo;

    private Binder<Prestamo> prestamoBinder = new Binder<>(Prestamo.class);



    Double cantidad;
    Double mes;
    Integer tiempo ;
    String periodo;
    Long numerocuentaCobro;
    Long numerocuentaIngreso;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public static enum DIALOG_RESULT {SAVE, CANCEL}



    private DIALOG_RESULT dialogResult;



    public CuotaSimulPreview(Long numerocuentaCobro, Long numercuentaIngreso, Integer t, String p, Double value, PrestamoService prestamoService) {
        super();
        this.prestamoService = prestamoService;
        cantidad = value;
        tiempo = t;
        periodo = p;
        this.numerocuentaCobro = numerocuentaCobro;
        this.numerocuentaIngreso = numercuentaIngreso;
        System.out.println("entran los valores" + cantidad + tiempo + periodo +" hola numero de cuenta"+ this.numerocuentaCobro + " hola numero cuenta ingreso" + this.numerocuentaIngreso );
        cantidadMensual(cantidad,tiempo,periodo,this.prestamoService);

        // create dialog layout
        add(createTitle(), createCard(cantidad, tiempo, periodo), new Hr(), createToolbarLayout());

    }

    private void cantidadMensual(Double cantidad, Integer tiempo, String periodo, PrestamoService prestamoService) {

       mes = prestamoService.cantidadMensuanl(cantidad,tiempo,periodo);

    }

    public DIALOG_RESULT getDialogResult() {
        return this.dialogResult;
    }
    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
        prestamoBinder.readBean(prestamo);
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }
    private Component createToolbarLayout() {

        Button saveButton = new Button("Confirm", event -> {
            // retreive the product updated from form
            this.dialogResult = DIALOG_RESULT.SAVE;

//            if (productBinder.writeBeanIfValid(product))
            close();

        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClickShortcut(Key.ENTER).listenOn(this);
        saveButton.getElement().getStyle().set("margin-left", "auto");

        Button cancelButton = new Button("Cancel", event -> {
            this.dialogResult = DIALOG_RESULT.CANCEL;

            close();
        });

        HorizontalLayout formToolBar = new HorizontalLayout(saveButton, cancelButton);
        formToolBar.setWidthFull();
        formToolBar.getElement().getStyle().set("padding-top", "30px");

        return formToolBar;
    }


    private Component createTitle() {
        return new H1("Resultado Prestamo Simulacion");
    }

    private VerticalLayout createCard(Double b, Integer t, String p) {


        // layout principal que contendrá los layouts posteriores
        VerticalLayout card = new VerticalLayout();
        card.setWidth("100 px");
        // layout con el logo de ingenia bank
        HorizontalLayout imagenLayout = new HorizontalLayout();
        Image ingeniaImage = new Image("images/bbvacirc.png", " bbvacirc");

        imagenLayout.add(ingeniaImage);
        imagenLayout.getElement().getStyle().set("display", "block");
        imagenLayout.getElement().getStyle().set("margin-left", "auto");
        imagenLayout.getElement().getStyle().set("margin-right", "auto");
        imagenLayout.getElement().getStyle().set("width", "50%");
        imagenLayout.getElement().getStyle().set("opacity", "50%");

        // layout con el saldo de la cuenta
        HorizontalLayout cantidadMes = new HorizontalLayout();
        cantidadMes.getElement().getStyle().set("text-align","center");

        Span cantidadSpan = new Span();
        cantidadSpan.add(mes + " €/mes");
        cantidadSpan.getElement().getStyle().set("font-weight", "bold");

        cantidadMes.add(cantidadSpan);

        // layout con el datos de la cuenta
        HorizontalLayout prestamo = new HorizontalLayout();

        Span prestamoSpan = new Span();
        prestamoSpan.add("Cantidad del prestamo " + b + " €");
        prestamoSpan.getElement().getStyle().set("font-weight", "bold");
        prestamo.add(prestamoSpan);

        // layout con el datos de la cuenta
        HorizontalLayout interes = new HorizontalLayout();

        Span interesSpan = new Span();
        interesSpan.add("5% de Interés fijo.");
        prestamoSpan.getElement().getStyle().set("font-weight", "bold");
        interes.add(interesSpan);

        // layout con el datos de la cuenta
        HorizontalLayout plazo = new HorizontalLayout();

        Span plazoSpan = new Span();
        if(p =="A")
            p ="Años";
        if(p=="M")
            p= "Meses";
        plazoSpan.add("El plazo elegido " +t +" "+p);
        plazoSpan.getElement().getStyle().set("font-weight", "bold");
        plazo.add(plazoSpan);


        HorizontalLayout cuentaC = new HorizontalLayout();

        Span cuentaCobro = new Span();
        cuentaCobro.add("Número de Cuenta de cobro " + this.numerocuentaCobro);
        cuentaCobro.getElement().getStyle().set("font-weight", "bold");
        cuentaC.add(cuentaCobro);

        HorizontalLayout cuentaI =new HorizontalLayout();

        Span cuentaIngreso = new Span();
        cuentaIngreso.add("Número de Cuenta de Ingreso " + this.numerocuentaIngreso);
        cuentaIngreso.getElement().getStyle().set("font-weight", "bold");
        cuentaC.add(cuentaIngreso);

        card.add(prestamo);
        card.add(interes);
        card.add(plazo);
        card.add(cantidadMes);
        card.add(imagenLayout);
        card.add(cuentaC);
        card.add(cuentaI);

        return card;

    }




}






