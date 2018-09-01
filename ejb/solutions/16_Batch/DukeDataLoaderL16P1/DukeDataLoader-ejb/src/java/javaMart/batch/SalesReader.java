/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaMart.batch;
import java.io.InputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author oracle
 * 
 * 
 */
@Named
public class SalesReader extends AbstractItemReader  {
    private JsonParser parser;
    


@Override
public void open(Serializable checkpoint) throws Exception {
Client client = ClientBuilder.newClient();
Response response = client.target("http://localhost:7005/BatchServices/rest/sales")
    .request(MediaType.APPLICATION_JSON)
    .async()
    .get()
    .get(2, TimeUnit.SECONDS);

parser = Json.createParser(response.readEntity(InputStream.class));
parser.next();
}



 @Override
    public Object readItem() throws Exception {
        JsonParser.Event event = parser.next();
        if (event == JsonParser.Event.START_OBJECT) {
            return JsonUtilities.readObject(parser);
        }
        return null;
    }




@Override
public void close() throws Exception {
  parser.close();
}
}

