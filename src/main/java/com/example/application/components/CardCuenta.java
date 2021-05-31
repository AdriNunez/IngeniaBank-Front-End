package com.example.application.components;

import com.example.application.backend.service.CuentaService;
import com.example.application.backend.service.MovimientoService;
import com.example.application.backend.model.Cuenta;
import com.github.appreciated.card.ClickableCard;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Locale;

public class CardCuenta extends ClickableCard {
    private Double saldo;
    private String mascara;
    public CardCuenta(Cuenta cuenta, CuentaService cuentaService, MovimientoService movimientoService) {
        super(event -> {
            new CuentaDialog(cuenta,cuentaService, movimientoService,cuenta.getId()).open();

        });
        recuperaSaldo(cuenta,cuentaService);
        // estilo del card

        this.setWidth("300px");
        this.setHeight("150px");
        this.getElement().getStyle().set("radius", "24px");

        // layout principal que contendrá los layouts posteriores
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        // layout con el logo de ingenia bank
        HorizontalLayout imagenLayout = new HorizontalLayout();
        Image ingeniaImage = new Image("images/logo.png", " logo");

        ingeniaImage.setWidth("70px");
        ingeniaImage.setHeight("50px");
        ingeniaImage.setHeight("18px");
        imagenLayout.add(ingeniaImage);

        // numerocuenta y tipo de cuenta
        HorizontalLayout numcLayout = new HorizontalLayout();
        Span numcSpan = new Span();

        numcSpan.add(cuenta.getTipocuenta().toUpperCase(Locale.ROOT)+"  " + cuenta.getNumerocuenta());

        numcLayout.add(numcSpan);


        // layout con el saldo de la cuenta
        HorizontalLayout saldoLayout = new HorizontalLayout();
        addClassName("saldo");
        Span saldoSpan = new Span();
        saldoSpan.add(saldo + " €");
        saldoLayout.add(saldoSpan);


        // añadimos todos los layouts horizontales al layout principal
        layout.add(imagenLayout);
        layout.add(numcLayout);
        layout.add(saldoLayout);

        add(layout);


    }

    private Double recuperaSaldo(Cuenta cuenta, CuentaService cuentaService) {
         saldo = cuentaService.getSaldoTotalCuenta(cuenta.getId());

        return  saldo;
    }

    private String hideCardNumber(String cardNumber){
        return "**** " + cardNumber.substring(Integer.parseInt(cardNumber.substring(cardNumber.length() - 2)));
    }


}

