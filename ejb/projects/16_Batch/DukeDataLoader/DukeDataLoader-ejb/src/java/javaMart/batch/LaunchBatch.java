package javaMart.batch;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;

@WebService(serviceName = "LaunchBatch")
@Stateless()
public class LaunchBatch {

    @WebMethod(operationName = "run")
    public String runBatch() {
        return "";
    }
}
