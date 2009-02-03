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
	
	static Connection con;

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

	public static void service(String connectionUrl,String targetXML, String sqlScript) throws Exception {
		PrintWriter out = null;
		try {
			//init(connectionUrl);
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new FileInputStream(targetXML));
			Element accElement = doc.getRootElement();
			File script = new File(sqlScript);
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
			throw ex;
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
			columnValues[i] = columnValues[i].replace("'", "''");
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
