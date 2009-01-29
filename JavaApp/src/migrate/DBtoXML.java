package migrate;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class DBtoXML {

	final static String connectionUrl = "jdbc:sqlserver://satyam-xp:1433;databaseName=breeze750;user=sa;password=breeze";
	final static int ACCOUNT_ID = 16401;
	static Connection con;

	public static void main(String[] args) {
		try {
			init();
			Element accElement = new Element("account");
			accElement.setAttribute(new Attribute("account_id",Integer.toString(ACCOUNT_ID)));
			Document doc = new Document(accElement);
			for (String table : QueryList.tableList) {
				String query = QueryList.queryMap.get(table);
				accElement.addContent(executeQuery(table, query));
			}
			destroy();
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(doc, new FileOutputStream("result.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void init() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void destroy() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Element executeQuery(String table, String query) {
		Element tableElement = new Element("table");
		tableElement.setAttribute(new Attribute("name", table));
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, ACCOUNT_ID);
			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Element rowElement = new Element("row");
				for (int i = 1; i <= columnCount; i++) {
					Element dataElement = new Element("data");
					dataElement.setAttribute(new Attribute("name", metaData
							.getColumnLabel(i)));
					dataElement.setAttribute(new Attribute("type", metaData
							.getColumnTypeName(i)));
					String value = (rs.getString(i) != null) ? rs.getString(i)
							: "";
					dataElement.setAttribute(new Attribute("value", value));
					rowElement.addContent(dataElement);
				}
				tableElement.addContent(rowElement);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return tableElement;
	}

}
