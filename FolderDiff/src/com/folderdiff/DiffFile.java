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
import java.util.logging.Level;
import java.util.logging.Logger;

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
}
