package migrate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

public class TransformXML {

	private static Document doc = null;
	private static Document idMapDoc = null;
	private static SAXBuilder builder = new SAXBuilder();
	private static Connection con;

	public static void service(String sourceXML, String idMapXML,
			String targetXML, String connectionUrl) throws Exception {
		init(sourceXML, idMapXML, connectionUrl);
		handleFields();
		removeIgnoredRows();
		buildIDMap(idMapXML);
		replaceIDs();
		writeXML(targetXML, doc);
		destroy();
	}

	private static void init(String sourceXML, String idMapXML,
			String connectionUrl) throws Exception {
		try {
			doc = builder.build(new FileInputStream(sourceXML));
			idMapDoc = builder.build(new FileInputStream(idMapXML));
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	private static void destroy() throws Exception {
		try {
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	private static void buildIDMap(String idMapXML) throws Exception {
		try {
			Element rootElement = idMapDoc.getRootElement();
			int idCount = 0;
			for (String idGroup : QueryList.idGroups) {
				List<Element> idList = XPath.newInstance(idGroup).selectNodes(
						doc.getRootElement());
				for (Element idElement : idList) {
					String id = idElement.getAttributeValue("value");
					if (XPath.newInstance("//id[@old=" + id + "]")
							.selectSingleNode(rootElement) == null) {
						Element idEntryElement = new Element("id");
						idEntryElement.setAttribute(new Attribute("old", id));
						rootElement.addContent(idEntryElement);
						idCount++;
					} else {
						System.out.println("WARNING--Attempt to remap " + id);
						continue;
					}
				}
			}
			long startId = blockNewIDs(idCount);
			if (startId == -1)
				throw new Exception("Error while blocking IDs");
			else {
				List<Element> workList = XPath.newInstance("//id").selectNodes(
						rootElement);
				for (Element idElement : workList) {
					if (idElement.getAttribute("new") != null)
						continue;
					else
						idElement.setAttribute(new Attribute("new", Long
								.toString(startId++)));
				}
			}
			writeXML(idMapXML, idMapDoc);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	private static long blockNewIDs(int blockSize) throws Exception {
		long startId = -1;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) "
					+ "FROM pps_enum_data_hosts " + "WHERE host_id=1000");
			ResultSet rs = stmt.executeQuery();
			long hostCount = 0;
			while (rs.next())
				hostCount = rs.getLong(1);
			if (hostCount == 0) {
				stmt = con
						.prepareStatement("INSERT INTO pps_enum_data_hosts"
								+ "(host_id, name, zone_id, type, status, connections,load_factor, date_expired) "
								+ "values "
								+ "(1000,'migrate_account_host',-100,-100, 'X', 0, 0, '3000-01-01')");
				stmt.executeUpdate();
				con.commit();
				rs.close();
				stmt = con
						.prepareStatement("INSERT INTO pps_sequences (host_id, name, start_value, end_value) "
								+ "SELECT CAST(1000 AS INT), 'ACL', seq, seq + "
								+ blockSize
								+ " "
								+ "FROM (SELECT MAX(end_value) AS seq FROM pps_sequences) a");
				stmt.executeUpdate();
				con.commit();
			} else {
				stmt = con
						.prepareStatement("UPDATE pps_sequences "
								+ "SET start_value = (SELECT MAX(end_value) FROM pps_sequences), "
								+ "end_value = (SELECT MAX(end_value) FROM pps_sequences) + "
								+ blockSize + " " + "WHERE host_id = 1000");
				stmt.executeUpdate();
				con.commit();
			}
			stmt = con.prepareStatement("SELECT start_value "
					+ "FROM pps_sequences " + "WHERE host_id = 1000");
			rs = stmt.executeQuery();
			while (rs.next())
				startId = rs.getLong("start_value");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return startId;
	}

	private static void replaceIDs() throws Exception {
		try {
			Element rootElement = idMapDoc.getRootElement();
			List<Element> idList = rootElement.getChildren();
			String oldId;
			String newId;
			for (Element idElement : idList) {
				oldId = idElement.getAttributeValue("old");
				newId = idElement.getAttributeValue("new");
				List<Element> workList = XPath.newInstance(
						"//data[@value=" + oldId + "]").selectNodes(
						doc.getRootElement());
				for (Element workItem : workList) {
					workItem.removeAttribute("value");
					workItem.setAttribute(new Attribute("value", newId));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void writeXML(String outFile, Document doc) throws Exception {
		try {
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			File result = new File(outFile);
			result.createNewFile();
			FileOutputStream fout = new FileOutputStream(result);
			outputter.output(doc, fout);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private static void handleFields() throws Exception {
		PreparedStatement stmt = con
				.prepareStatement("SELECT field_id, xml_name "
						+ "FROM pps_field_names ");
		ResultSet rs = stmt.executeQuery();
		Map<String, List<Long>> fieldNamesMap = new HashMap<String, List<Long>>();
		while (rs.next()) {
			String xmlName = rs.getString("xml_name");
			List<Long> idList = fieldNamesMap.get(xmlName);
			if (idList == null) {
				idList = new ArrayList<Long>();
				fieldNamesMap.put(xmlName, idList);
			}
			idList.add(rs.getLong("field_id"));
		}

		List<Element> ppsFieldNamesRows = XPath.newInstance(
				"//table[@name='pps_field_names']/row").selectNodes(
				doc.getRootElement());

		for (Element ppsFieldNameRow : ppsFieldNamesRows) {

			Element ppsXmlNameElement = (Element) (XPath
					.newInstance("./data[@name='XML_NAME']")
					.selectSingleNode(ppsFieldNameRow));

			Element ppsFieldIdElement = (Element) (XPath
					.newInstance("./data[@name='FIELD_ID']")
					.selectSingleNode(ppsFieldNameRow));

			Long fieldId = new Long(ppsFieldIdElement
					.getAttributeValue("value"));

			String xmlName = ppsXmlNameElement.getAttributeValue("value");

			List<Long> idList = fieldNamesMap.get(xmlName);

			if (idList != null) {
				if (!(idList.contains(fieldId))) {
					Element rootElement = idMapDoc.getRootElement();
					if (XPath.newInstance("//id[@old=" + fieldId + "]")
							.selectSingleNode(rootElement) == null) {
						Element idEntryElement = new Element("id");
						idEntryElement.setAttribute(new Attribute("old",
								fieldId.toString()));
						idEntryElement.setAttribute("new", idList.get(0)
								.toString());
						rootElement.addContent(idEntryElement);
					} else {
						System.out.println("WARNING--Attempt to remap "
								+ fieldId);
						continue;
					}
				}
				ppsFieldNameRow.setAttribute(new Attribute("ignore", "true"));
			}
		}
	}

	private static void removeIgnoredRows() throws Exception {
		List<Element> tableElementList = XPath.newInstance("//table")
				.selectNodes(doc.getRootElement());
		for (Element tableElement : tableElementList) {
			List<Element> rowList = XPath.newInstance("./row[@ignore='true']")
					.selectNodes(tableElement);
			for (Element row : rowList) {
				tableElement.removeContent(row);
			}
		}
	}
}
