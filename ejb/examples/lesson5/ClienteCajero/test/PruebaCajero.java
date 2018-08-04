
import com.certificatic.OperacionesCajeroWS;
import com.certificatic.OperacionesCajeroWSService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredfred2
 */
public class PruebaCajero {
    public static void main(String[] args) {
        OperacionesCajeroWSService s=new OperacionesCajeroWSService();
        OperacionesCajeroWS port = s.getOperacionesCajeroWSPort();
        double respuesta = port.depositaEfectivo("A1344", 2000);
        System.out.println("PruebaCajero.depositaTest():="+respuesta);
    }
}
