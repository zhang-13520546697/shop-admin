/** 
 * <pre>项目名称:shop-admin1 
 * 文件名称:DateAUtil.java 
 * 包名:com.fh.shop.util 
 * 创建日期:2019年6月9日下午5:27:44 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * <pre>项目名称：shop-admin1    
 * 类名称：DateAUtil    
 * 类描述：    
 * 创建人：Mr.zhang    2424968072@qq.com
 * 创建时间：2019年6月9日 下午5:27:44    
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改时间：2019年6月9日 下午5:27:44    
 * 修改备注：       
 * @version </pre>    
 */
public class DateAUtil {
	
	public static final String YMD= "yyyy-MM-dd";
	public static final String Y= "yyyy";
	public static final String FULL_TIME ="yyyy-MM-dd HH:mm:ss";
	
	public static String  datastr(Date date,String pattern){
		//为空判定  没有值返回空字符串
		if (date == null) {
			return "";
		}
		SimpleDateFormat sim=	new SimpleDateFormat(pattern);
		String res = sim.format(date);
		//System.out.println(date);
		return res;
	}

}
