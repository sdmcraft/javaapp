package algorithms;

import java.util.Calendar;

public class SortedArray implements Array {

    public int elements[];
    private int count;
    int ops;

    public SortedArray(int size) {
        elements = new int[size];
        count = 0;
    }

    public SortedArray() {
        int len = (int) (Math.random() * 10);
        elements = new int[len];
        for (int i = 0; i < len; i++) {
            elements[i] = (int) (Math.random() * len);
        }
        count = len;
    }

    public SortedArray(SortedArray arr) {
        this.elements = new int[arr.elements.length];
        int j = 0;
        for (int i : arr.elements) {
            this.elements[j++] = i;
        }
    }

    public SortedArray(int[] elements) {
        this.elements = elements;
    }
    
    public static void main(String[] args) {
        SortedArray arr = new SortedArray();
        System.out.println("Unsorted Array:");
        arr.display();
//        arr.bubbleSort1();
        System.out.println("\nSorted Array:");
        arr.insertionSort();
        arr.display();
        System.out.println("Median:"+arr.median());
//        
//        //arr.elements = new int[]{9,4,1,3,8,8,4,9,5,6};
//        arr.display();
//        //arr.externalSort(10);
//        arr.quickSort(0,arr.elements.length-1);
//        //arr.shellSort();
//        
//        arr.display();
//        System.out.println("Total ops:"+arr.ops);
//        SortedArray.analyze();

    }

    public static void analyze() {
        SortedArray arr = new SortedArray();

        SortedArray arr1 = new SortedArray(arr);
        long start = Calendar.getInstance().getTimeInMillis();
        arr1.bubbleSort();
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Bubble Sort:" + (end - start));

        SortedArray arr2 = new SortedArray(arr);
        start = Calendar.getInstance().getTimeInMillis();
        arr2.insertionSort();
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Insertion Sort:" + (end - start));

        SortedArray arr3 = new SortedArray(arr);
        start = Calendar.getInstance().getTimeInMillis();
        arr3.selectionSort();
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Selection Sort:" + (end - start));

//        SortedArray arr4 = new SortedArray(arr);
//        start = Calendar.getInstance().getTimeInMillis();
//        arr4.mergeSort(0, arr4.elements.length - 1);
//        end = Calendar.getInstance().getTimeInMillis();
//        System.out.println("Merge Sort:" + (end - start));

        SortedArray arr5 = new SortedArray(arr);
        start = Calendar.getInstance().getTimeInMillis();
        arr5.shellSort();
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Shell Sort:" + (end - start));

        SortedArray arr6 = new SortedArray(arr);
        start = Calendar.getInstance().getTimeInMillis();
        arr6.bubbleSort1();
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Bubble1 Sort:" + (end - start));
        arr6.display();

    }

    public boolean insert(int item) {
        boolean status = false;
        if (count < elements.length) {
            elements[count++] = item;
            status = true;
        }
        return status;
    }

    public void display() {
        System.out.println("");
        for (int element : elements) {
            System.out.print(element + " ");
        }
        System.out.println("");
    }

    public void bubbleSort() {
        for (int outer = elements.length; outer > 0; outer--) {
            for (int inner = 0; inner < outer - 1; inner++) {
                if (elements[inner] > elements[inner + 1]) {
                    int temp = elements[inner];
                    elements[inner] = elements[inner + 1];
                    elements[inner + 1] = temp;
                }
            }
        }
    }

    public void bubbleSort1() {
        int leftLimit = 0;
        int rtLimit = elements.length;
        for (; rtLimit > leftLimit; rtLimit--) {
            for (int inner = leftLimit; inner < rtLimit - 1; inner++) {
                if (elements[inner] > elements[inner + 1]) {
                    int temp = elements[inner];
                    elements[inner] = elements[inner + 1];
                    elements[inner + 1] = temp;
                }
            }

            for (int inner = rtLimit - 1; inner > leftLimit; inner--) {
                if (elements[inner] < elements[inner - 1]) {
                    int temp = elements[inner];
                    elements[inner] = elements[inner - 1];
                    elements[inner - 1] = temp;
                }
            }
            leftLimit++;
        }
    }

    public void selectionSort() {
        for (int outer = 0; outer < elements.length; outer++) {
            int min = outer;
            for (int inner = outer; inner < elements.length; inner++) {
                if (elements[inner] < elements[min]) {
                    min = inner;
                }
            }
            int temp = elements[min];
            elements[min] = elements[outer];
            elements[outer] = temp;
        }
    }

    public void insertionSort() {
        for (int outer = 1; outer < elements.length; outer++) {
            int temp = elements[outer];
            int inner = outer;
            while (inner > 0 && temp < elements[inner - 1]) {
                elements[inner] = elements[inner - 1];
                inner--;
            }
            elements[inner] = temp;
        }
    }

    public double median() {
        this.insertionSort();
        double median = 0;
        if ((elements.length % 2) == 0) {
            median = (double)(elements[(int)(elements.length / 2 - 1)] + elements[(int)(elements.length / 2)]) / 2;
        } else {
            median = elements[(int) elements.length / 2];
        }
        return median;
    }
    
    public void removeDups()
    {
        this.insertionSort();
        int marker = 0;
        for(int i=0;i<elements.length-1;i++)
        {
            if(elements[i] != elements[i+1])
            {
                if(marker != i)
                    elements[marker] = elements[i];
                marker++;
            }
        }
        for(int i=marker;i<elements.length;i++)
        {
            elements[i] = -1;
        }
    }
    
    @Override
    public boolean equals(Object arr)
    {
        boolean result = false;
        if(this == arr)
            result = true;
        else if((arr != null) && (arr instanceof SortedArray))
        {
            if(((SortedArray)arr).elements == this.elements)
                result = true;
            else if(((SortedArray)arr).elements.length != this.elements.length)
                result = false;
            else 
            {
                int i=0;
                for(;i<this.elements.length;i++)
                    if(this.elements[i]!= ((SortedArray)arr).elements[i])
                        break;
                if(i == this.elements.length)
                    result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.elements != null ? this.elements.hashCode() : 0);
        return hash;
    }

//    public void mergeSort(int start, int end) {
//        if (start == end) {
//            return;
//        }
//        int mid = (start + end) / 2;
//        mergeSort(start, mid);
//        mergeSort(mid + 1, end);
//        merge(start, end, mid);
//    }

//    public void merge(int start, int end, int mid) {
//        int arrA[] = new int[mid - start + 1];
//        int arrB[] = new int[end - mid];
//        int j = 0;
//        for (int i = start; i <= mid; i++) {
//            arrA[j++] = elements[i];
//        }
//        j = 0;
//        for (int i = mid + 1; i <= end; i++) {
//            arrB[j++] = elements[i];
//        }
//        int arrC[] = Merge.merge(arrA, arrB);
//        for (int i : arrC) {
//            elements[start++] = i;
//        }
//    }

    public void shellSort() {
        int h = 1;
        while (h <= elements.length / 3) {
            h = 3 * h + 1;
        }
        while (h > 0) {
            for (int outer = h; outer < elements.length; outer = outer + h) {
                int temp = elements[outer];
                int inner = outer;
                while (inner > (h - 1) && temp < elements[inner - h]) {
                    elements[inner] = elements[inner - h];
                    inner -= h;
                }
                elements[inner] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    public static int[] insertionSortSvc(int[] elements) {
        for (int outer = 1; outer < elements.length; outer++) {
            int temp = elements[outer];
            int inner = outer;
            while (inner > 0 && temp < elements[inner - 1]) {
                elements[inner] = elements[inner - 1];
                inner--;
            }
            elements[inner] = temp;
        }
        return elements;
    }

    /*public int partition(int start,int end,int pivot) {
    int leftPtr = start-1;
    int rtPtr = end;
    while(true) {
    while(elements[++leftPtr] < pivot)
    ops++;
    while((rtPtr > 0) && (elements[--rtPtr] >= pivot))
    ops++;
    if(leftPtr > rtPtr)
    break;
    else {
    int temp = elements[leftPtr];
    elements[leftPtr] = elements[rtPtr];
    elements[rtPtr] = temp;
    }
    }
    int temp = elements[leftPtr];
    elements[leftPtr] = elements[end];
    elements[end] = temp;
    return leftPtr;
    }*/
    public int partition(int start, int end, int pivot) {
        int leftPtr = start;
        int rtPtr = end - 1;
        while (true) {
            while ((leftPtr <= end) && (elements[leftPtr] <= pivot)) {
                leftPtr++;
            }
            while ((rtPtr > start + 1) && (elements[rtPtr] > pivot)) {
                rtPtr--;
            }
            System.out.println("Left Ptr:" + leftPtr + " Rt Ptr:" + rtPtr);
            if (leftPtr < rtPtr) {
                int temp = elements[leftPtr];
                elements[leftPtr] = elements[rtPtr];
                elements[rtPtr] = temp;
                leftPtr++;
                rtPtr--;
            } else {
                break;
            }
        }
        int temp = elements[leftPtr];
        elements[leftPtr] = elements[end];
        elements[end] = temp;
        System.out.println("Pivot location:" + leftPtr);
        return leftPtr;
    }

    public void quickSort(int start, int end) {
        if ((end - start) <= 0) {
            return;
        } else {
            System.out.println("\n\nWorking on array:");
            for (int i = start; i <= end; i++) {
                System.out.print(elements[i] + " ");
            }
            int pivot = elements[end];
            System.out.println("\nPivot:" + pivot);
            int newPivotLoc = partition(start, end, pivot);
            System.out.println("After partitioning:");
            for (int i = start; i <= end; i++) {
                System.out.print(elements[i] + " ");
            }
            quickSort(start, newPivotLoc - 1);
            quickSort(newPivotLoc + 1, end);
        }
    }

    public void externalSort(int memorySize) {
        int n = elements.length / memorySize;
        int[] memory = new int[memorySize];
        int start = 0;
        int end = 0;
        while (end < elements.length) {
            for (int i = 0; i < memorySize; i++) {
                memory[i] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < memorySize; i++) {
                if (end < elements.length) {
                    memory[i] = elements[end++];
                }
            }
            //System.out.println("Read till:"+end);
            memory = insertionSortSvc(memory);
            for (int i = 0; i < memorySize; i++) {
                if (start < elements.length) {
                    elements[start++] = memory[i];
                //System.out.println("Sorted till:"+start);
                }
            }
        }
        int matrix[][] = new int[n][memorySize];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < memorySize; j++) {
                //System.out.print(elements[k]+" ");
                matrix[i][j] = elements[k++];
            }
        //System.out.println("");
        }
    /*Heap heap = new Heap(memory.length);
    for(int i=0;i<memory.length;i++)
    {
    HeapNode node = new HeapNode();
    node.data = elements[i*n];
    node.chunkNumber = i;
    heap.insert(node);
    }*/
    }
}
