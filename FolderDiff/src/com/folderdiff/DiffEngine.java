/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author satyam
 */
public class DiffEngine {

    public static List<DiffFile> doDiff(String[] roots) throws Exception {
        List[] fileLists = new ArrayList[roots.length];
        List<DiffFile> diffFileList = new ArrayList<DiffFile>();
        int counter = 0;
        for (String root : roots) {
            fileLists[counter++] = DirectoryUtils.listContents(root);
        }
        counter = 0;
        for (List<File> fileList : fileLists) {
            for (File f : fileList) {
                String strippedName = DirectoryUtils.stripRoot(f, roots[counter]);
                boolean found = false;
                for (DiffFile diffFile : diffFileList) {
                    if (strippedName.equals(diffFile.name)) {
                        diffFile.copies.add(f);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    diffFileList.add(new DiffFile(strippedName, f));
                }
            }
            counter++;
        }
        return diffFileList;
    }

    public static File writeToHtml(List<DiffFile> list, String[] roots) {
        File html = new File("temp.html");
        PrintWriter out = null;
        try {
            out = new PrintWriter(html);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        out.write("<HTML>");
        out.write("<BODY>");
        out.write("<TABLE BORDER=\"2\"");
        out.write("<THEAD>");
        out.write("<TR BGCOLOR=\"GREY\"");
        for (String root : roots) {
            out.write("<TD>");
            out.write("<H4>"+root+"</H4>");
            out.write("</TD>");
        }
        out.write("</TR>");
        out.write("</THEAD>");
        out.write("<TBODY>");
        for (DiffFile diffFile : list) {
            if(diffFile.isNew(roots.length))
                out.write("<TR BGCOLOR=FF0000>");
            else if(diffFile.isModified())
                out.write("<TR BGCOLOR=FFFF00>");
            else
                out.write("<TR BGCOLOR=99FF00>");
            File[] fileList = new File[roots.length];
            for (File f : diffFile.copies) {
                int[] columns = DirectoryUtils.chooseRoot(f, roots);
                for(int i=0;i<columns.length;i++)
                {
                    if(columns[i]==1)
                    fileList[i] = f;
                }
            }
            for (int i = 0; i < fileList.length; i++) {
                out.write("<TD>");
                if(fileList[i] != null)
                    out.write("<B>"+fileList[i].toString()+"</B>");
                out.write("</TD>");
            }
            out.write("</TR>");
        }
        out.write("</TBODY>");
        out.write("</TABLE>");
        out.write("</BODY>");
        out.write("</HTML>");
        out.close();
        return html;
    }
}
