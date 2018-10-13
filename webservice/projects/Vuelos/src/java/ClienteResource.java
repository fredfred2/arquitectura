
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
@Path("clientes")
public class ClienteResource {
    @PUT
    @Path("/{id}")
    public Cliente acumulaMillas(@PathParam("id")int id, Cliente cliente){
        //acumulacion
    }
}
