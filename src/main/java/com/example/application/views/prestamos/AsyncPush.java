package com.example.application.views.prestamos;

import com.example.application.backend.model.Categoria;
import com.example.application.backend.model.Movimiento;
import com.example.application.backend.model.Tarjeta;
import com.example.application.backend.service.CategoriaService;
import com.example.application.backend.service.CuentaService;
import com.example.application.backend.service.MovimientoService;
import com.example.application.backend.service.TarjetaService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Push
@Route("asyncpush")
public class AsyncPush extends Div {
    private static final int WAITING_TIME = 5000;
    public static Long cuentaIdCobro;
    public static Long cuentaIdIngreso;
    public  static Long cuenta;
    public static Double cantidadMes;
    public static CuentaService cuentaService;
    private static int durationAsync = 0;
    private static MovimientoService movimientoService;
    private static final String PRESTAMO = "Prestamo";
    private static final String  CONCEPTO = "Prestamo personal";
    private static final Long idCategoria = 5L;
    private static CategoriaService categoriaService;
    private static  TarjetaService tarjetaService;

    private FeederThread thread;


    public AsyncPush(Integer tiempo,
                     Long cuentaIdCobro,
                     Long cuentaIdIngreso,
                     Double mes,
                     CuentaService cuentaService,
                     MovimientoService movimientoService,
                     TarjetaService tarjetaService,
                     CategoriaService categoriaService){
        //datos del prestamo
        this.cuentaIdCobro = cuentaIdCobro;
        this.cuentaIdIngreso = cuentaIdIngreso;

        cantidadMes = mes;
        this.cuentaService = cuentaService;
        AsyncPush.movimientoService = movimientoService;
        this.categoriaService = categoriaService;
        AsyncPush.tarjetaService = tarjetaService;
        durationAsync = tiempo;
        FeederThread thread = new FeederThread();
        thread.start();

    }
    private static class FeederThread  extends Thread{

        Logger logger = LoggerFactory.getLogger(this.getClass());
        private int count = 1;


        public FeederThread() {}



        /**
         * It runs the main loop that holds the thread for A WAITING TIME between create
         * the call createtransaction() function
         * Counting each iteration , making the account of monthly quota to pay,
         * insert and updating in the database tables
         *
         */
        @Override
        public void run() {

            try {
                // Update the data for a while
                while (count <= durationAsync) {
                    // Sleep to emulate background work
                    Thread.sleep(WAITING_TIME);

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
            Double cantidad = null;

            Movimiento movimiento = new Movimiento();
            //recuperar el tipo de categoria.
            Categoria categoria = categoriaService.findById(idCategoria);
            Tarjeta tarjeta = tarjetaService.findById(4441L);

            String message = "Cobro Cuota numero " + count + "/" + durationAsync;
            logger.info(message);

            try {

                //crear movimiento de cobro;
                if(cuentaIdCobro!=null) {
                    Movimiento movimientoc = new Movimiento();
                    cantidad = (-1 * cantidadMes);
                    cuenta = cuentaIdCobro;
                    crearMovimiento(cantidad, cuenta,movimientoc,tarjeta,categoria);

                }
                if(cuentaIdIngreso!=null) {
                    Movimiento movimientoi = new Movimiento();
                    cantidad = cantidadMes;
                    cuenta = cuentaIdIngreso;
                    crearMovimiento(cantidad, cuenta,movimientoi,tarjeta,categoria);

                }




            } catch (Exception e) {
                logger.error(e.getMessage());

               // movimiento.setId(-500L);
                return false;
            }
            return true;
        }

        private void crearMovimiento(Double cantidad, Long cuenta, Movimiento movimiento, Tarjeta tarjeta, Categoria categoria) {

            movimiento.setCuenta(cuentaService.findById(cuenta));
            movimiento.setImporte(cantidad);
            movimiento.setConcepto(CONCEPTO);
            movimiento.setFecha(LocalDateTime.now());
            movimiento.setFechaValor(LocalDate.now());
            movimiento.setDescripcion(PRESTAMO);
            movimiento.setTarjeta(tarjeta);
            movimiento.setCategoria(categoria);
            movimiento.setCuenta(cuentaService.findById(cuenta));
            movimientoService.createMovimiento(movimiento);
        }
    }
}
