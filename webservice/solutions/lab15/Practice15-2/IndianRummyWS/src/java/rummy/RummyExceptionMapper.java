package rummy;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBException;

@Provider
public class RummyExceptionMapper implements ExceptionMapper<JAXBException> {

@Override
public Response toResponse(JAXBException exception) {
    ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
    rb.type(MediaType.TEXT_PLAIN_TYPE);
    if(exception.getCause() != null) {
        rb.entity(exception.getCause().getMessage());
    } else if (exception.getMessage() != null) {
        rb.entity(exception.getMessage());
    } else {
        rb.entity("JAXB Problem, if this continues contact support");
    }
    return rb.build();
}
}