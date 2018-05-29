package com.allen.schoolo2o.util;

import javax.servlet.http.HttpServletRequest;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月28日 下午7:09:41 
*/
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
	if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
		return false;
	}
	return true;
	
	
	
	}

}
 