package misc;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UnicodeDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		FileOutputStream fout = null;
		Connection srcCon = null;
		OutputStreamWriter writer = null;
		try {
//			File f = new File("C:\\unicode.txt");
//			f.delete();
//			f.createNewFile();
//			fout = new FileOutputStream(f);
//			writer = new OutputStreamWriter(fout,"UTF-16");
//			Format format = Format.getPrettyFormat();
//			format.setEncoding("UTF-16");
//			XMLOutputter outputter = new XMLOutputter(format);
//			Document doc = new Document();
//			Element e = new Element("root");

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			srcCon = DriverManager
					.getConnection("jdbc:sqlserver://satyam-xp:1433;databaseName=prodDB;user=sa;password=breeze");
			PreparedStatement stmt = srcCon
					.prepareStatement("insert into pps_user_sessions values(-1,NULL,'Sbreezm6mkfu7sha5se2zg@1242655261691','2007-08-23 23:44:30.870','166.166.126.52',NULL,'Teresa',NULL,NULL)");
//			ResultSet rs = stmt.executeQuery();
			stmt.executeUpdate();

//			while (rs.next()) {
////				writer.write("ض ض ض");
////				writer.write(rs.getString(1));
//				e.setAttribute(new Attribute("value", rs.getString(1)));
//			}
//			doc.addContent(e);
//			outputter.output(doc, writer);
//			
//			SAXBuilder builder = new SAXBuilder();
//			doc = builder.build(new File("C:\\unicode.txt"));
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getErrorCode());
			System.out.println(ex.getMessage());
			System.out.println(null + "abc");
		}
		finally {
//			writer.close();
//			fout.close();
			srcCon.close();
		}

	}

}
