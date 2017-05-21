package com.linghushaoxia.reflect.classloader;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**功能说明：反射
 * @author:linghushaoxia
 * @time:2017年5月3日下午10:38:52
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class ReflectClassLoader extends ClassLoader{
	public static ReflectClassLoader getClassLoader() {
		return ClassLoaderHolder.classLoader;
	}
	/**
	 * 
	 * 功能说明:定义class文件
	 * @param name
	 * @param byteCodes void
	 * @time:2017年5月3日下午10:54:49
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public  Class<?> defineClass(String name,byte[] byteCodes){
		return super.defineClass(name, byteCodes, 0, byteCodes.length);
	}
	public static Unsafe getUnsafe() {
		   try {
		           Field f = Unsafe.class.getDeclaredField("theUnsafe");
		           f.setAccessible(true);
		           return (Unsafe)f.get(null);
		   } catch (Exception e) { 
		       /* ... */
		   }
		return null;
 }
	private ReflectClassLoader(){
		
	}
	private static class ClassLoaderHolder{
		private static ReflectClassLoader classLoader = new ReflectClassLoader();
	}
}

/**
* 现实就是实现理想的过程
*/