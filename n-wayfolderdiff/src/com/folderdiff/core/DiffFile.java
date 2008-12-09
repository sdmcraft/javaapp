/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author satyam
 */
public class DiffFile {

    public String name;
    public List<File> copies;
    public Map<String, List<File>> textBuckets;
    public List<String> roots;

    public DiffFile(String stippedName, File f, String root) {

        this.name = stippedName;

        copies = new ArrayList<File>();
        copies.add(f);

        roots = new ArrayList<String>();
        roots.add(root);
    }

    public void add(File f, String root) throws Exception{
        if(!(DirectoryUtils.stripRoot(f, root).equals(name)))
            throw new Exception("Invalid file for this DiffFile!!!");
        copies.add(f);
        if (!(roots.contains(root))) {
            roots.add(root);
        }
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(name);
//        sb.append("\n");
//        for (File f : copies) {
//            sb.append(f.toString() + " " + f.lastModified());
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//
//    public boolean isModified() {
//        long lastModified = copies.get(0).lastModified();
//        boolean result = false;
//        for (File f : copies) {
//            if (f.lastModified() != lastModified) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }

//    public boolean isNew(int numRoots) {
//        return (copies.size() < numRoots);
//    }

    public void buildTextBuckets() throws Exception {
        textBuckets = DiffEngine.diffFileMD5(copies);
    }
}
