package migrate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

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

	public static void main(String[] args) {
		init();
		replaceIDs();
		writeXML();
	}

	private static void init() {
		try {
			doc = builder.build(new FileInputStream("C:/temp/result.xml"));
			idMapDoc = builder.build(new FileInputStream(
			"C:/temp/idMap.xml"));			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void buildIDMap()
	{
		Element rootElement = idMapDoc.getRootElement();
		Element accountElement = doc.getRootElement();
		List<String> idList = new ArrayList<String>();
	}

	private static void replaceIDs() {
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
		}
	}

	private static void writeXML() {
		try {
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			File transformedResult = new File("C:/temp/transformedResult.xml");
			transformedResult.createNewFile();
			FileOutputStream fout = new FileOutputStream(transformedResult);
			outputter.output(doc, fout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
