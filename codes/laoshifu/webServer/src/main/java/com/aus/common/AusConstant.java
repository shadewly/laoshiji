package com.aus.common;

public class AusConstant {

	/**
	 * 账号状态枚举
	 * 
	 * @author yuxinchen
	 * 
	 */
	public static enum AccountStatus {
		Enable("启用", "0"), Off("禁用", "1");
		private String v;

		private String index;

		private String value;

		AccountStatus(String index) {
			v = value;
		}

		AccountStatus(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (AccountStatus c : AccountStatus.values()) {
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
