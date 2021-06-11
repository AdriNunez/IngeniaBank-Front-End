package com.example.application.views.movimientos;


import com.example.application.backend.model.Movimiento;
import com.example.application.backend.service.MovimientoService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;


import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Route(value = "Movimientos/:samplePersonID?/:action?(edit)", layout = MainView.class)
@PageTitle("Bienvenido/a a tu banca")
public class MovimientosView extends HorizontalLayout {
    private Icon arrow;
    List<Movimiento> movimientosList = new ArrayList<>();
    ListDataProvider<Movimiento> movimientosProvider;

    Grid<Movimiento> grid = new Grid<>(Movimiento.class);

    private MovimientoService movimientoService;

    public MovimientosView(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
        this.movimientosList= movimientoService.findMovimientos();

        loadGrid();



        grid.setColumns("tarjeta.id","importe","concepto","fechaValor");
//        grid.addColumn(movimiento -> hideCardNumber(movimiento.getTarjeta().toString())).setHeader("Tarjeta");
        grid.addComponentColumn(item -> iconoArrow(grid,item)).setFlexGrow(1).setWidth("120px").setHeader("");
        grid.getColumnByKey("tarjeta.id").setHeader("Tarjeta");
        grid.getColumnByKey("importe").setHeader("Importe");
        grid.getColumnByKey("concepto").setHeader("Concepto");
        grid.getColumnByKey("fechaValor").setHeader("Fecha");
        HorizontalLayout layout = new HorizontalLayout();
        add(new H1("Movimientos"));
        add(new Hr());


// shorthand methods for changing the component theme variants
        layout.setPadding(false);
        layout.setMargin(true);
// just a demonstration of the API, by default the spacing is on
        layout.setSpacing(true);

        add(layout);
        addClassName("inicio-view");

        HorizontalLayout layout2 = new HorizontalLayout();
        layout2.setPadding(false);
        layout2.setSpacing(false);
        layout2.setMargin(false);
        layout2.add(grid);
        add(layout2);




    }
    @PostConstruct
    public void init() {

        List<Movimiento> movimientosList = new ArrayList<>();
        ListDataProvider<Movimiento> movimientosProvider;

    }

    private Icon iconoArrow(Grid<Movimiento> grid, Movimiento item) {
        if(item.getImporte()>0d){

            arrow = new Icon(VaadinIcon.ARROW_CIRCLE_DOWN);
            arrow.setColor("green");

        }else{
            arrow = new Icon(VaadinIcon.ARROW_CIRCLE_UP);
            arrow.setColor("red");
        }
        return arrow;
    }


    private void loadGrid()
    {
        movimientosProvider = DataProvider.ofCollection(this.movimientosList);
        movimientosProvider.setSortOrder(Movimiento::getFecha, SortDirection.DESCENDING);

        grid.setDataProvider(movimientosProvider);
    }


}


