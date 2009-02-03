package migrate;

import java.io.FileInputStream;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

public class Migrate {

	private static SAXBuilder builder = new SAXBuilder();
	private static Document confDoc = null;
	private static String sourceDBurl = null;
	private static String targetDBurl = null;
	private static String sourceXML = null;
	private static String targetXML = null;
	private static String idMapXML = null;
	private static String sqlScript = null;
	private static int account_id;
	private static int src_grp_account_id;
	private static int tgt_grp_account_id;
	private static int srs_bama_usr_id;
	private static int tgt_bama_usr_id;

	public static void main(String[] args) throws Exception {
		if (args.length != 1)
			System.out.println("Usage Migrate <path to conf.xml>");
		else
		{
			init(args[0]);
			DBtoXML.service(sourceDBurl, sourceXML, account_id);
			initIDMap();
			TransformXML.service(sourceXML, idMapXML, targetXML, targetDBurl);
			XMLtoDB.service(targetDBurl, targetXML, sqlScript);
		}
	}

	private static void init(String conf) throws Exception {
		try {
			confDoc = builder.build(new FileInputStream(conf));
			Element rootElement = confDoc.getRootElement();
			
			Element srcDB = (Element) XPath.newInstance(
					"//entry[@name='SOURCE_DATABASE']").selectSingleNode(
					rootElement);
			sourceDBurl = createDBurl(srcDB);

			Element tgtDB = (Element) XPath.newInstance(
			"//entry[@name='TARGET_DATABASE']").selectSingleNode(
			rootElement);
			targetDBurl = createDBurl(tgtDB);
						
			Element accountIDelement = (Element) XPath.newInstance(
			"//entry[@name='ACCOUNT']/item[@name='ID']").selectSingleNode(
			rootElement);
			account_id = Integer.parseInt(accountIDelement.getAttributeValue("value"));

			Element sourceXMLpathElement = (Element) XPath.newInstance(
			"//entry[@name='SOURCE_XML']/item[@name='PATH']").selectSingleNode(
			rootElement);
			sourceXML = sourceXMLpathElement.getAttributeValue("value");
			sourceXML = sourceXML.replace(".", "_" + account_id +".");

			Element idMapXMLpathElement = (Element) XPath.newInstance(
			"//entry[@name='ID_MAP_XML']/item[@name='PATH']").selectSingleNode(
			rootElement);
			idMapXML = idMapXMLpathElement.getAttributeValue("value");
			idMapXML = idMapXML.replace(".", "_" + account_id +".");
			
			Element targetXMLpathElement = (Element) XPath.newInstance(
			"//entry[@name='TARGET_XML']/item[@name='PATH']").selectSingleNode(
			rootElement);
			targetXML = targetXMLpathElement.getAttributeValue("value");
			targetXML = targetXML.replace(".", "_" + account_id +".");
			
			Element sqlScriptPathElement = (Element) XPath.newInstance(
			"//entry[@name='SQL_SCRIPT']/item[@name='PATH']").selectSingleNode(
			rootElement);
			sqlScript = sqlScriptPathElement.getAttributeValue("value");
			sqlScript = sqlScript.replace(".", "_" + account_id +".");
			
			Element srcGrpAccIDelement = (Element) XPath.newInstance(
			"//entry[@name='SOURCE_GROUP_ACCOUNT']/item[@name='ID']").selectSingleNode(
			rootElement);
			src_grp_account_id = Integer.parseInt(srcGrpAccIDelement.getAttributeValue("value"));

			Element tgtGrpAccIDelement = (Element) XPath.newInstance(
			"//entry[@name='TARGET_GROUP_ACCOUNT']/item[@name='ID']").selectSingleNode(
			rootElement);
			tgt_grp_account_id = Integer.parseInt(tgtGrpAccIDelement.getAttributeValue("value"));

			Element srsBAMAusrId = (Element) XPath.newInstance(
			"//entry[@name='SOURCE_BAMA_USER']/item[@name='ID']").selectSingleNode(
			rootElement);
			srs_bama_usr_id = Integer.parseInt(srsBAMAusrId.getAttributeValue("value"));

			Element tgtBAMAusrId = (Element) XPath.newInstance(
			"//entry[@name='TARGET_BAMA_USER']/item[@name='ID']").selectSingleNode(
			rootElement);
			tgt_bama_usr_id = Integer.parseInt(tgtBAMAusrId.getAttributeValue("value"));
						
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	private static String createDBurl(Element dbElement)
	{
		String host = null;
		String port = null;
		String dbName = null;
		String user = null;
		String pwd = null;
		
		List<Element> dbDetails = dbElement.getChildren();
		for(Element itemElement : dbDetails)
		{
			if("HOST".equals(itemElement.getAttributeValue("name")))
					host = itemElement.getAttributeValue("value");
			else if("PORT".equals(itemElement.getAttributeValue("name")))
				port = itemElement.getAttributeValue("value");
			else if("DB_NAME".equals(itemElement.getAttributeValue("name")))
				dbName = itemElement.getAttributeValue("value");
			else if("USER".equals(itemElement.getAttributeValue("name")))
				user = itemElement.getAttributeValue("value");
			else if("PASSWORD".equals(itemElement.getAttributeValue("name")))
				pwd = itemElement.getAttributeValue("value");						
		}
		return "jdbc:sqlserver://" + host + ":" + port +
		";databaseName=" + dbName +
		";user=" + user +
		";password=" + pwd;
		
	}
	
	private static void initIDMap() throws Exception
	{
		Element rootElement = new Element("root");
		Element idElement = new Element("id");
		idElement.setAttribute(new Attribute("old",Integer.toString(src_grp_account_id)));
		idElement.setAttribute(new Attribute("new",Integer.toString(tgt_grp_account_id)));
		rootElement.addContent(idElement);
		idElement = new Element("id");
		idElement.setAttribute(new Attribute("old",Integer.toString(srs_bama_usr_id)));
		idElement.setAttribute(new Attribute("new",Integer.toString(tgt_bama_usr_id)));
		rootElement.addContent(idElement);
		Document idMapDoc = new Document(rootElement);
		TransformXML.writeXML(idMapXML, idMapDoc);
	}

}
