package com.linghushaoxia.reflect;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.linghushaoxia.reflect.bytecode.MethodGenerator;

import sun.reflect.MethodAccessor;
/**功能说明：
 * @author:linghushaoxia
 * @time:2017年5月4日下午11:24:22
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class ReflectCache {
		//方法缓存
		private static Map<MethodProperty, MethodAccessor> methodCache = new ConcurrentHashMap<MethodProperty, MethodAccessor>();
		/**
		 * 
		 * 功能说明:获取指定的方法
		 * 作为测试,方法的返回值默认为空
		 * @param className
		 * 类名
		 * @param methodName
		 * 方法名
		 * @param params
		 * 方法参数
		 * @return MethodAccessor
		 * @time:2017年5月7日下午12:31:09
		 * @author:linghushaoxia
		 * @exception:
		 *
		 */
		public static MethodAccessor getMethod(String className,String methodName,Class<?> returnType,Class<?>... params){
			//返回结果
			MethodAccessor methodResult=null;
			MethodProperty methodProperty = new MethodProperty(className,methodName, params);
			if (methodCache.containsKey(methodProperty)) {
				methodResult = methodCache.get(methodProperty);
			}else {
				methodResult= MethodGenerator.generateMethod(className, methodName, returnType, Modifier.PUBLIC, params);
				methodCache.put(methodProperty, methodResult);
			}
			return methodResult;
		}
}

/**
* 现实就是实现理想的过程
*/