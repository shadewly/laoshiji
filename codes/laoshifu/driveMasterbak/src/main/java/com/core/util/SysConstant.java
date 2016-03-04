package com.core.util;



public class SysConstant {
	public static final String BLANK_NULL = "blank";
	public static final String ENCRYPT_KEY = "mju76yhN";
	public static final String ERROR_MSG = "系统异常";
	public static final String DATAGRID_URL = "baseData.do?dataGrid";
	/**
	 * datagrid列表一页默认显示数据条数
	 */
	public static final int ONE_PAGE_DEFUALT_ROWS = 15;
	/**
	 * 系统主页面Code
	 */
	public static final String MAIN_HOME_URL_CODE = "1002";
	/**
	 * 返回页面控制条组件集合
	 */
	public static final String CTR_ITEM_List = "ctrItemList";
	/**
	 * 返回页面列表数据键
	 */
	public static final String DATA_GRID_LIST = "dataGridList";
	/**
	 * 返回页面列表Page
	 */
	public static final String DATA_GRID_PAGE = "dataGridPage";
	/**
	 * 返回页面列表数据键
	 */
	public static final String DATA_GRID_Filed_LIST = "dataGridFiledList";
	/**
	 * 主目录父节点id
	 */
	public static final Integer ROOT_MENU_FATHER_ID = 0;
	/**
	 * servletContext中存放目录常量名
	 */
	public static final String MENU_LIST_MAP = "menuListMap";
	/**
	 * 返回页面title键
	 */
	public static final String TITLE_LIST = "titleList";

	/**
	 * servletContext中存放URL常量名
	 */
	public static final String URL_LIST_MAP = "urlListMap";
	/**
	 * servletContext中存放数据源配置常量名
	 */
	public static final String DB_CONF_LIST_MAP = "dbConfListMap";
	/**
	 * servletContext中存放标题列表常量名
	 */
	public static final String TITLE_LIST_MAP = "titleListMap";
	
	/**
	 * servletContext中存放数据源配置常量名
	 */
	public static final String CONTROL_ITEM_GROUP_LIST_MAP = "controlItemGroupListMap";

	/**
	 * 登陆结果
	 * 
	 * @author yuxinchen
	 * 
	 */
	public static enum LoginStatus {
		Alive("alive", "登陆中"), LoginSuccess("success", "登陆成功"), LoginFailed(
				"failed", "登陆失败");
		private String v;

		private String index;

		private String value;

		LoginStatus(String index) {
			v = value;
		}

		LoginStatus(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (LoginStatus c : LoginStatus.values()) {
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

	public static enum urlType {
		SystemUrl("S", "系统类型URL");
		private String v;

		private String index;

		private String value;

		urlType(String index) {
			v = value;
		}

		urlType(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (LoginStatus c : LoginStatus.values()) {
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
	 * 翻页
	 * @author yuxinchen
	 *
	 */
	public static enum PageBtnType{
		Home("1000002", "首页"), PgUp("1000003", "上一页"), PgDn("1000004", "下一页"), LastPg("1000005", "尾页");
		private String v;

		private String index;

		private String value;

		PageBtnType(String index) {
			v = value;
		}

		PageBtnType(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (LoginStatus c : LoginStatus.values()) {
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
	 * 添加目录组件
	 * 
	 * @author yuxinchen
	 * 
	 */
	public static enum AddMenuItems {
		MenuList("menuList", "目录集合"),DBGroupList("dbGroupList", "数据源组集合"),TitleGroupList("titleGroupList", "列表标题组集合"),TopControlGroupList("topControlGroupList", "上部控制组集合"),BottomControlGroupList("bottomControlGroupList", "下部控制组集合");
		private String v;

		private String index;

		private String value;

		AddMenuItems(String index) {
			v = value;
		}

		AddMenuItems(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (LoginStatus c : LoginStatus.values()) {
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
	
	public static enum JsonResult {
		Result("result","结果"),Success("success", "操作成功"),Fail("fail", "操作失败"),Msg("msg", "信息");
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
		UrlCode("urlCode", "请求路径码"),UrlList("urlList", "请求路径集合"),URL("url","请求路径"),TempUrlList("tempUrlList", "模板路径集合");
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
	
	/**
	 * 控制组件ENUM
	 * 
	 * @author yuxinchen
	 * 
	 */
	public static enum ControlItemEnum {
		ControlItemEle("controlItemEle","控制组件元素"),ControlItem("controlItem","控制组件"),ControlItemGroupList("controlItemGroupList", "控制组件组集合"),ControlItemEleList("ctrItemEleList", "控制组件元素集合"),ControlItemList("controlItemList", "控制组件集合"),ControlItemGroup("controlItemGroup", "控制组件组"),CtrItemGroupMapperCode("ctrItemGroupMapperCode","100008"),CtrGroupItemMapperCode("ctrGroupItemMapperCode","100012"),TitItemGroupMapperCode("titItemGroupMapperCode","100017");
		private String v;

		private String index;

		private String value;

		ControlItemEnum(String index) {
			v = value;
		}

		ControlItemEnum(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (ControlItemEnum c : ControlItemEnum.values()) {
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
	 * 标题ENUM
	 * 
	 * @author yuxinchen
	 * 
	 */
	public static enum TitleEnum {
		TitleGroup("titleGroup","标题组"),Title("title","目录"),TitleList("titleList","标题集合");
		private String v;

		private String index;

		private String value;

		TitleEnum(String index) {
			v = value;
		}

		TitleEnum(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (TitleEnum c : TitleEnum.values()) {
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
	 * 角色权限映射emule
	 * 
	 * @author yuxinchen
	 * 
	 */
	public static enum RoleAuthMapperEnum {
		SearchValueKey("searchValueKey","查询具体值键"),SearchKey("SearchKey","ROLE_CODE"),ReturnDbEleCode("returnDbEleCode","1012"),ReturnTitleGroupMapperCode("returnTitleGroupMapperCode","10011"),CtrItemGroupMapperCode("ctrItemGroupMapperCode","100033"),TitleGroupMapperCode("titleGroupMapperCode","10010"),BottomCtrMapperCode("bottomCtrMapperCode","100002"),DbEleCode("dbEleCode","1011");
		private String v;

		private String index;

		private String value;

		RoleAuthMapperEnum(String index) {
			v = value;
		}

		RoleAuthMapperEnum(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (TitleEnum c : TitleEnum.values()) {
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
	 * 权限资源映射emule
	 * 
	 * @author yuxinchen
	 * 
	 */
	public static enum AuthUrlMapperEnum {
		SearchValueKey("searchValueKey","查询具体值键"),SearchKey("SearchKey","AUTH_CODE"),ReturnDbEleCode("returnDbEleCode","1013"),ReturnTitleGroupMapperCode("returnTitleGroupMapperCode","10012"),CtrItemGroupMapperCode("ctrItemGroupMapperCode","100034"),TitleGroupMapperCode("titleGroupMapperCode","10003"),BottomCtrMapperCode("bottomCtrMapperCode","100002"),DbEleCode("dbEleCode","1003");
		private String v;

		private String index;

		private String value;

		AuthUrlMapperEnum(String index) {
			v = value;
		}

		AuthUrlMapperEnum(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (TitleEnum c : TitleEnum.values()) {
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
	 * 用户角色映射emule
	 * 
	 * @author yuxinchen
	 * 
	 */
	public static enum RoleUserMapperEnum {
		SearchValueKey("searchValueKey","查询具体值键"),SearchKey("SearchKey","USER_NAME"),ReturnDbEleCode("returnDbEleCode","1015"),ReturnTitleGroupMapperCode("returnTitleGroupMapperCode","10014"),CtrItemGroupMapperCode("ctrItemGroupMapperCode","100038"),TitleGroupMapperCode("titleGroupMapperCode","10009"),BottomCtrMapperCode("bottomCtrMapperCode","100002"),DbEleCode("dbEleCode","1010");
		private String v;

		private String index;

		private String value;

		RoleUserMapperEnum(String index) {
			v = value;
		}

		RoleUserMapperEnum(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (TitleEnum c : TitleEnum.values()) {
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
