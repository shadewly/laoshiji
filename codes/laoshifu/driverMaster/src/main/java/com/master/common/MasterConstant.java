package com.master.common;



public class MasterConstant {

	/**
	 * 师傅状态
	 * @author Administrator
	 *
	 */
	public static enum UserMasterStatus {
		Incomplete("待完善", "0"), Pending("待审", "1"),Pass("通过","2");
		private String v;

		private String index;

		private String value;

		UserMasterStatus(String index) {
			v = value;
		}

		UserMasterStatus(String index, String value) {
			this.index = index;
			this.value = value;
		}

		public static String getValue(String index) {
			for (UserMasterStatus c : UserMasterStatus.values()) {
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
