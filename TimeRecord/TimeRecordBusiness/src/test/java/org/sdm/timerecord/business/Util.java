package org.sdm.timerecord.business;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Util {
	public static Object getField(Object object, Class clazz, String fieldName)
			throws Exception {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

	public static Object invokeMethod(Object object, Class clazz,
			String methodName, Class[] argTypes, Object[] args)
			throws Exception {
		Method method = clazz.getDeclaredMethod(methodName, argTypes);
		method.setAccessible(true);
		return method.invoke(object, args);
	}

}
