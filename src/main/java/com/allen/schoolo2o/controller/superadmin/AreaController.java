package com.allen.schoolo2o.controller.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.schoolo2o.entity.Area;
import com.allen.schoolo2o.service.AreaService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年5月24日 下午5:28:11
 */

@Controller
@RequestMapping("/superadmin")
public class AreaController {
	
	@Autowired
	private AreaService areaService;

	@RequestMapping(value="/listarea", method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> listArea() {
		List<Area> areaList=new ArrayList<>();
		Map<String,Object> map=new HashMap<>();
	try {
		areaList=areaService.getAreaList();
	   map.put("rows", areaList);
	   map.put("total", areaList.size());
	   return map;
		}catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
	}

}
