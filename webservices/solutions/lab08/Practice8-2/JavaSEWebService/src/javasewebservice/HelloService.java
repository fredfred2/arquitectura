package javasewebservice;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class HelloService {

    public String getMessage(
            @WebParam(name = "name") String name) {
        return name + " rocks!";
    }
}