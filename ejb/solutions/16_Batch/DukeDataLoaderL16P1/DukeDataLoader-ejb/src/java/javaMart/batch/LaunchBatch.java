package javaMart.batch;

import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "LaunchBatch")
@Stateless()
public class LaunchBatch {

    @WebMethod(operationName = "run")
public String runBatch() {
    try {
        JobOperator jo = BatchRuntime.getJobOperator();
        long jobId = jo.start("loadSalesJob", new Properties());
        return "Launched job: " + jobId;
    } catch (JobStartException | JobSecurityException ex) {
        return "error: " + ex.getMessage();
    }
}
}
