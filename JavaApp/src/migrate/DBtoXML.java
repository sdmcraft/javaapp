package migrate;

import java.io.File;
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

	static Connection con;

	public static void service(String connectionUrl, String resultFile, int account_id) throws Exception {
		try {
			init(connectionUrl);
			Element accElement = new Element("account");
			accElement.setAttribute(new Attribute("account_id",Integer.toString(account_id)));
			Document doc = new Document(accElement);
			for (String table : QueryList.tableList) {
				String query = QueryList.queryMap.get(table);
				accElement.addContent(executeQuery(table, query, account_id));
			}
			destroy();
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			File result = new File(resultFile);
			result.createNewFile();
			FileOutputStream fout = new FileOutputStream(result);
			outputter.output(doc, fout);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	private static void init(String connectionUrl) throws Exception {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	private static void destroy() throws Exception {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private static Element executeQuery(String table, String query, int account_id) throws Exception {
		Element tableElement = new Element("table");
		tableElement.setAttribute(new Attribute("name", table));
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, account_id);
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
			throw e;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
		}
		return tableElement;
	}

}
