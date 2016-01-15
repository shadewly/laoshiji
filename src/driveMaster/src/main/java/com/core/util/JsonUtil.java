package com.core.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * $.ajax后需要接受的JSON
 * 
 * @author
 * 
 */
public class JsonUtil {

	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息

	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public static void writeJson(HttpServletResponse response,
			Map<String, Object> resultMap) throws IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");		
		JSONObject jsonObj = new JSONObject();
		jsonObj.accumulateAll( JSONObject.fromObject(resultMap));
		System.out.println(JSONObject.fromObject(resultMap));
		response.getWriter().print(jsonObj.toString());
	}

}
