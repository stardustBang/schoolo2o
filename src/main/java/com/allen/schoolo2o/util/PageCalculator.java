package com.allen.schoolo2o.util;

import com.allen.schoolo2o.entity.Shop;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年6月2日 下午12:11:09
 */
public class PageCalculator {

	public static int calculatorRowIndex(int pageIndex, int pageSize) {

		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}

}
