package com.core.util;

public class FieldUtil {
	/**
	 * 数据库字段名称转化为对象类属性名称
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String fieldNameToModelPropName(String fieldName) {
		String modelPropName = null;
		String[] fieldcharacters = fieldName.split("_");
		StringBuffer metaPropName = new StringBuffer();

		if (fieldcharacters.length > 1) {
			for (int i = 0; i < fieldcharacters.length; i++) {
				if (i == 0) {
					metaPropName.append(fieldcharacters[i].toLowerCase());
				} else {
					metaPropName.append(fieldcharacters[i].substring(0, 1)
							.toUpperCase());
					metaPropName.append(fieldcharacters[i].substring(1)
							.toLowerCase());

					modelPropName = metaPropName.toString();
				}

			}

		} else if (fieldcharacters.length == 1) {
			modelPropName = fieldcharacters[0].toLowerCase();
		}

		return modelPropName;
	}

	/**
	 * 对象类属性名称转化为数据库字段名称
	 * 
	 * @param propName
	 * @return
	 */
	public static StringBuffer modelPropNameTofieldName(String modelPropName) {
		StringBuffer fieldName = new StringBuffer();

		for (int i = 0; i < modelPropName.length(); i++) {
			char c = modelPropName.charAt(i);
			if (Character.isUpperCase(c)) {

				if (modelPropName.length() != i) {
					fieldName.append("_");
				}

			}
			fieldName.append(Character.toUpperCase(c));
		}

		return fieldName;
	}

	/**
	 * 对象类名称转化为数据库表名称
	 * 
	 * @param propName
	 * @return
	 */
	public static String modelClassNameToTableName(String modelClassName) {
		StringBuffer tableName = new StringBuffer();

		for (int i = 0; i < modelClassName.length(); i++) {
			char c = modelClassName.charAt(i);
			if (Character.isUpperCase(c)) {

				if (modelClassName.length() != i && i > 0) {
					tableName.append("_");
				}

			}
			tableName.append(Character.toLowerCase(c));
		}

		return tableName.toString();
	}

	/**
	 * 通过数据库field名获取对象类中对应属性set方法
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String getSetMethodName(String fieldName) {

		StringBuffer setMethodName = new StringBuffer();
		String propName = fieldNameToModelPropName(fieldName);

		setMethodName.append("set");
		setMethodName.append(propName.substring(0, 1).toUpperCase());
		setMethodName.append(propName.substring(1));

		return setMethodName.toString();
	}

	/**
	 * 通过数据库field名获取对象类中对应属性set方法
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String getGetMethodName(String fieldName) {

		StringBuffer getMethodName = new StringBuffer();
		String propName = fieldNameToModelPropName(fieldName);

		getMethodName.append("get");
		getMethodName.append(propName.substring(0, 1).toUpperCase());
		getMethodName.append(propName.substring(1));

		return getMethodName.toString();
	}

	/**
	 * 通过对象类中对应属性名获取该属性set方法
	 * 
	 * @param propName
	 * @return
	 */
	public static String getSetMethodNameByPropName(String propName) {

		StringBuffer setMethodName = new StringBuffer();

		setMethodName.append("set");
		setMethodName.append(propName.substring(0, 1).toUpperCase());
		setMethodName.append(propName.substring(1));

		return setMethodName.toString();
	}

	/**
	 * 通过对象类中对应属性名获取该属性get方法
	 * 
	 * @param propName
	 * @return
	 */
	public static String getGetMethodNameByPropName(String propName) {

		StringBuffer setMethodName = new StringBuffer();

		setMethodName.append("get");
		setMethodName.append(propName.substring(0, 1).toUpperCase());
		setMethodName.append(propName.substring(1));

		return setMethodName.toString();
	}
}
