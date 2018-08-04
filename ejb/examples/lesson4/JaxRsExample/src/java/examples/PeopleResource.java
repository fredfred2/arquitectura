package examples;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("p")
@Produces({MediaType.APPLICATION_JSON})
public class PeopleResource {
       
    @GET
    @Path("person")
    public Person getPerson() {      
        Person p = new Person();
        p.firstName = "Jane";
        p.lastName = "Doe";
        return p;
    }
    
    @GET
    @Path("people")
    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();
        Person p = new Person();
        p.firstName = "Jane";
        p.lastName = "Doe";
        people.add(p);
        p = new Person();
        p.firstName = "John";
        p.lastName = "Doe";
        people.add(p);
        return people;
    }
    
    @GET
    @Path("people2")
    public Response getMessages() {
        List<Person> people = new ArrayList<>();
        Person p = new Person();
        p.firstName = "Jane";
        p.lastName = "Doe";
        people.add(p);
        p = new Person();
        p.firstName = "John";
        p.lastName = "Doe";
        people.add(p);
        GenericEntity<List<Person>> entity
                = new GenericEntity<List<Person>>(people) {};
        Response response = Response.ok(entity).build();
        return response;
    }
}
