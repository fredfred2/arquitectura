package javaMart.batch;

import java.io.StringReader;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.END_ARRAY;
import static javax.json.stream.JsonParser.Event.END_OBJECT;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
import static javax.json.stream.JsonParser.Event.START_ARRAY;
import static javax.json.stream.JsonParser.Event.START_OBJECT;
import static javax.json.stream.JsonParser.Event.VALUE_NUMBER;
import static javax.json.stream.JsonParser.Event.VALUE_STRING;

public class JsonUtilities {

    public static JsonObject readObject(JsonParser parser) {
        StringWriter out = new StringWriter();
        try (JsonGenerator generator = Json.createGenerator(out)) {
            generator.writeStartObject();
            int objectCount = 1;
            String keyName = null;
            while (objectCount > 0) {
                switch (parser.next()) {
                    case START_ARRAY:
                        generator.writeStartArray(keyName);
                        break;
                    case END_OBJECT:
                        objectCount--;
                    case END_ARRAY:
                        generator.writeEnd();
                        break;
                    case START_OBJECT:
                        generator.writeStartObject();
                        objectCount++;
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_NUMBER:
                        generator.write(keyName, parser.getBigDecimal());
                        break;
                    case VALUE_STRING:
                        generator.write(keyName, parser.getString());
                        break;
                }
            }
        }
        return Json.createReader(new StringReader(out.toString())).readObject();
    }
}
