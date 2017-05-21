package com.linghushaoxia.reflect.test;

import java.lang.reflect.InvocationTargetException;

import sun.reflect.MethodAccessor;

import com.linghushaoxia.reflect.ReflectCache;

/**功能说明：
 * @author:linghushaoxia
 * @time:2017年5月21日上午11:22:23
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class MethodTest {
public static void main(String[] args) {
	String className = "com.linghushaoxia.reflect.test.Business";
	String methodName="run";
	Class<?>[] params={int.class,int.class,char.class,Long.class};
	Class<?> returnType = Void.class;
	MethodAccessor methodAccessor=  ReflectCache.getMethod(className, methodName,returnType, params);
	Business obj = new Business();
	Object[] aObjects= {1,2,'!',2L};
	try {
		methodAccessor.invoke(obj, aObjects);
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

/**
* 现实就是实现理想的过程
*/