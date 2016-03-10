package com.common.util;

public class SysConstant {
	public static final String SYS_PROPERTIES = "properties";
	public static final String SSO_TICKETS = "sso.tickets";
	public static final String SSO_PROXY_VALIDATE = "sso.proxyValidate";
	public static final String SSO_AUS_SERVICE = "sso.aus.service";

	public static enum JsonResult {
		Result("result", "结果"), Success("success", "操作成功"), Fail("fail", "操作失败"), Msg(
				"msg", "信息");
		private String v;

		private String index;

		private String value;

		JsonResult(String index) {
			v = value;
		}

		JsonResult(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (JsonResult c : JsonResult.values()) {
				if (c.getIndex().equals(index)) {
					return c.getValue();
				}
			}
			return null;
		}

		@Override
		public String toString() {
			return v;
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * 请求路径ENUM
	 * 
	 * @author yuxinchen
	 * 
	 */
	public static enum UrlEnum {
		UrlCode("urlCode", "请求路径码"), UrlList("urlList", "请求路径集合"), URL("url",
				"请求路径"), TempUrlList("tempUrlList", "模板路径集合");
		private String v;

		private String index;

		private String value;

		UrlEnum(String index) {
			v = value;
		}

		UrlEnum(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (UrlEnum c : UrlEnum.values()) {
				if (c.getIndex().equals(index)) {
					return c.getValue();
				}
			}
			return null;
		}

		@Override
		public String toString() {
			return v;
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
