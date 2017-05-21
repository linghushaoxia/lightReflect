package com.linghushaoxia.reflect;

import java.util.Arrays;

/**功能说明:方法属性描述
 * @author:linghushaoxia
 * @time:2017年4月30日下午4:27:39
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class MethodProperty {
	//类名
	private String className;
	//方法名
	private String methodName;
	//方法参数
	private Class<?>[] params;
	public MethodProperty(){
		
	}
	public MethodProperty(String className ,String methodName,Class<?> ... params){
		this.className=className;
		this.methodName=methodName;
		this.params = params;
	}
	@Override
	public boolean equals(Object object){
		if (object==null) {
			return false;
		}
		if (this==object) {
			return true;
		}else {
			if (object instanceof MethodProperty) {
				MethodProperty methodProperty = (MethodProperty)object;
				//方法名相同
				if (className!=null&&methodProperty.getClassName()!=null
						&&className.equals(methodProperty.getClassName())
						&&methodName!=null&&methodProperty.getMethodName()!=null
						&&methodName.equals(methodProperty.getMethodName())) {
					if (params==null&&methodProperty.getParams()==null) {
						return true;
					}else {
						if (params!=null&&methodProperty.getParams()!=null&&params.length==methodProperty.getParams().length) {
							for(int i=0;i<params.length;i++){
								if (params[i]!=methodProperty.getParams()[i]) {
									return false;
								}
							}
							return true;
						}
					}
					
				}
			}
		}
		return false;
		
	}
	//hash值的求解方法可以改善
	@Override
	public int hashCode(){
		int result=19;
		//类名
		result=className.hashCode()*result+methodName.hashCode();
		//参数的hash值
		if (params!=null) {
			for (Class<?> class1 : params) {
				if (class1.getName()!=null) {
					result = result*class1.getName().hashCode();
				}
			}
		}
		
		return result;
		
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getParams() {
		return params;
	}
	public void setParams(Class<?>[] params) {
		this.params = params;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "{\""
				+ (className != null ? "className\":\"" + className + "\",\""
						: "")
				+ (methodName != null ? "methodName\":\"" + methodName
						+ "\",\"" : "")
				+ (params != null ? "params\":\"" + Arrays.toString(params)
						: "") + "\"}";
	}
	
}

/**
* 现实就是实现理想的过程
*/