package com.wb.master.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dm.common.util.JsonUtil;
import com.dm.master.model.UserMaster;
import com.wb.common.util.MessageUtil;
import com.wb.master.service.MasterServiceI;

@Controller
@RequestMapping("/userMasterController")
public class UserMasterController {
	@Autowired
	private MasterServiceI userMasterService;

	/**
	 * 添加师傅详细信息
	 * 
	 * @param response
	 * @param account
	 */
	@RequestMapping(params = "addUserMaster")
	public void register(HttpServletResponse response, UserMaster userMaster) {
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();

			userMasterService.addUserMaster(userMaster);
			resultMap.put("msg", MessageUtil.getMsg("MSG_USER_MASTER_0001"));
			JsonUtil.writeJson(response, resultMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
