/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misc;

import java.util.List;

/**
 *
 * @author satyam
 */
class Gen1
{
    
}

class Gen2 extends Gen1
{
    
}

class Gen3 extends Gen2
{
    
}

public class GenericsDemo {
    public static void main(String[] args) {        
        List<? extends Gen2> list1 = null;
        List<? super Gen2> list2 = null;

        List<Gen2> list3 = (List<Gen2>) list1;
        //List<Gen1> list4 = (List<Gen1>) list1;
        List<Object> list5 = (List<Object>)list2;
        //List<Gen1> list5 = (List<Gen1>)list2;
    }

}
