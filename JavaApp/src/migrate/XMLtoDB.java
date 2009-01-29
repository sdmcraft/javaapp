package migrate;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class XMLtoDB {

	final static String connectionUrl = "jdbc:sqlserver://satyam-lt:1433;databaseName=breeze750;user=sa;password=breeze";
	static Connection con;

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

	public static void main(String[] args) {
		PrintWriter out = null;
		try {
			//init();
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new FileInputStream("result.xml"));
			Element accElement = doc.getRootElement();
			File script = new File("script.sql");
			script.createNewFile();
			out = new PrintWriter(script);
			List<Element> tableList = accElement.getChildren();
			for (Element tableElement : tableList) {
				String table = tableElement.getAttributeValue("name");
				List<Element> rowList = tableElement.getChildren();
				for (Element rowElement : rowList) {
					String[] columnNames = new String[100];
					String[] columnTypes = new String[100];
					String[] columnValues = new String[100];
					List<Element> dataList = rowElement.getChildren();
					int count = 0;
					for (Element dataElement : dataList) {
						String columnValue = dataElement.getAttributeValue("value");
						if ("".equals(columnValue))
							continue;
						else
							columnValues[count] = dataElement.getAttributeValue("value");
						columnNames[count] = dataElement
								.getAttributeValue("name");
						columnTypes[count] = dataElement
								.getAttributeValue("type");
						
						count++;
					}
					String stmt = constructInsert(columnNames, columnTypes,
							columnValues, table, count);
					out.println(stmt);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			out.flush();
			out.close();
			//destroy();
		}
	}

	private static String constructInsert(String[] columnNames,
			String[] columnTypes, String[] columnValues, String table, int count) {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO " + table + "(");
		for (int i = 0; i < count; i++) {
			query.append(columnNames[i] + ",");
		}
		query.deleteCharAt(query.lastIndexOf(","));
		query.append(") VALUES (");
		for (int i = 0; i < count; i++) {
			if ("int".equals(columnTypes[i]))
				query.append(columnValues[i] + ",");
			else
				query.append("'" + columnValues[i] + "',");
		}
		query.deleteCharAt(query.lastIndexOf(","));
		query.append(");");

		return query.toString();
	}

}
