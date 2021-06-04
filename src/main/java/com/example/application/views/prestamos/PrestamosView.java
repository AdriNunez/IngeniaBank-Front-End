package com.example.application.views.prestamos;

import com.example.application.backend.model.Prestamo;
import com.example.application.backend.service.PrestamoService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.math.BigInteger;

@Route(value = "Prestamos", layout = MainView.class)
@PageTitle("Pide tu préstamo con facilidad")
public class PrestamosView  extends HorizontalLayout {
    private  PrestamoService prestamoService;
    private Prestamo prestamo;
    // Create a container for such beans

    public String periodo;
    public Integer tiempo;
    public BigInteger cantidadc;


    public PrestamosView(PrestamoService prestamoService) {
        this.setSizeFull();
        this.setPadding(true);

        // add two rows
        add(createPreview());
    }
//
//        this.prestamoService = prestamoService;
//        HorizontalLayout horizontalLayout = new HorizontalLayout();
//        FormLayout columnLayout = new FormLayout();
//// Setting the desired responsive steps for the columns in the layout
//        columnLayout.setResponsiveSteps(
//                new FormLayout.ResponsiveStep("25em", 1),
//                new FormLayout.ResponsiveStep("32em", 2),
//                new FormLayout.ResponsiveStep("40em", 3));
//
//        //calcular.
//        BigDecimalField bigDecimalField = new BigDecimalField("Total cost");
//        bigDecimalField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
//        bigDecimalField.setPrefixComponent(new Icon(VaadinIcon.EURO));
//
//        Paragraph tax = new Paragraph();
//
//        bigDecimalField.addValueChangeListener(e -> {
//            BigDecimal taxValue;
//            if (e.getValue() == null) {
//                Notification.show("Obligatorio valor");
//            } else {
//                cantidadc = e.getValue().toBigInteger();
//                System.out.println("kkkkk"+ cantidadc);
//            }
//
//        });
//
//
//        System.out.println("++++1"+ cantidadc);
//        add(bigDecimalField);
//
////        NumberField cantidad = new NumberField();
////        cantidad.getValue();
////        Double cantidadc = cantidad.getValue();
////        System.out.println("cantidadc"+cantidadc);
////        cantidad.setPlaceholder("First Name");
//        //duracion
//        VerticalLayout tiempoLayout = new VerticalLayout();
//        //
//        //
//        ComboBox<Integer> duracion = new ComboBox<>();
//        duracion.setLabel("Duracion");
//        duracion.setItems(6,1,2);
//        Div value = new Div();
//        value.setText("Select a value");
//
//        duracion.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<Integer>, Integer>>() {
//            @Override
//            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<Integer>, Integer> event) {
//                System.out.println("holla");
//                Notification.show(String.valueOf(event.getValue()));
//                tiempo = event.getValue();
//            }
//        });
////        duracion.addValueChangeListener(event -> {
////            if (event.getValue() == null) {
////                value.setText("No option selected");
////            } else {
////                value.setText("Selected: " + event.getValue());
////                tiempo = event.getValue();
////
////            }
////        });
//
//        duracion.setPlaceholder("tiempo");
//
//        ComboBox<String> periodoCbx = new ComboBox<>();
//        periodoCbx.setLabel("periodo");
//        periodoCbx.setItems("M","A");
//        Div vauluep = new Div();
//        value.setText("Select a value");
//        periodoCbx.addValueChangeListener(event -> {
//            if (event.getValue() == null) {
//                vauluep.setText("No option selected");
//            } else {
//                vauluep.setText("Selected: " + event.getValue());
//                 periodo = event.getValue();
//
//            }
//        });
//        duracion.setPlaceholder("periodo");
//        //recuperar para calcular
//
//        tiempoLayout.add(duracion,value,periodoCbx,vauluep);
//        //
//
//        columnLayout.add(bigDecimalField, tiempoLayout,bigDecimalField, tax);
//        // You can set the desired column span for the components individually.
//        add(columnLayout);
//        //para test
//        System.out.println("que llevo"+cantidadc+ value.getText()+tiempo+periodo);
//        add(simulPreview(cantidadc,tiempo,periodo));
//
//        System.out.println("*****"+periodo);
//        System.out.println("++++"+ cantidadc);
//        System.out.println("*****tiempo"+tiempo);
//
//    }

    private Component createPreview() {
        HorizontalLayout row = new HorizontalLayout();

        ComboBox<Integer> duracion = new ComboBox<>();
        duracion.setLabel("Duracion");
        duracion.setItems(6,1,2);
        Div value = new Div();
        value.setText("Select a value");

        duracion.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<Integer>, Integer>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<Integer>, Integer> event) {
                System.out.println("holla");
                Notification.show(String.valueOf(event.getValue()));
                tiempo = event.getValue();
            }
        });


        //period
        ComboBox<String> periodoCbx = new ComboBox<>();
        periodoCbx.setLabel("periodo");
        periodoCbx.setItems("M","A");
        Div vauluep = new Div();
        value.setText("Select a value");
        periodoCbx.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                vauluep.setText("No option selected");
            } else {
                vauluep.setText("Selected: " + event.getValue());
                periodo = event.getValue();

            }
        });
        duracion.setPlaceholder("periodo");



        Button stringButton = new Button("preview",clickEvent -> {
            System.out.println("recojo los datos"+duracion.getValue()+ periodoCbx.getValue());
            // define form dialog
            CuotaSimulPreview cuotaSimulPreview = new CuotaSimulPreview(duracion.getValue(),periodoCbx.getValue());
            cuotaSimulPreview.setWidth("700px");
            cuotaSimulPreview.setCloseOnEsc(true);
            cuotaSimulPreview.setCloseOnOutsideClick(true);
            // bind form dialog with product entity


            // define form dialog view callback

            // open form dialog view
            cuotaSimulPreview.open();
        });



    
    ;

//        stringButton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
//            @Override
//            public void onComponentEvent(ClickEvent<Button> event) {
//                Notification.show(String.valueOf(duracion.getValue()), 2000, Notification.Position.TOP_END);
//                Notification.show(periodoCbx.getValue(), 2000, Notification.Position.TOP_END);
//            }
//        });
        row.add(duracion,periodoCbx,stringButton);

        return row;
    }


    private Button simulPreview(BigInteger cantidadc, Integer t, String p) {

        Button preview = new Button("preview",clickEvent -> {
            // define form dialog
            CuotaSimulPreview cuotaSimulPreview = new CuotaSimulPreview(t,p);
            cuotaSimulPreview.setWidth("700px");
            cuotaSimulPreview.setCloseOnEsc(true);
            cuotaSimulPreview.setCloseOnOutsideClick(false);
            // bind form dialog with product entity


            // define form dialog view callback

            // open form dialog view
            cuotaSimulPreview.open();
        });


        return preview;
    }

}
