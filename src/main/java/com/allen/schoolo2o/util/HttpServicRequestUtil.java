package com.allen.schoolo2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年5月23日 下午9:31:30
 */
public class HttpServicRequestUtil {

	public static int getInt(HttpServletRequest request, String key) {
		try {
			return Integer.decode(request.getParameter(key));

		} catch (Exception e) {
			return -1;
		}
	}

	public static long getLong(HttpServletRequest request, String key) {
		try {
			return Long.valueOf(request.getParameter(key));

		} catch (Exception e) {
			return -1L;
		}
	}

	public static double getDouble(HttpServletRequest request, String key) {
		try {
			return Double.valueOf(request.getParameter(key));

		} catch (Exception e) {
			return -1d;
		}
	}

	public static Boolean getBoolean(HttpServletRequest request, String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));

		} catch (Exception e) {
			return false;
		}
	}

	public static String getString(HttpServletRequest request, String key) {

		try {
			String result = request.getParameter(key);
			if (result != null) {
				result = result.trim();
			}
			if ("".equals(result)) {
				return null;
			}
			return result;

		} catch (Exception e) {
			return null;
		}
	}

}
