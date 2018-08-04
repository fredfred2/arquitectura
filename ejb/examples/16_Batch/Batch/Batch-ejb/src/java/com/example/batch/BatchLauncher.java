package com.example.batch;

import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;

@WebService(serviceName = "BatchLauncher")
@Stateless()
public class BatchLauncher {

    @WebMethod(operationName = "runChunks")
    public String runChunks() {
        try {
            JobOperator jo = BatchRuntime.getJobOperator();
            long jobId = jo.start("chunk", new Properties());
            return "Launched job: " + jobId;
        } catch (JobStartException | JobSecurityException ex) {
            return "error: " + ex.getMessage();
        }
    }

    @WebMethod(operationName = "runBatchlet")
    public String runBatchlet() {
        try {
            JobOperator jo = BatchRuntime.getJobOperator();
            long jobId = jo.start("batchlet", new Properties());
            return "Launched job: " + jobId;
        } catch (JobStartException | JobSecurityException ex) {
            return "error: " + ex.getMessage();
        }
    }

    @WebMethod(operationName = "runMultiStep")
    public String runMultiStep() {
        try {
            JobOperator jo = BatchRuntime.getJobOperator();
            long jobId = jo.start("multiStep", new Properties());
            return "Launched job: " + jobId;
        } catch (JobStartException | JobSecurityException ex) {
            return "error: " + ex.getMessage();
        }
    }
}
