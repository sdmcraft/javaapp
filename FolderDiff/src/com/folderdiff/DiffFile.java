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
public class DiffFile {
    public String name;
    public List<File> copies;

    public DiffFile(String stippedName,File f) {
        copies = new ArrayList<File>();
        copies.add(f);
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
