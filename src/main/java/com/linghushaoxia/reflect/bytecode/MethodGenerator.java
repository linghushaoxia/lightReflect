package com.linghushaoxia.reflect.bytecode;

import java.lang.reflect.Modifier;

import com.linghushaoxia.reflect.classloader.ReflectClassLoader;

import sun.reflect.MethodAccessor;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**功能说明：基于javassist的反射方法生成器
 * @author:linghushaoxia
 * @time:2017年5月1日下午11:50:06
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class MethodGenerator {
	private static long count=0;
	/**
	 * 
	 * 功能说明:根据传入参数生成目标方法的代理对象
	 * @param className
	 * 类名
	 * @param methodName
	 * 方法名
	 * @param returnType
	 * 返回类型
	 * @param modifier
	 * 访问权限修饰符
	 * @param params
	 * 参数类型列表
	 * @return MethodAccessor
	 * @time:2017年5月1日下午11:56:57
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	public static MethodAccessor generateMethod(String className,String methodName,Class<?> returnType,int modifier,Class<?>...params){
		MethodAccessor methodResult=null;
		try {
			ClassPool classPool = ClassPool.getDefault();
			/**
			 * 以下手工方式写出代理方法
			 * 作为示例,略去了异常处理和校验
			 */
			//生成的类名
			String classname="sun.reflect.GeneratedMethodAccessor$"+count++;
			CtClass ctClass= classPool.makeClass(classname);
			classPool.importPackage("java.lang");
			classPool.importPackage("java.lang.reflect");
			ctClass.setModifiers(Modifier.PUBLIC);
			//实现MethodAccessor,和反射api无缝结合
			CtClass[] interfaces={classPool.get("sun.reflect.MethodAccessor")};
			ctClass.setInterfaces(interfaces);
			//方法体
			StringBuilder proxyInvokeBody = new StringBuilder();
			//调用目标类的参数传值
			StringBuilder targetParamValues = new StringBuilder();
			for(int  i=0;i<params.length;i++){
				if (isPrime(params[i])) {
					targetParamValues.append(processPrimeType(params[i], i));
				}else {
					targetParamValues.append("(").append(params[i].getName()).append(")").append(" params[").append(i).append("]");
				}
			    if (i<params.length-1) {
			    	targetParamValues.append(",");
			    }
			}
			
		 	proxyInvokeBody.append("public Object invoke(Object obj,").append("Object[] params").append(")").append(" throws IllegalArgumentException, InvocationTargetException ").append("{");
		    proxyInvokeBody.append("\n	");
		    proxyInvokeBody.append(className).append(" target").append("=").append("(").append(className).append(")").append("obj").append(";");
		    proxyInvokeBody.append("\n	");
		    if (returnType==Void.class) {
			    proxyInvokeBody.append("\n target.").append(methodName).append("(").append(targetParamValues.toString()).append(")").append(";");
			    proxyInvokeBody.append("\n return null;");

			}else {
			    proxyInvokeBody.append("\n return  	target.").append(methodName).append("(").append(targetParamValues.toString()).append(")").append(";");
			}
		    proxyInvokeBody.append("\n	}");
			CtMethod invokePrint =CtMethod.make(proxyInvokeBody.toString(), ctClass);
			
			ctClass.addMethod(invokePrint);
			//自定义的classloader,生成class
			ReflectClassLoader classLoader = ReflectClassLoader.getClassLoader();
			Class<?> generateClass= classLoader.defineClass(classname, ctClass.toBytecode());
			//保存到磁盘,查看具体代码
			ctClass.writeFile(classname);
			methodResult= (MethodAccessor) generateClass.newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return methodResult;
	}
	/**
	 * 
	 * 功能说明:判断是否是基本类型
	 * @param cla
	 * 类类型
	 * @return boolean
	 * @time:2017年5月21日下午1:18:27
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private static boolean isPrime(Class<?> cla){
		boolean isPrime = 
				cla==boolean.class||cla==byte.class||cla==char.class
				||cla==short.class||cla==int.class||cla==long.class||
				cla==double.class||cla==float.class;
		return isPrime;
	}
	/**
	 * 
	 * 功能说明:处理基本类型,封装为基本类型的参数形式
	 * 主要是需要进行类型转换,而非直接强转
	 * @param cla
	 * 类类型
	 * @param i
	 * 在参数数组中的索引
	 * @return String
	 * @time:2017年5月21日下午1:18:59
	 * @author:linghushaoxia
	 * @exception:
	 *
	 */
	private static String processPrimeType(Class<?> cla,int i){
		StringBuilder builder = new StringBuilder();
		Class<?>[] primeClass={boolean.class,byte.class,
				short.class,long.class,
				double.class,float.class};
		String resultPlaceHolder = builder
				.append("((UpType)params[").append(i).append("]")
				.append(").lowTypeValue()").toString();	
		/**
		 * 特殊处理
		 * char,Object转为Integer再转为char
		 * int,包装器类型是Integer而非Int
		 */
		if (cla==char.class) {
//			resultPlaceHolder="String.valueOf( params["+i+"]).charAt(0)";
//			return resultPlaceHolder;
			resultPlaceHolder=resultPlaceHolder.replace("lowType","char");
			resultPlaceHolder=resultPlaceHolder.replace("UpType","Character");
			return resultPlaceHolder;
		}
		if (cla==int.class) {
			resultPlaceHolder=resultPlaceHolder.replace("lowType","int");
			resultPlaceHolder=resultPlaceHolder.replace("UpType","Integer");
			return resultPlaceHolder;
		}
		for(Class<?> type:primeClass){
			if (cla==type) {
				resultPlaceHolder=resultPlaceHolder.replace("lowType",type.getName());
				resultPlaceHolder=resultPlaceHolder.replace("UpType",type.getName().substring(0,1).toUpperCase()+type.getName().substring(1,type.getName().length()));
				break;
			}
		}
		return resultPlaceHolder;
	}
}

/**
* 现实就是实现理想的过程
*/