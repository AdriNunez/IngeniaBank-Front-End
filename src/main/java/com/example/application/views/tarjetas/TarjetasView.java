package com.example.application.views.tarjetas;

import java.util.Arrays;
import java.util.List;

import com.example.application.backend.model.Cuenta;
import com.example.application.backend.model.Tarjeta;
import com.example.application.backend.service.TarjetaService;
import com.example.application.backend.service.MovimientoService;
import com.example.application.backend.service.TarjetaService;
import com.example.application.components.CardCuenta;
import com.example.application.components.CardTarjeta;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "Tarjetas", layout = MainView.class)
@PageTitle("Bienvenido/a a tu banca")
public class TarjetasView extends HorizontalLayout  {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int NOTIFICATION_DEFAULT_DURATION = 5000;
    List<Tarjeta> tarjetas;

    private TarjetaService tarjetaService;
    private MovimientoService movimientoService;

    public TarjetasView(TarjetaService tarjetaService, MovimientoService movimientoService) {
        this.tarjetaService = tarjetaService;
        this.movimientoService = movimientoService;
        addClassName("tarjetas-view");
        setSizeFull();
        //cargar datos desde repostorio
        loadData();
        //añadir las cuentas en componente card
        add(createCard());




    }

    private HorizontalLayout createCard() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        add(new H1("Tarjetas"));
        add(new Hr());
        for (Tarjeta tarjeta : this.tarjetas) {


            if(tarjeta.getNumeroTarjeta()!=4441){
            horizontalLayout.add(new CardTarjeta(tarjeta, this.tarjetaService, this.movimientoService));

                }
        }

        return horizontalLayout;
    }


    private void loadData() {
        this.tarjetas = tarjetaService.findTarjetas();
    }




}
