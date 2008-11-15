/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff;

import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sdmahesh
 */
public class DirectoryUtilsTest {

    public DirectoryUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    public void setupListContents() {
        String curDir = System.getProperty("user.dir");
        System.out.println("Current Directory:"+curDir);
        new File(curDir+File.separator+"testDir").mkdir();
        System.out.println("Current Directory:" + curDir);
        new File(curDir + File.pathSeparator + "testDir").mkdir();
    }

    public void tearDown11ListContents()
    {
        String curDir = System.getProperty("user.dir");        
        new File(curDir+File.separator+"testDir").delete();
    }
    
    /**
     * Test of listContents method, of class DirectoryUtils.
     */
    @Test
    public void listContents() {
        setupListContents();
        String curDir = System.getProperty("user.dir");
        List<File> result = DirectoryUtils.listContents(curDir+File.separator+"testDir");
        assertEquals(0,result.size());  
        tearDown11ListContents();
    }
//    @Test
//    public void listContents() {
//        setupListContents();
//        File node = null;
//        List<File> expResult = null;
//        List<File> result = DirectoryUtils.listContents(node);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of stripRoot method, of class DirectoryUtils.
     */
    @Test
    public void stripRoot() {
        String root = "C:\\";
        String f = "C:\\test";
        boolean expResult = true;
        String result = null;
        try {
            result = DirectoryUtils.stripRoot(f, root);
        } catch (Exception ex) {
            fail("The test case is a prototype.");
            ex.printStackTrace();
        }
        assertEquals(expResult, "test".equals(result));

        root = "C:\\root\\";
        f = "C:\\root\\test";
        expResult = true;
        result = null;
        try {
            result = DirectoryUtils.stripRoot(f, root);
        } catch (Exception ex) {
            fail("The test case is a prototype.");
            ex.printStackTrace();
        }
        assertEquals(expResult, "test".equals(result));

        root = "/root/";
        f = "/root/test";
        expResult = true;
        result = null;
        try {
            result = DirectoryUtils.stripRoot(f, root);
        } catch (Exception ex) {
            fail("The test case is a prototype.");
            ex.printStackTrace();
        }
        assertEquals(expResult, "test".equals(result));

        root = "/root/";
        f = "/root1/test";
        expResult = true;
        result = null;
        try {
            result = DirectoryUtils.stripRoot(f, root);
        } catch (Exception ex) {
            assertEquals(true, "Incorrect root for this file. Cannot be stripped!!!".equals(ex.getMessage()));
        }

        
    }
//
//    /**
//     * Test of chooseRoot method, of class DirectoryUtils.
//     */
//    @Test
//    public void chooseRoot() {
//        System.out.println("chooseRoot");
//        File f = null;
//        String[] roots = null;
//        int[] expResult = null;
//        int[] result = DirectoryUtils.chooseRoot(f, roots);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//

}