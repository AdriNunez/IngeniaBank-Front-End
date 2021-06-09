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
import com.vaadin.flow.component.grid.Grid;
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
    List<Cuenta> cuentas;
    Cuenta cuenta;
    Long numerocuentaCobro;
    Long numercuentaIngreso;
    private FormLayout formLayout;
    private ComboBox<Integer> duracion;
    ComboBox<String> periodoCbx;
    NumberField cantidadField;
    ComboBox<Cuenta> cuentaIngreso;
    ComboBox<Cuenta> cuentaCobro;
    private static final String PROP_ERROR = "error";
    private Binder<Prestamo> prestamoBinder = new Binder<>(Prestamo.class);



    public PrestamosView(CuentaService cuentaService,PrestamoService prestamoService) {
        this.setSizeFull();
        this.setPadding(true);

        this.cuentaService = cuentaService;
        this.prestamoService = prestamoService;

        loadData();
//        prestamoBinder.bindInstanceFields(this);

       // TEXTO Y HR
        add(new H1("Préstamos IngeniaBank"));
        add(new Hr());
        add(new H3("Rellena el formulario a continuación :"));

        add(createPreview());
        add(new Hr());

    }


    private void loadData() {
        cuentas = cuentaService.findAll();

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
        formLayout = new FormLayout();
        formLayout.setWidthFull();

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("1px", 1),
                new FormLayout.ResponsiveStep("600px", 2),
                new FormLayout.ResponsiveStep("700px", 3));

        VerticalLayout ver = new VerticalLayout();
        HorizontalLayout row = new HorizontalLayout();
        HorizontalLayout row2 = new HorizontalLayout();
        //combo duracion

        duracion = new ComboBox<>();
        duracion.setLabel("Duracion");

        duracion.setItems(1,2,3,4,5,6,7,8,9,10,11,12);
        duracion.setValue(1);
        duracion.setAutofocus(true);

        Div value = new Div();
        value.setText("Select a value");
        duracion.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                System.out.println("estoy aui?");


            } else {
                System.out.println("tengo cosilllas que no muestro?");
                value.setText("Selected: " + event.getValue());


            }
        });
        //esta es la opción del requerimiento.
        prestamoBinder.forField(duracion)
                .asRequired("Seleccione")
                .bind("duracion");
        
        //periodo
        periodoCbx = new ComboBox<>();
        periodoCbx.setLabel("Meses o Años");
        periodoCbx.setItems("M","A");
        periodoCbx.setValue("M");
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
        
        //cantidad
        cantidadField = new NumberField("Importe del préstamo");
        cantidadField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        cantidadField.setSuffixComponent(new Icon(VaadinIcon.EURO));
        prestamoBinder.forField(cantidadField)
                .asRequired("Obligatorio")
                .bind("cantidad");
        cantidadField.addValueChangeListener(e -> {
            System.out.println("hola cantidad");
            if (e.getValue() == null) {
                Notification.show("Obligatorio valor");
            } else {
                Double cantidadc = e.getValue();

            }

        });

        cuentaIngreso = new ComboBox<>();
        cuentaIngreso.setLabel("Cuenta de Ingreso");
        List<Cuenta> cuentaList = cuentaService.findAll();
        cuentaIngreso.setItems(cuentaList);
        cuentaIngreso.setItemLabelGenerator(Cuenta::getTipocuenta);
        Div valuep = new Div();
        value.setText("Select a value");
        cuentaIngreso. addValueChangeListener(event -> {
            if (event.getValue() == null) {
                vauluep.setText("No option selected");
            } else {
                vauluep.setText("Selected: " + event.getValue());


            }
            numercuentaIngreso = event.getValue().getNumerocuenta();
        });

        cuentaCobro = new ComboBox<>();

        cuentaCobro.setLabel("Cuenta de Cobro");
        cuentaCobro.setItems(cuentaList);
        cuentaCobro.setItemLabelGenerator(Cuenta::getTipocuenta);

        value.setText("Select a value");
        cuentaCobro.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                vauluep.setText("No option selected");
            } else {
                vauluep.setText("Selected: " + event.getValue());

                System.out.println(event.getValue().getNumerocuenta());



            }
            numerocuentaCobro = event.getValue().getNumerocuenta();
            System.out.println("333"+event.getValue().getNumerocuenta());
        });
        //trabajo con los datos recogidos
        Button preview = new Button("Pide tu préstamo", clickEvent -> {
            // define form dialog
            CuotaSimulPreview simulPrestaView = new CuotaSimulPreview(numerocuentaCobro,numercuentaIngreso,duracion.getValue(),periodoCbx.getValue(),cantidadField.getValue(),this.prestamoService);
            simulPrestaView.setWidth("700px");
            simulPrestaView.setCloseOnEsc(true);
            simulPrestaView.setCloseOnOutsideClick(false);
            // bind form dialog with product entity


            // define form dialog view callback

            // open form dialog view
            simulPrestaView.open();
        });


//        ComboBox<Cuenta> cuentaIngreso = new ComboBox<>();
//        cuentaIngreso.setLabel("Cuenta de Ingreso");
//        List<Cuenta> cuentaList = cuentaService.findAll();
//        cuentaIngreso.setItems(cuentaList);
//        cuentaIngreso.setItemLabelGenerator(Cuenta::getTipocuenta);
//        Div valuep = new Div();
//        value.setText("Select a value");
//        cuentaIngreso.addValueChangeListener(event -> {
//            if (event.getValue() == null) {
//                vauluep.setText("No option selected");
//            } else {
//                vauluep.setText("Selected: " + event.getValue());
//
//
//            }
//        });
//
//        ComboBox<Cuenta> cuentaCobro = new ComboBox<>();
//        cuentaCobro.setLabel("Cuenta de Cobro");
//        cuentaCobro.setItems(cuentaList);
//        cuentaCobro.setItemLabelGenerator(Cuenta::getTipocuenta);
//        value.setText("Select a value");
//        cuentaCobro.addValueChangeListener(event -> {
//            if (event.getValue() == null) {
//                vauluep.setText("No option selected");
//            } else {
//                vauluep.setText("Selected: " + event.getValue());
//
//
//            }
//        });

        Image ingeniaImage = new Image("images/logoB.png", " logo");
        ingeniaImage.getElement().getStyle().set("display", "block");
        ingeniaImage.getElement().getStyle().set("margin-left", "auto");
        ingeniaImage.getElement().getStyle().set("margin-right", "auto");
        ingeniaImage.getElement().getStyle().set("width", "5%");
        Hr hr = new Hr();

        row.add(duracion,periodoCbx,cantidadField,cuentaIngreso,cuentaCobro);
        row2.add();
        ver.add(row,row2,preview,hr,ingeniaImage);
        return ver;
    }

    public void setError(boolean error) {
        if (error) {
            setEnabled(true);
        }
        getElement().setProperty(PROP_ERROR, error);
    }

    public Grid createGrid(){
        Grid<Prestamo> grid = new Grid<>(Prestamo.class);
        grid.setColumns("cantidad","duracion");
        grid.setMaxWidth("500 px");
        return grid;
    }


}
