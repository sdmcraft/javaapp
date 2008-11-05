/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.folderdiff;

import java.util.List;

/**
 *
 * @author satyam
 */
public class Demo {
    public static void main(String[] args) {
        List<DiffFile> list = DiffEngine.doDiff(new String[]{"C:\\shared","C:\\shared1"});
        for(DiffFile f : list)
            System.out.println(f);
    }

}
