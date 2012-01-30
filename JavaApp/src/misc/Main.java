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

        System.out.println("SELECT * FROM ( " + "SELECT DISTINCT SA.Version , " + "AI.INTERACTION_ID, T.ASSET_ID, " + "AI.NAME, AI.INTERACTION_TYPE, AI.DISPLAY_SEQ, AI.DESCRIPTION " + "FROM " +
            "(SELECT MAX(VERSION) as Version, SCO_ID, ASSET_ID " + "FROM PPS_SCO_ASSETS " + "GROUP BY SCO_ID, ASSET_ID) AS SA " + "JOIN PPS_TRANSCRIPTS T " +
            "ON T.SCO_ID = SA.SCO_ID AND T.ASSET_ID = SA.ASSET_ID " + "JOIN PPS_TRANSCRIPT_DETAILS A " + "ON T.TRANSCRIPT_ID = A.TRANSCRIPT_ID " + "JOIN PPS_ASSET_INTERACTIONS as ai " +
            "ON AI.INTERACTION_ID = A.INTERACTION_ID  " + "WHERE AI.INTERACTION_TYPE NOT IN(?,?,?) " + " AND T.SCO_ID = ?  " + ") A1 WHERE 1=1");
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
