package com.example.application.views.prestamos;

import com.example.application.backend.model.*;
import com.example.application.backend.service.*;
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
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Route(value = "Prestamos", layout = MainView.class)
@PageTitle("Pide tu préstamo con facilidad")
public class PrestamosView  extends VerticalLayout {

    CuentaService cuentaService;
    PrestamoService prestamoService;
    CategoriaService categoriaService;
    private MovimientoService movimientoService;
    TarjetaService tarjetaService;
    Prestamo prestamo;
    List<Cuenta> cuentas;
    Cuenta cuenta;
    Long numerocuentaCobro;
    Long numercuentaIngreso;
    Long cuentaIdCobro;
    Long cuentaIdIngreso;
    private  final String PRESTAMO = "Prestamo";
    private  final String  CONCEPTO = "Prestamo personal";

    private FormLayout formLayout;
    private ComboBox<Integer> duracion;
    ComboBox<String> periodoCbx;
    NumberField cantidadField;
    ComboBox<Cuenta> cuentaIngreso;
    ComboBox<Cuenta> cuentaCobro;
    private static final String PROP_ERROR = "error";
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Binder<Prestamo> prestamoBinder = new Binder<>(Prestamo.class);



    public PrestamosView(CuentaService cuentaService,PrestamoService prestamoService,MovimientoService movimientoService,CategoriaService categoriaService,TarjetaService tarjetaService) {
        this.setSizeFull();
        this.setPadding(true);

        this.cuentaService = cuentaService;
        this.prestamoService = prestamoService;
        this.movimientoService = movimientoService;
        this.categoriaService = categoriaService;
        this.tarjetaService =  tarjetaService;

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
            } else {

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
        cantidadField.setWidth("40%");
        cantidadField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        cantidadField.setSuffixComponent(new Icon(VaadinIcon.EURO));
        prestamoBinder.forField(cantidadField)
                .asRequired("Obligatorio")
                .bind("cantidad");
        cantidadField.addValueChangeListener(e -> {

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
            cuentaIdIngreso = event.getValue().getId();
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

            }
            numerocuentaCobro = event.getValue().getNumerocuenta();
            cuentaIdCobro = event.getValue().getId();
        });
        //trabajo con los datos recogidos
        Button preview = new Button("Pide tu préstamo", clickEvent -> {
            // define form dialog
            CuotaSimulPreview simulPrestaView = new CuotaSimulPreview(numerocuentaCobro,numercuentaIngreso,duracion.getValue(),periodoCbx.getValue(),cantidadField.getValue(),this.prestamoService);
            simulPrestaView.setWidth("700px");
            simulPrestaView.setCloseOnEsc(true);
            simulPrestaView.setCloseOnOutsideClick(false);
            // open form dialog view
            // define form dialog view callback
            String periodo = simulPrestaView.periodo;
            Integer tiempo = simulPrestaView.tiempo;
            simulPrestaView.addOpenedChangeListener(event -> {
                if(!event.isOpened()) {
                    if (simulPrestaView.getDialogResult() == CuotaSimulPreview.DIALOG_RESULT.SAVE)
                        try {
                            //crear movimiento ingreso
                            Movimiento movimientoi = new Movimiento();
                            Double cantidad = cantidadField.getValue();
                            Categoria categoria = categoriaService.findById(5l);
                            Tarjeta tarjeta = tarjetaService.findById(4441L);
                            crearMovimiento(cantidad, cuentaIdIngreso,movimientoi,tarjeta,categoria);
                            AsyncPush asyncPush = new AsyncPush(tiempo,cuentaIdCobro,cuentaIdIngreso, simulPrestaView.mes, cuentaService,movimientoService, tarjetaService, categoriaService);

                            Notification.show("Préstamo solicitado con éxito", 5000, Notification.Position.MIDDLE);


                        } catch (Exception ex) {
                            logger.error(ex.getMessage());

                            Notification.show("Error interno, no se ha podido formalizar el préstamo",5000,Notification.Position.MIDDLE);

                        }
                }
            });

            // open form dialog view
            simulPrestaView.open();
        });

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

    private void crearMovimiento(Double cantidad, Long cuentaIdIngreso, Movimiento movimientoi, Tarjeta tarjeta, Categoria categoria) {
        movimientoi.setCuenta(cuentaService.findById(cuentaIdIngreso));
        movimientoi.setImporte(cantidad);
        movimientoi.setConcepto(CONCEPTO);
        movimientoi.setFecha(LocalDateTime.now());
        movimientoi.setFechaValor(LocalDate.now());
        movimientoi.setDescripcion(PRESTAMO);
        movimientoi.setTarjeta(tarjeta);
        movimientoi.setCategoria(categoria);
        movimientoi.setCuenta(cuentaService.findById(cuentaIdIngreso));
        movimientoService.createMovimiento(movimientoi);

    }

    public void setError(boolean error) {
        if (error) {
            setEnabled(true);
        }
        getElement().setProperty(PROP_ERROR, error);
    }



}
