package com.certificatic;

import java.util.Random;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(portName = "MyPort", serviceName = "SuperService")
public class OperacionesCajeroWS {

    @WebMethod(operationName = "retirameEfectivo")
    @WebResult(name = "saldoResultante")
    public double retiraEfectivo(@WebParam(name = "cuenta") String cuenta, @WebParam(name = "monto") double monto) {
        return new Random().nextDouble();
    }

    @WebMethod(operationName = "depositameEfectivo")
    @WebResult(name = "saldoResultante")
    public double depositaEfectivo(@WebParam(name = "cuenta") String cuenta, @WebParam(name = "monto") double monto) {
        return new Random().nextDouble();
    }
}
