/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummy;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBException;

/**
 *
 * @author admin
 */
@Provider
public class RummyExceptionMapper
        implements ExceptionMapper<JAXBException> {

    @Override
    public Response toResponse(JAXBException e) {
        Response.ResponseBuilder rb = 
                Response.status(
                        Response.Status.INTERNAL_SERVER_ERROR);
        rb.type(MediaType.TEXT_PLAIN_TYPE);
        if(e.getCause()!=null){
            rb.entity(e.getCause().getMessage());
        }else if(e.getMessage()!=null){
            rb.entity(e.getMessage());
        }else{
            rb.entity("Problema de JAXB");
        }
        return rb.build();
    }

}
