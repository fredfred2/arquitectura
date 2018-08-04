package javaMart.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map.Entry;
import java.util.Properties;

public class Debugger {

  public static void debugConnection(Connection c) throws Exception {
    StringBuilder s = new StringBuilder();
    log(s, "<<Debugger>>");
    try (Statement st = c.createStatement();) {
      log(s, "SCHEMA: " + c.getSchema());
      log(s, "CATALOG: " + c.getCatalog());
      log(s, "PROPERTIES: ");
      Properties clientInfo = c.getClientInfo();
      for (Entry e : clientInfo.entrySet()) {
        log(s, e.getKey() + ":" + e.getValue());
      }
      try (ResultSet rs = st.executeQuery(
              "select s.schemaname || '.' || t.tablename "
              + "from sys.systables t, sys.sysschemas s  "
              + "where t.schemaid = s.schemaid and t.tabletype = 'T' "
              + "order by s.schemaname, t.tablename")) {
        log(s, "TABLES: ");
        while (rs.next()) {
          log(s, rs.getString(1));
        }
      }
    }
    log(s, "<<END>>");
  }

  public static Connection openConnection() throws SQLException {
    Properties connectionProps = new Properties();
    connectionProps.put("user", "oracle");
    connectionProps.put("password", "oracle");
    return DriverManager.getConnection("jdbc:derby://localhost:1527/JavaMartDB", connectionProps);
  }

  private static void log(StringBuilder s, String debug) {
    System.out.println(debug);
    s.append(s);
    s.append('\n');
  }

}
