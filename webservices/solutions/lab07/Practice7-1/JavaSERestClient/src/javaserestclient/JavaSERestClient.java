package javaserestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import wlsmon.ArrayType;
import wlsmon.DataType;
import wlsmon.ObjectType;
import wlsmon.PropertyType;
import wlsmon.ValueType;

public class JavaSERestClient {

    public static void main(String[] args) {
        try {
            // http://docs.oracle.com/javase/7/docs/technotes/guides/net/http-auth.html
			Authenticator authinstance = new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("weblogic", "weblogic1".toCharArray());
				}
			};
			Authenticator.setDefault(authinstance);


			URL url = new URL("http://localhost:7001/management/tenant-monitoring/applications/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Accept", "application/xml");

			if (connection.getResponseCode() >= 400) {
				InputStream rIn = connection.getErrorStream();
				BufferedReader buff = new BufferedReader(new InputStreamReader(rIn));
				String inputLine;
				while ((inputLine = buff.readLine()) != null) {
					System.out.println(inputLine);
				}
			} else {
				InputStream rIn = connection.getInputStream();
				BufferedReader buff = new BufferedReader(new InputStreamReader(rIn));
				StringBuilder sb = new StringBuilder();
				String inputLine;
				while ((inputLine = buff.readLine()) != null) {
					sb.append(inputLine).append("\n");
				}

				JAXBContext jc = JAXBContext.newInstance("wlsmon");
				Unmarshaller u = jc.createUnmarshaller();

				StringReader reader = new StringReader(sb.toString());
				JAXBElement<DataType> jbDataType = (JAXBElement<DataType>) u.unmarshal(reader);
				DataType data = jbDataType.getValue();
				printObjectType(data.getObject(), 0);
			}

		} catch (IOException | JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public static void printObjectType(ObjectType obj, int indentLevel) {
		List<PropertyType> properties = obj.getProperty();
		if (properties != null) {
			String indentStr = "";
			for (int i = 0; i < indentLevel; i++) {
				indentStr += "    ";
			}
			for(PropertyType pType : properties) {
				System.out.print(indentStr + pType.getName());
				if(pType.getValue() != null) {
					ValueType valType = pType.getValue().getValue();
					System.out.println("=" + valType.getValue());
				} else {
					System.out.println("");
				}
				ArrayType arrType = pType.getArray();
				if(arrType != null) {
					List<ObjectType> objList = arrType.getObject();
					for(ObjectType obj2 : objList) {
						printObjectType(obj2, indentLevel + 1);
					}
				}
				ObjectType objType = pType.getObject();
				if(objType != null) {
					printObjectType(objType, indentLevel + 1);
				}
			}
			System.out.println("");
		}
	}
}