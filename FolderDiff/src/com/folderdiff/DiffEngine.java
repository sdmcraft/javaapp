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

    public static List<DiffFile> doDiff(String[] roots)
    {
        List[] fileLists = new ArrayList[roots.length];
        List<DiffFile> diffFileList = new ArrayList<DiffFile>();
        int counter = 0;
        for(String root : roots)
        {
            fileLists[counter++] = DirectoryUtils.listContents(root);
        }
        counter = 0;
        for(List<File> fileList : fileLists)
        {
            for(File f : fileList)
            {
                String strippedName = DirectoryUtils.stripRoot(f, roots[counter]);
                boolean found = false;
                for(DiffFile diffFile : diffFileList)
                {
                    if(strippedName.equals(diffFile.name))
                    {
                        diffFile.copies.add(f);
                        found = true;
                        break;
                    }
                }
                if(!found)
                {                    
                    diffFileList.add(new DiffFile(strippedName, f));
                }
            }
            counter++;
        }
        return diffFileList;
    }
    
    public static void writeToHtml(List<DiffFile> list,String path)
    {
        File html = new File(path);
        PrintWriter out = null;
        try {
            out = new PrintWriter(html);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        out.write("<HTML>");
        out.write("<BODY>");
        for(DiffFile f : list)
        {
            out.write("<BR>");
            out.write(f.toString());
        }        
        out.write("</BODY>");
        out.write("</HTML>");
        out.close();
    }
    
}
