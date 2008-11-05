/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author satyam
 */
public class DirectoryUtils {

    public static List<File> listContents(File node) {        
        List<File> list = new ArrayList<File>();
        if (node.isFile()) {
            list.add(node);
        } else if(node.isDirectory()){
            File[] contents = node.listFiles();
            for (File f : contents)
            {
                list.addAll(listContents(f));
            }
        }
        return list;
    }
    
    public static List<File> listContents(String root) {        
        return listContents(new File(root));
    }
    
    public static String stripRoot(File f, String root)
    {
        String name = null;
        if(f.toString().substring(0,root.length()).equals(root))
            name = f.toString().substring(root.length());
        return name;
    }
    
    public static void main(String[] args) {
        List<File> list = listContents("C:\\setup");
        for(File f : list)
        {
            System.out.println(f.toString());
        }
    }
}
