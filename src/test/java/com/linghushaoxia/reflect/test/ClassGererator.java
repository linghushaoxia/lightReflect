package com.linghushaoxia.reflect.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import com.linghushaoxia.reflect.bytecode.MethodGenerator;

/**功能说明：测试类生成
 * @author:linghushaoxia
 * @time:2017年5月20日下午12:37:48
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class ClassGererator {
public static void main(String[] args) {
	String className="com.linghushaoxia.reflect.test.Business";
	String methodName = "run";
	Class<?> returnType = Void.class;
	int modifier = Modifier.PUBLIC;
	Class<?>[] params={int.class,int.class,char.class,Long.class};
	MethodGenerator.generateMethod(className, methodName, returnType, modifier, params);
	
}

}

/**
* 现实就是实现理想的过程
*/