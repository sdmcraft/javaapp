/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.test;

import algorithms.PostfixConvertor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author satyam
 */
public class PostfixConvertorTest {        

    public PostfixConvertorTest() {
    }

    @Test
    public void convert() throws Exception {
        assertEquals("ab+",PostfixConvertor.convert("a+b"));        
        assertEquals("ab+c-",PostfixConvertor.convert("a+b-c"));
        assertEquals("a",PostfixConvertor.convert("a"));
        assertEquals("abc-+",PostfixConvertor.convert("a+(b-c)"));
        assertEquals("abc*+",PostfixConvertor.convert("a+b*c"));
        assertEquals("a",PostfixConvertor.convert("(a)"));
        assertEquals("ab*c*",PostfixConvertor.convert("a*b*c"));
        assertEquals("abc-d-+",PostfixConvertor.convert("a+((b-c)-d)"));
        assertEquals("",PostfixConvertor.convert("()"));
        assertEquals("",PostfixConvertor.convert("(()())"));
        assertEquals("ab+c/defg+/+*h-",PostfixConvertor.convert("(a+b)/c*(d+e/(f+g))-h"));
    }

}