package com.linghushaoxia.reflect.bytecode;

import java.lang.reflect.Modifier;

import javassist.ClassPool;
import javassist.CtClass;
import sun.reflect.FieldAccessor;

/**功能说明：字段访问类生成器
 * @author:linghushaoxia
 * @time:2017年5月21日上午11:20:07
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class FieldGenerator implements FieldAccessor{
	private static int count;
	/**
	 * 
	 * 功能说明:
	 * @param className
	 * @param fieldType
	 * @return FieldAccessor
	 * @time:2017年5月21日下午2:00:45
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static FieldAccessor fieldAccessorGenerator(String className,String fieldName, Class<?> fieldType){
		FieldAccessor fieldAccessor=null;
		try {
			ClassPool classPool = ClassPool.getDefault();
			/**
			 * 以下手工方式写出代理方法
			 * 作为示例,略去了异常处理和校验
			 */
			//生成的类名
			String classname="sun.reflect.GeneratedFieldAccessor$"+count++;
			CtClass ctClass= classPool.makeClass(classname);
			classPool.importPackage("java.lang");
			classPool.importPackage("java.lang.reflect");
			ctClass.setModifiers(Modifier.PUBLIC);
			//实现MethodAccessor,和反射api无缝结合
			CtClass[] interfaces={classPool.get("sun.reflect.FieldAccessor")};
			ctClass.setInterfaces(interfaces);
			StringBuilder fieldAccessorBuilder = new StringBuilder();
			fieldAccessorBuilder.append("@Override").append("\n");
			fieldAccessorBuilder.append("public Object get(Object obj) throws IllegalArgumentException {").append("\n");
			fieldAccessorBuilder.append("return null;").append("\n");
			fieldAccessorBuilder.append("}");
		} catch (Exception e) {
			
		}
		return fieldAccessor;
	}
	@Override
	public Object get(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean getBoolean(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public byte getByte(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public char getChar(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public short getShort(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getInt(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public long getLong(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getFloat(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getDouble(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void set(Object obj, Object value) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setBoolean(Object obj, boolean z)
			throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setByte(Object obj, byte b) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setChar(Object obj, char c) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setShort(Object obj, short s) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setInt(Object obj, int i) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLong(Object obj, long l) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setFloat(Object obj, float f) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDouble(Object obj, double d)
			throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		
	}
}

/**
* 现实就是实现理想的过程
*/