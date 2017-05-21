package com.linghushaoxia.reflect.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**功能说明：方法调用测试
 * @author:linghushaoxia
 * @time:2017年5月21日上午11:12:12
 * @version:1.0
 * 为中国羸弱的技术撑起一片自立自强的天空
 */
public class InvokeTest {
	public static void main(String[] args) {
		Business business = new Business();
		Object[] objects={1,2,'1',1L};
		try {
			invoke(business, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Object invoke(Object arg0, Object[] arg1)
			throws IllegalArgumentException, InvocationTargetException {
		Business arg2 = (Business) arg0;
		arg2.run(Integer.valueOf(String.valueOf(arg1[0])).intValue(), Integer
				.valueOf(String.valueOf(arg1[1])).intValue(), (char) Integer
				.valueOf(String.valueOf(arg1[2])).intValue(), (Long) arg1[3]);
		Character character = '=';
		System.out.println(String.valueOf(character).charAt(0));
		return null;
	}
}

/**
* 现实就是实现理想的过程
*/