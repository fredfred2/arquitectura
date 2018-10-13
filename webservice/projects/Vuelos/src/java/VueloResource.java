
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
@Path("vuelo")
public class VueloResource {

    @GET
    public List<Vuelo> getVuelos(@QueryParam("pagina") int pagina, 
            @QueryParam("resultados") int resultados) {
        return null;
    }
    
    @PUT
    @Path("/{idVuelo}")
    public Vuelo checkin(@PathParam("idVuelo")int idVuelo){
        //get vuelo
        //haz checkin
        //regresa vuelo modificado
    }
}
