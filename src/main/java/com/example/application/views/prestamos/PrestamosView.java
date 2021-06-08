package com.example.application.views.prestamos;

import com.example.application.backend.model.Cuenta;
import com.example.application.backend.model.Prestamo;
import com.example.application.backend.service.CuentaService;
import com.example.application.backend.service.PrestamoService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

@Route(value = "Prestamos", layout = MainView.class)
@PageTitle("Pide tu préstamo con facilidad")
public class PrestamosView  extends VerticalLayout {

    CuentaService cuentaService;
    PrestamoService prestamoService;
    Prestamo prestamo;

//    private Binder<Prestamo> prestamoBinder = new BeanValidationBinder<Prestamo>(Prestamo.class);



    public PrestamosView(CuentaService cuentaService,PrestamoService prestamoService) {
        this.setSizeFull();
        this.setPadding(true);

        this.cuentaService = cuentaService;
        this.prestamoService = prestamoService;

//        prestamoBinder.bindInstanceFields(this);

       // TEXTO Y HR
        add(new H1("Préstamos IngeniaBank"));
        add(new Hr());
        add(new H3("Rellena el formulario a continuación :"));

        add(createPreview());
        add(new Hr());

    }
    //para bindear//
    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
        //prestamoBinder.readBean(prestamo);
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }
    private Component createPreview() {
        VerticalLayout ver = new VerticalLayout();
        HorizontalLayout row = new HorizontalLayout();
        HorizontalLayout row2 = new HorizontalLayout();
        //combo duracion
        ComboBox<Integer> duracion = new ComboBox<>();
        duracion.setLabel("Duracion");
        duracion.setItems(1,2,3,4,5,6,7,8,9,10,11,12);
        Div value = new Div();
        value.setText("Select a value");
        duracion.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                value.setText("No option selected");
            } else {
                value.setText("Selected: " + event.getValue());


            }
        });
        duracion.setPlaceholder("tiempo");

        ComboBox<String> periodoCbx = new ComboBox<>();
        periodoCbx.setLabel("Meses o Años");
        periodoCbx.setItems("M","A");
        Div vauluep = new Div();
        value.setText("Select a value");
        periodoCbx.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                vauluep.setText("No option selected");
            } else {
                vauluep.setText("Selected: " + event.getValue());


            }
        });



        duracion.setPlaceholder("Número de meses o años");
        NumberField bigDecimalField = new NumberField("Importe del préstamo");
        bigDecimalField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        bigDecimalField.setSuffixComponent(new Icon(VaadinIcon.EURO));



        bigDecimalField.addValueChangeListener(e -> {

            if (e.getValue() == null) {
                Notification.show("Obligatorio valor");
            } else {
                Double cantidadc = e.getValue();
                System.out.println("kkkkk"+ cantidadc);
            }

        });

        //trabajo con los datos recogidos
        Button preview = new Button("Pide tu préstamo", clickEvent -> {
            // define form dialog
            CuotaSimulPreview simulPrestaView = new CuotaSimulPreview(duracion.getValue(),periodoCbx.getValue(),bigDecimalField.getValue(),this.prestamoService);
            simulPrestaView.setWidth("700px");
            simulPrestaView.setCloseOnEsc(true);
            simulPrestaView.setCloseOnOutsideClick(false);
            // bind form dialog with product entity


            // define form dialog view callback

            // open form dialog view
            simulPrestaView.open();
        });

        ComboBox<Cuenta> cuentaIngreso = new ComboBox<>();
        cuentaIngreso.setLabel("Cuenta de Ingreso");
        List<Cuenta> cuentaList = cuentaService.findAll();
        cuentaIngreso.setItems(cuentaList);
        cuentaIngreso.setItemLabelGenerator(Cuenta::getTipocuenta);
        Div valuep = new Div();
        value.setText("Select a value");
        cuentaIngreso.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                vauluep.setText("No option selected");
            } else {
                vauluep.setText("Selected: " + event.getValue());


            }
        });

        ComboBox<Cuenta> cuentaCobro = new ComboBox<>();
        cuentaCobro.setLabel("Cuenta de Cobro");
        cuentaCobro.setItems(cuentaList);
        cuentaCobro.setItemLabelGenerator(Cuenta::getTipocuenta);
        value.setText("Select a value");
        cuentaCobro.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                vauluep.setText("No option selected");
            } else {
                vauluep.setText("Selected: " + event.getValue());


            }
        });

        Image ingeniaImage = new Image("images/logoB.png", " logo");
        ingeniaImage.getElement().getStyle().set("display", "block");
        ingeniaImage.getElement().getStyle().set("margin-left", "auto");
        ingeniaImage.getElement().getStyle().set("margin-right", "auto");
        ingeniaImage.getElement().getStyle().set("width", "5%");
        Hr hr = new Hr();

        row.add(duracion,periodoCbx,bigDecimalField,cuentaIngreso,cuentaCobro);
        row2.add();
        ver.add(row,row2,preview,hr,ingeniaImage);
        return ver;
    }


}
