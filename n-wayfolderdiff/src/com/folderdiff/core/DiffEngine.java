/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 *
 * @author satyam
 */
public class DiffEngine {
    
    private static final int BATCH_SIZE = 50;
    private static final Logger  logger = Logger.getLogger(com.folderdiff.core.DiffEngine.class);
    
    public static List<DiffFile> doDiff(String[] roots) throws Exception {
    	logger.info("(+)doDiff(+)");
        List<DiffFile> diffFileList = new ArrayList<DiffFile>();
        Thread[] threadList = new Thread[roots.length];
        int i=0;
        for(String root : roots)
        {
            threadList[i] = new Thread(new CollateRootTask(root, diffFileList));
            threadList[i].start();
            i++;            
        }
        for(i=0;i<threadList.length;i++)
            threadList[i].join();
                  
        List<DiffFile[]> batches = buildBatches(diffFileList);
        threadList = new Thread[batches.size()];              
        i=0;
        for (DiffFile[] batch : batches) {
            threadList[i] = new Thread(new BuildTextBucketsTask(batch));
            threadList[i].start();
            i++;            
        }
        for(i=0;i<threadList.length;i++)
            threadList[i].join();
        logger.info("(-)doDiff(-)");
        return diffFileList;        
    }
    
    private static List<DiffFile[]> buildBatches(List<DiffFile> diffFileList)
    {
        List<DiffFile[]> batches = new ArrayList<DiffFile[]>();
        DiffFile[] diffFileArray = diffFileList.toArray(new DiffFile[0]);
        DiffFile[] batch = new DiffFile[BATCH_SIZE];
        int count = 0, count1 = 0;
        while(count < diffFileArray.length)
        {
            batch[count1] = diffFileArray[count];
            count++;
            count1++;
            if(count1 == BATCH_SIZE)
            {
                batches.add(batch);
                count1 = 0;
                batch = new DiffFile[BATCH_SIZE];
            }
        }
        batches.add(batch);
        return batches;
    }

    List<String> summarizeDiff(List<DiffFile> list) {
        List<String> summary = new ArrayList<String>();
        summary.add("Total number of files compared: " + list.size());
        return summary;
    }

//    public static boolean diffFile(String f1, String f2) throws Exception {
//        return diffFile(new File(f1), new File(f2));
//    }
//
//    public static boolean diffFile(File f1, File f2) throws Exception {
//        boolean result = true;
//        FileReader reader1 = new FileReader(f1);
//        FileReader reader2 = new FileReader(f2);
//        int c1 = -1;
//        int c2 = -1;
//        while (((c1 = reader1.read()) != -1) & ((c2 = reader2.read()) != -1)) {
//            if (c1 != c2) {
//                result = false;
//                break;
//            }
//        }
//        if (c1 != c2) {
//            result = false;
//        }
//
//        return result;
//    }

    public static Map<String, List<File>> diffFileMD5(List<File> list) throws Exception {

        Map<String, List<File>> map = new HashMap<String, List<File>>();
        for (File f : list) {
            String key = md5Digester(f);
            List<File> sameTextList = map.get(key);
            if (sameTextList == null) {
                sameTextList = new ArrayList<File>();
                map.put(key, sameTextList);
            }
            sameTextList.add(f);
        }
        return map;
    }

    public static String md5Digester(File f) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        InputStream is = new FileInputStream(f);
        byte[] buffer = new byte[8192];
        int read = 0;
        String output = "";
        try {
            while ((read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            output = bigInt.toString(16);
        } catch (IOException e) {
            throw new RuntimeException("Unable to process file for MD5", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
            }
        }
        return output;
    }

    public static File writeToHtml(List<DiffFile> list) throws IOException {
    	logger.info("(+)writeToHtml(+)");    	    	
        File html = new File(DirectoryUtils.getInstallDir()+ "temp/temp.html");
        html.createNewFile();
        PrintWriter out = null;
        String[] colorlist = new String[]{"00FFFF",
                                          "CCFF33",
                                          "FF99FF",
                                          "66FF66",
                                          "CCFFFF",
                                          "99FF00",
                                          "FFFF66",
                                          "FFFFFF",
                                          "CCCCCC",
                                          "FFFF00"};                                          
        try {
            out = new PrintWriter(html);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        out.write("<HTML>\n");
        out.write("\t<HEAD>\n");
        out.write("\t</HEAD>\n");
        out.write("\t<BODY>\n");
        out.write("\t\t<H2><FONT COLOR=\"FF0000\">Files in same color have no difference.</FONT></H2>\n");
        out.write("\t\t<script TYPE=\"text/javascript\">\n");
        out.write("\t\t<!--\n");
        out.write("\t\tfunction myPopup() {\n");
        out.write("\t\t\twindow.open( \""+ DirectoryUtils.getHelpUrl()+"#understandingOutput\" )\n");
        out.write("\t\t}\n");
        out.write("\t\t//-->\n");
        out.write("\t\t</script>\n");
        out.write("\t\t<form>\n");
        out.write("\t\t\t<INPUT  TYPE=\"button\" ONCLICK=\"myPopup()\" VALUE=\"Click me to understand output!\"/>\n");
        out.write("\t\t</form>\n");
        out.write("\t\t<HR width=\"1000\" color=\"black\" size=\"2\" align=\"left\">\n");
        for (DiffFile diffFile : list) {
            Set<Entry<String, List<File>>> entrySet = diffFile.textBuckets.entrySet();
            out.write("\t\t<TABLE BORDER=\"2\">\n");
            out.write("\t\t\t<TBODY>\n");
            int i = 0;
            for (Entry<String, List<File>> entry : entrySet) {
                String color = colorlist[(i++) % colorlist.length];
                for (File f : entry.getValue()) {
                    out.write("\t\t\t\t<TR BGCOLOR=\"" + color + "\">\n");
                    out.write("\t\t\t\t\t<TD>\n");
                    out.write("\t\t\t\t\t\t"+f.toString()+"\n");
                    out.write("\t\t\t\t\t</TD>\n");
                    out.write("\t\t\t\t</TR>\n");
                }
            }
            out.write("\t\t\t</TBODY>\n");
            out.write("\t\t</TABLE>\n");
            out.write("\t\t<BR>\n");
            out.write("\t\t<HR width=\"1000\" color=\"black\" size=\"2\" align=\"left\">\n");
            out.write("\t\t<BR>\n");
        }
        out.write("\t\t<form>\n");
        out.write("\t\t\t<INPUT  TYPE=\"button\" ONCLICK=\"myPopup()\" VALUE=\"Click me to understand output!\"/>\n");
        out.write("\t\t</form>\n");
        out.write("\t</BODY>\n");
        out.write("</HTML>");
        out.close();
        logger.info("(-)writeToHtml(-)");
        return html;
    }
}
