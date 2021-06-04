package com.example.application.views.prestamos;

import com.example.application.backend.service.PrestamoService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

public class CuotaSimulPreview extends Dialog {


    private PrestamoService prestamoService;
    BigInteger cantidad;
    Integer tiempo ;
    String periodo;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public static enum DIALOG_RESULT {SAVE, CANCEL}

    ;

    private DIALOG_RESULT dialogResult;

//    private Prestamo prestamo;

    public CuotaSimulPreview(Integer t, String p) {
        super();
        this.cantidad = cantidad;
        tiempo = t;
        periodo = p;
        System.out.println("entran los valores" + cantidad + tiempo + periodo);


        // create dialog layout
        add(createTitle(), createCard(cantidad, tiempo, periodo), new Hr(), createToolbarLayout());

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
        return new H3("Resultado Prestamo Simulacion");
    }

    private HorizontalLayout createCard(BigInteger b, Integer t, String p) {

        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");


        return card;

    }

    private void recuperarPrestamo(long l) {

        Double cantidad;
        Double interes;
        System.out.println("prestamo");
//        Optional<Prestamo> prestamodb = this.prestamoService.findById(1L);
//        if(prestamodb.isPresent()){
//            cantidad=prestamodb.get().getCantidad();
//            interes = prestamodb.get().getTipoInteres().getInteres();
//            System.out.println("cantidad"+cantidad);
//            System.out.println(interes);
//
//        }
//
//


}



        }


