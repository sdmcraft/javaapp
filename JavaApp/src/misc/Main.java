package misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main
{
    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws Exception, InterruptedException
    {
        // InternetAddress address = new
        // InternetAddress("\"Satya Deep\"<satyam@adobe.com>");
        // System.out.println(address.getPersonal());
        // System.out.println(address.getAddress());
        //
        // address = new InternetAddress("satyam@adobe.com");
        // System.out.println(address.getPersonal());
        // System.out.println(address.getAddress());
        //
        // address = new InternetAddress("Satya Deep <satyam@adobe.com>");
        // System.out.println(address.getPersonal());
        // System.out.println(address.getAddress());

        // String icalText = readFileAsString("C:\\temp\\a.txt");
        // System.out.println(icalText);
        // icalText = icalText.replaceAll("\r\n|\n", "\\\\n");
        // System.out.println(icalText);
        System.out.println(1f / 2 * 4);

        System.out.println("SELECT TOP " + 5000 + " " +
                "actions.action_id, " +
                "actions.action_type_id, " +
                "actions.target_acl_id, " +
                "prefs.lang, " +
                "actions.body, " +
                "af.field_id, " +
                "af.value, " +
                "actions.status, " +
                "actions.schedule, " +
                "actions.date_scheduled " +
                "FROM " +
                "pps_actions actions LEFT OUTER JOIN pps_acl_preferences prefs ON actions.target_acl_id=prefs.acl_id, " +
                "pps_acl_fields af " +
                "WHERE " +
                "actions.status in ('S', 'P', 'I', 'N', 'R') " +
                "AND actions.date_scheduled < CURRENT_TIMESTAMP " +
                "AND actions.action_id = af.acl_id " +
                "AND actions.zone_id = ? " +
                "ORDER BY " +
                "actions.date_scheduled desc, actions.target_acl_id, actions.action_type_id");
    }

    private static String readFileAsString(String filePath) throws java.io.IOException
    {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;

        while ((numRead = reader.read(buf)) != -1)
        {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }

        reader.close();

        return fileData.toString();
    }

}
