package com.example.timer;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import javax.inject.Inject;

@WebService(serviceName = "TimerWebService")
@Stateless()
public class TimerWebService {

    @Inject
    private ProgramaticTimer programaticTimer;
    @Inject
    private AutoTimer autoTimer;

    @WebMethod(operationName = "checkProgramatic")
    public String checkProgramatic() {
        return programaticTimer.getValue();
    }

    @WebMethod(operationName = "checkAutomatic")
    public String checkAutomatic() {
        return autoTimer.getValue();
    }
}
