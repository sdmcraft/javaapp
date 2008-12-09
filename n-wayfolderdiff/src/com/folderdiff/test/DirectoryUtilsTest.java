/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff.test;

import com.folderdiff.core.DirectoryUtils;
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

    /**
     * Test of listContents method, of class DirectoryUtils.
     */
    @Test
    public void listContents() throws Exception {
        String curDir = System.getProperty("user.dir");
        new File(curDir + File.separator + "testDir").mkdir();
        List<File> result = DirectoryUtils.listContents(curDir + File.separator + "testDir");
        assertEquals(0, result.size());
        (new File(curDir + File.separator + "testDir" + File.separator + "testFile")).createNewFile();
        result = DirectoryUtils.listContents(curDir + File.separator + "testDir");
        assertEquals(1, result.size());
        (new File(curDir + File.separator + "testDir" + File.separator + "testFile")).delete();
        new File(curDir + File.separator + "testDir").delete();
//        result = DirectoryUtils.listContents("C:\\Documents and Settings");
//        for(File f : result)
//            System.out.println(f);
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
            ex.printStackTrace();
            fail();
        }
        assertEquals(expResult, "test".equals(result));

        root = "C:\\root\\";
        f = "C:\\root\\test";
        expResult = true;
        result = null;
        try {
            result = DirectoryUtils.stripRoot(f, root);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
        assertEquals(expResult, "test".equals(result));

        root = "/root/";
        f = "/root/test";
        expResult = true;
        result = null;
        try {
            result = DirectoryUtils.stripRoot(f, root);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
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
            //ex.printStackTrace();
        }

        root = "";
        f = "/root1/test";
        expResult = true;
        result = null;
        try {
            result = DirectoryUtils.stripRoot(f, root);
            assertEquals(true, result.equals("/root1/test"));
        } catch (Exception ex) {            
            ex.printStackTrace();
            fail();
        }

        root = "";
        f = "/root1/test";
        expResult = true;
        result = null;
        try {
            result = DirectoryUtils.stripRoot(f, root);
            assertEquals(true, result.equals("/root1/test"));
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }

        root = "";
        f = "";
        expResult = true;
        result = null;
        try {
            result = DirectoryUtils.stripRoot(f, root);
            assertEquals(true, result.equals(""));
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
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