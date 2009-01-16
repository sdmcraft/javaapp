/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.test;

import algorithms.SortedArray;
import junit.framework.TestCase;

/**
 *
 * @author satyam
 */
public class SortedArrayTest extends TestCase {

    public SortedArrayTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of main method, of class SortedArray.
     */
//    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        SortedArray.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of analyze method, of class SortedArray.
     */
//    public void testAnalyze() {
//        System.out.println("analyze");
//        SortedArray.analyze();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of insert method, of class SortedArray.
//     */
//    public void testInsert() {
//        System.out.println("insert");
//        int item = 0;
//        SortedArray instance = new SortedArray();
//        boolean expResult = false;
//        boolean result = instance.insert(item);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of display method, of class SortedArray.
//     */
//    public void testDisplay() {
//        System.out.println("display");
//        SortedArray instance = new SortedArray();
//        instance.display();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of bubbleSort method, of class SortedArray.
//     */
//    public void testBubbleSort() {
//        System.out.println("bubbleSort");
//        SortedArray instance = new SortedArray();
//        instance.bubbleSort();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of bubbleSort1 method, of class SortedArray.
//     */
//    public void testBubbleSort1() {
//        System.out.println("bubbleSort1");
//        SortedArray instance = new SortedArray();
//        instance.bubbleSort1();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of selectionSort method, of class SortedArray.
//     */
//    public void testSelectionSort() {
//        System.out.println("selectionSort");
//        SortedArray instance = new SortedArray();
//        instance.selectionSort();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of insertionSort method, of class SortedArray.
//     */
//    public void testInsertionSort() {
//        System.out.println("insertionSort");
//        SortedArray instance = new SortedArray();
//        instance.insertionSort();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of median method, of class SortedArray.
     */
    public void testMedian() {
        System.out.println("median");
        SortedArray instance = new SortedArray(new int[]{1, 2, 3});
        long expResult = 2;
        long result = Math.round(instance.median());
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 2});
        double expResult1 = 1.5;
        double result1 = instance.median();
        assertEquals(expResult1, result1);

        instance = new SortedArray(new int[]{1});
        expResult = 1;
        result = Math.round(instance.median());
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{-1});
        expResult = -1;
        result = Math.round(instance.median());
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{-1, -2});
        expResult1 = -1.5;
        result1 = instance.median();
        assertEquals(expResult1, result1);

        instance = new SortedArray(new int[]{-1, -2, -3});
        expResult = -2;
        result = Math.round(instance.median());
        assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test removeDups
     */
    public void testRemoveDups() {
        SortedArray instance = new SortedArray(new int[]{1, 1, 2, 3});
        instance.removeDups();
        boolean result = instance.equals(new SortedArray(new int[]{1, 2, 3, -1}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 2, 2, 3, 4});
        instance.removeDups();
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4, -1}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 2, 2, 3, 3, 4});
        instance.removeDups();
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4, -1, -1}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 1});
        instance.removeDups();
        result = instance.equals(new SortedArray(new int[]{1, -1}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1});
        instance.removeDups();
        result = instance.equals(new SortedArray(new int[]{1}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 2, 3, 4});
        instance.removeDups();
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 1, 1, 1, 1});
        instance.removeDups();
        result = instance.equals(new SortedArray(new int[]{1, -1, -1, -1, -1}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 2, 2, 3, 4, 4, 4, 5, 6, 6, 7, 7});
        instance.removeDups();
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4, 5, 6, 7, -1, -1, -1, -1, -1}));
        assertEquals(true, result);

    }

    public void testInsertionSortremoveDups() {
        SortedArray instance = new SortedArray(new int[]{1, 1, 2, 3});
        instance.insertionSortremoveDups();
        boolean result = instance.equals(new SortedArray(new int[]{-1,1, 2, 3}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 2, 2, 3, 4});
        instance.insertionSortremoveDups();
        result = instance.equals(new SortedArray(new int[]{-1,1, 2, 3, 4}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 2, 2, 3, 3, 4});
        instance.insertionSortremoveDups();
        result = instance.equals(new SortedArray(new int[]{-1,-1,1, 2, 3, 4}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 1});
        instance.insertionSortremoveDups();
        result = instance.equals(new SortedArray(new int[]{-1, 1}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1});
        instance.insertionSortremoveDups();
        result = instance.equals(new SortedArray(new int[]{1}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 2, 3, 4});
        instance.insertionSortremoveDups();
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 1, 1, 1, 1});
        instance.insertionSortremoveDups();
        result = instance.equals(new SortedArray(new int[]{-1, -1, -1, -1, 1}));
        assertEquals(true, result);

        instance = new SortedArray(new int[]{1, 2, 2, 3, 4, 4, 4, 5, 6, 6, 7, 7});
        instance.insertionSortremoveDups();
        result = instance.equals(new SortedArray(new int[]{-1, -1, -1, -1, -1,1, 2, 3, 4, 5, 6, 7}));
        assertEquals(true, result);
        
        instance = new SortedArray(new int[]{3, 2, 1});
        instance.insertionSortremoveDups();
        boolean expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 2, 3});
        instance.insertionSortremoveDups();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1});
        instance.insertionSortremoveDups();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{2, 1});
        instance.insertionSortremoveDups();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 4, 3, 7, 9, 2, 4, 6, 0, 1});
        instance.insertionSortremoveDups();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{-1,-1, 0, 1, 2, 3, 4,6, 7, 9}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{5, 4, 3, 2, 1});
        instance.insertionSortremoveDups();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4, 5}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{6, 5, 4, 3, 2, 1});
        instance.insertionSortremoveDups();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4, 5, 6}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 1, 2, 2, 3, 3});
        instance.insertionSortremoveDups();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{-1,-1,-1, 1, 2, 3}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{3, 3, 2, 2, 1, 1});
        instance.insertionSortremoveDups();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{-1,-1,-1, 1, 2, 3}));
        assertEquals(expResult, result);
        
        instance = new SortedArray(new int[]{2,2,2,2,2,2,1});
        instance.insertionSortremoveDups();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{-1,-1,-1,-1,-1, 1, 2}));
        assertEquals(expResult, result);        
        

    }
    
    /**
     * Test equals
     */
    public void testEquals() {
        SortedArray instance = new SortedArray(new int[]{1, 2, 3});
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3}));
        assertEquals(expResult, result);

        expResult = false;
        result = instance.equals(null);
        assertEquals(expResult, result);

        expResult = false;
        result = instance.equals("abc");
        assertEquals(expResult, result);

        expResult = false;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4}));
        assertEquals(expResult, result);

        expResult = false;
        result = instance.equals(new SortedArray(new int[]{1, 2, 4}));
        assertEquals(expResult, result);

        expResult = true;
        int[] arr = new int[]{1, 2, 3};
        instance = new SortedArray(arr);
        result = instance.equals(new SortedArray(arr));
        assertEquals(expResult, result);

        expResult = true;
        arr = null;
        instance = new SortedArray(arr);
        result = instance.equals(new SortedArray(arr));
        assertEquals(expResult, result);

    }

    public void testOddEvenSort() {
        SortedArray instance = new SortedArray(new int[]{3, 2, 1});
        instance.oddEvenSort();
        boolean expResult = true;
        boolean result = instance.equals(new SortedArray(new int[]{1, 2, 3}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 2, 3});
        instance.oddEvenSort();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1});
        instance.oddEvenSort();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{2, 1});
        instance.oddEvenSort();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 4, 3, 7, 9, 2, 4, 6, 0, 1});
        instance.oddEvenSort();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{0, 1, 1, 2, 3, 4, 4, 6, 7, 9}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{5, 4, 3, 2, 1});
        instance.oddEvenSort();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4, 5}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{6, 5, 4, 3, 2, 1});
        instance.oddEvenSort();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4, 5, 6}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 1, 2, 2, 3, 3});
        instance.oddEvenSort();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 1, 2, 2, 3, 3}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{3, 3, 2, 2, 1, 1});
        instance.oddEvenSort();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 1, 2, 2, 3, 3}));
        assertEquals(expResult, result);

    }
    
    public void testInsertionSort1() {
        SortedArray instance = new SortedArray(new int[]{3, 2, 1});
        instance.insertionSort1();
        boolean expResult = true;
        boolean result = instance.equals(new SortedArray(new int[]{1, 2, 3}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 2, 3});
        instance.insertionSort1();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1});
        instance.insertionSort1();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{2, 1});
        instance.insertionSort1();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 4, 3, 7, 9, 2, 4, 6, 0, 1});
        instance.insertionSort1();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{0, 1, 1, 2, 3, 4, 4, 6, 7, 9}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{5, 4, 3, 2, 1});
        instance.insertionSort1();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4, 5}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{6, 5, 4, 3, 2, 1});
        instance.insertionSort1();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 2, 3, 4, 5, 6}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{1, 1, 2, 2, 3, 3});
        instance.insertionSort1();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 1, 2, 2, 3, 3}));
        assertEquals(expResult, result);

        instance = new SortedArray(new int[]{3, 3, 2, 2, 1, 1});
        instance.insertionSort1();
        expResult = true;
        result = instance.equals(new SortedArray(new int[]{1, 1, 2, 2, 3, 3}));
        assertEquals(expResult, result);

    }
    
//
//    /**
//     * Test of mergeSort method, of class SortedArray.
//     */
//    public void testMergeSort() {
//        System.out.println("mergeSort");
//        int start = 0;
//        int end = 0;
//        SortedArray instance = new SortedArray();
//        instance.mergeSort(start, end);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of merge method, of class SortedArray.
//     */
//    public void testMerge() {
//        System.out.println("merge");
//        int start = 0;
//        int end = 0;
//        int mid = 0;
//        SortedArray instance = new SortedArray();
//        instance.merge(start, end, mid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of shellSort method, of class SortedArray.
//     */
//    public void testShellSort() {
//        System.out.println("shellSort");
//        SortedArray instance = new SortedArray();
//        instance.shellSort();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of insertionSortSvc method, of class SortedArray.
//     */
//    public void testInsertionSortSvc() {
//        System.out.println("insertionSortSvc");
//        int[] elements = null;
//        int[] expResult = null;
//        int[] result = SortedArray.insertionSortSvc(elements);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of partition method, of class SortedArray.
//     */
//    public void testPartition() {
//        System.out.println("partition");
//        int start = 0;
//        int end = 0;
//        int pivot = 0;
//        SortedArray instance = new SortedArray();
//        int expResult = 0;
//        int result = instance.partition(start, end, pivot);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of quickSort method, of class SortedArray.
//     */
//    public void testQuickSort() {
//        System.out.println("quickSort");
//        int start = 0;
//        int end = 0;
//        SortedArray instance = new SortedArray();
//        instance.quickSort(start, end);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of externalSort method, of class SortedArray.
//     */
//    public void testExternalSort() {
//        System.out.println("externalSort");
//        int memorySize = 0;
//        SortedArray instance = new SortedArray();
//        instance.externalSort(memorySize);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
