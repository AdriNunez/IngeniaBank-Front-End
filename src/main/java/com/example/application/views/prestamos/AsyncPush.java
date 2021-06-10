package com.example.application.views.prestamos;

import com.example.application.backend.model.Categoria;
import com.example.application.backend.model.Cuenta;
import com.example.application.backend.model.Movimiento;
import com.example.application.backend.service.CategoriaService;
import com.example.application.backend.service.CuentaService;
import com.example.application.backend.service.MovimientoService;
import com.vaadin.flow.component.html.Div;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AsyncPush extends Div {
    private static final int WAITING_TIME = 5000;
    public static Long cuentaIdCobro;
    public Long cuentaIdIngreso;
    public static Double cantidadMes;
    public static CuentaService cuentaService;
    private static int durationAsync = 0;
    private static MovimientoService movimientoService;
    private static final String PRESTAMO = "Prestamo";
    private static final String  CONCEPTO = "Prestamo personal";
    private static final Long idCategoria = 5L;
    private static CategoriaService categoriaService;


    public AsyncPush(
                     Integer tiempo,
                     Long cuentaIdCobro,
                     Long cuentaIdIngreso,
                     Double mes,
                     CuentaService cuentaService,

                     MovimientoService movimientoService,
                     CategoriaService categoriaService) {
        //datos del prestamo
        AsyncPush.cuentaIdCobro = cuentaIdCobro;
        this.cuentaIdIngreso = cuentaIdIngreso;
        cantidadMes = mes;
        AsyncPush.cuentaService = cuentaService;
        AsyncPush.movimientoService = movimientoService;
        AsyncPush.categoriaService = categoriaService;
        durationAsync = tiempo;

        FeederThread thread = new FeederThread();
        thread.start();
    }

    private static class FeederThread  extends Thread{

        Logger logger = LoggerFactory.getLogger(this.getClass());
        private int count = 1;

        public FeederThread() {

            System.out.println("hola");
        }


        /**
         * It runs the main loop that holds the thread for A WAITING TIME between create
         * the call createtransaction() function
         * Counting each iteration , making the account of monthly quota to pay,
         * insert and updating in the database tables
         *
         */
        @Override
        public void run() {
            System.out.println("estoy en run run");
            System.out.println("count"+count);

            try {
                // Update the data for a while
                while (count <= durationAsync) {
                    // Sleep to emulate background work
                    sleep(WAITING_TIME);

                    createTransaction(count);
                    count++;


                }

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Create the Loan Transaction
         * Counting each iteration , making the account of monthly quota to pay,
         * insert and updating in the database tables
         *
         * @param count number of the transaction iteration  (int count)
         * @return Boolean if the operation makes succesfull
         */
        private Boolean createTransaction(int count ) throws IOException {

            System.out.println("entro aqui");
            Double cantidad;
            Movimiento movimiento = new Movimiento();
            //recuperar el tipo de categoria.
            //Categoria categoria = categoriaService.findById(idCategoria);

//            if(cuentaIdCobro!=null)
//                System.out.println("hola estoy aqui con los cobrrooo");
//                cuentaService.getSaldoTotalCuenta(cuentaIdCobro);
//                Double saldocobro = cuentaService.getSaldoTotalCuenta(cuentaIdCobro);
//            System.out.println(saldocobro);

             //cuentaService.getSaldoTotalCuenta(cuentaIdCobro);

            String message = "Cobro Cuota numero " + count + "/" + durationAsync;
            logger.info(message);

            try {

                System.out.println("aqui voy con el id de la cuenta a realizar el getSadlo");
                if(cuentaIdCobro!=null)
                    System.out.println("hola estoy aqui con los cobrrooo");
                cuentaService.getSaldoTotalCuenta(cuentaIdCobro);
                Double saldocobro = cuentaService.getSaldoTotalCuenta(cuentaIdCobro) - cantidadMes;
                System.out.println("++++++"+saldocobro);
                Cuenta cuenta = cuentaService.findById(cuentaIdCobro);
                System.out.println(cuenta);
              //  cuenta.setImporteactual(saldocobro);
                System.out.println(cantidadMes);
//
//
//                movimiento.setCuenta(cuentaService.findById(cuentaIdCobro));
//                if(cuentaIdCobro!=null){
//                    System.out.println("hola estoy aqui con los cobrrooo");
//                    cantidad =( -1 * cantidadMes);
//                }else{
//                    cantidad = cantidadMes;
//                }
//
//
//
//                movimiento.setImporte(cantidad);
//                movimiento.setConcepto(CONCEPTO);
//                movimiento.setFecha(LocalDateTime.now());
//                movimiento.setFechaValor(LocalDate.now());
//                movimiento.setDescripcion(PRESTAMO);
//
//                movimiento.setCategoria(categoria);
//                movimiento.setCuenta(cuentaService.findById(cuentaIdCobro));
//                movimientoService.createMovimiento(movimiento);
//


            } catch (Exception e) {
                logger.error(e.getMessage());
                System.out.println("estoy aqui en la excepcion=====");
               // movimiento.setId(-500L);
                return false;
            }
            return true;
        }
    }
}
