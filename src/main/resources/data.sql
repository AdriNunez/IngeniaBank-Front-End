INSERT INTO categoria (id, nombre) VALUES (4, 'NOMINAS');


insert into cuenta (id,numerocuenta,codpais,digitocontrol,entidad,oficina,tipocuenta,estado, fechaactual, fechaapertura,fechacontable, saldo_actual,saldo_inicial)
values (1,12345678,11,01,1234,03,"Cuenta Seguro Plus",0,NOW(),'2018-01-01',NOW(),1000,2000);

insert into cuenta (id,numerocuenta,codpais,digitocontrol,entidad,oficina,tipocuenta,estado, fechaactual, fechaapertura,fechacontable, saldo_actual,saldo_inicial)
values (2,12345679,11,01,1234,03,"Cuenta Seguro Plus",0,NOW(),'2018-01-01',NOW(),1000,2000);

insert into tarjeta(id,numero_tarjeta,ccv, estado_tarjeta,fecha_expedicion, fecha_expiracion, limite, tipo,cuenta_id)
values (444441,444441,124,0,'2021-05-12 13:47:39.000000','2023-05-12 13:47:39.000000',500,'debito',1);

insert into tarjeta(id,numero_tarjeta,ccv, estado_tarjeta,fecha_expedicion, fecha_expiracion, limite, tipo,cuenta_id)
values (333331,333331,124,0,'2021-05-12 13:47:39.000000','2023-05-12 13:47:39.000000',500,'debito',2);

insert into tarjeta(id,numero_tarjeta,ccv, estado_tarjeta,fecha_expedicion, fecha_expiracion, limite, tipo,cuenta_id)
values (222221,222221,124,0,'2021-05-12 13:47:39.000000','2023-05-12 13:47:39.000000',500,'debito',1);

--insert into tarjeta(id,numero_tarjeta,ccv, estado_tarjeta,fecha_expedicion, fecha_expiracion, limite, tipo,cuenta_id)
--values (111111,111111,124,0,'2021-05-12 13:47:39.000000','2023-05-12 13:47:39.000000',500,'debito',1);

--insert into tarjeta(id,numero_tarjeta,ccv, estado_tarjeta,fecha_expedicion, fecha_expiracion, limite, tipo,cuenta_id)
--values (5,000001,124,0,'2021-05-12 13:47:39.000000','2023-05-12 13:47:39.000000',500,'debito',2);

insert into movimiento(id,concepto,descripcion,fecha,fecha_valor,importe,id_categoria,id_cuenta,id_tarjeta)
values (1,'Cobro nómina','cobro nómina','2020-05-12','2020-05-12',1000,4,1,222221);

insert into movimiento(id,concepto,descripcion,fecha,fecha_valor,importe,id_categoria,id_cuenta,id_tarjeta)
values (2,'Pago escuela','Pago escuela','2021-01-12','2021-01-12',-2000,4,1,222221);

insert into movimiento(id,concepto,descripcion,fecha,fecha_valor,importe,id_categoria,id_cuenta,id_tarjeta)
values (3,'Pago restaurante italiano','Pago en restaurante italiano','2021-02-12','2021-02-12',-25,4,1,222221);

insert into movimiento(id,concepto,descripcion,fecha,fecha_valor,importe,id_categoria,id_cuenta,id_tarjeta)
values (4,'Pago restaurante','Pago en restaurante','2021-03-12','2021-03-12',-30,4,1,222221);

insert into movimiento(id,concepto,descripcion,fecha,fecha_valor,importe,id_categoria,id_cuenta,id_tarjeta)
values (5,'Cobro paga extra','Paga extra','2021-04-12','2021-04-12',2000,4,2,333331);

insert into movimiento(id,concepto,descripcion,fecha,fecha_valor,importe,id_categoria,id_cuenta,id_tarjeta)
values (6,'Pago Netflix','Pago Netflix','2021-05-12','2021-05-12',-12,4,2,333331);

insert into movimiento(id,concepto,descripcion,fecha,fecha_valor,importe,id_categoria,id_cuenta,id_tarjeta)
values (7,'Cobro transferencia','cobro transferencia','2021-06-12','2021-06-12',1000,4,2,333331);

insert into movimiento(id,concepto,descripcion,fecha,fecha_valor,importe,id_categoria,id_cuenta,id_tarjeta)
values (8,'Pago restaurante','pago restaurante','2021-07-12','2021-07-12',2000,4,1,222221);

insert into movimiento(id,concepto,descripcion,fecha,fecha_valor,importe,id_categoria,id_cuenta,id_tarjeta)
values (9,'Retirada de efectivo','retirada de efectivo','2021-08-12','2021-08-12',3000,4,1,222221);











