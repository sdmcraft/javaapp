package tools;

import junit.framework.Assert;

import java.lang.reflect.Field;


public class TestUtils
{
    public static void inspect(Object object, String s_field, Object expected) throws Exception
    {
        Field field = object.getClass().getDeclaredField(s_field);
        field.setAccessible(true);
        Assert.assertEquals(expected, field.get(object));
    }
}
