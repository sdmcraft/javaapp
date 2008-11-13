/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.folderdiff;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author satyam
 */
public class DiffFile {
    public String name;
    public List<File> copies;
    public List<String> presentInRoots;
    public Map<Long,List<String>> timeBuckets;
    public List<List<File>> textBuckets;

    public DiffFile(String stippedName,File f, String root) {
        copies = new ArrayList<File>();
        copies.add(f);
        
        presentInRoots = new ArrayList<String>();        
        presentInRoots.add(root);

        timeBuckets = new HashMap<Long,List<String>>();        
        List<String> list = new ArrayList<String>();
        list.add(f.toString());
        timeBuckets.put(new Long(f.lastModified()), list);
        
        textBuckets = new ArrayList<List<File>>();
        List<File> list2 = new ArrayList<File>();
        list2.add(f);
        textBuckets.add(list2);        
        
        this.name = stippedName;                
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\n");
        for(File f : copies)
        {
            sb.append(f.toString()+" "+f.lastModified());
            sb.append("\n");
        }
        return sb.toString();
    }  
    
    public boolean isModified()
    {
        long lastModified = copies.get(0).lastModified();
        boolean result = false;
        for(File f : copies)
        {
            if(f.lastModified() != lastModified)
            {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public boolean isNew(int numRoots)
    {
        return (copies.size() < numRoots);
    }
}
