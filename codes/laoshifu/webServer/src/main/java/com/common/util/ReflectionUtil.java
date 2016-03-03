package com.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectionUtil {
	/**
	 * model 属性转化为field名字
	 * 
	 * @param cla
	 * @return
	 */
	public static List<String> modelPropertyToFileName(Class cla) {
		List<String> filedNameList = new ArrayList<String>();
		Field[] fields = cla.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			fields[i].getName();
		}
		return filedNameList;
	}

	/**
	 * 通过class创建新对象
	 * 
	 * @param cla
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static List<Object> createObjListByClass(Class cla, List resultMap)
			throws Exception {

		List<Object> objList = new ArrayList<Object>();
		Set<String> fieldKeySet = null;
		Iterator fieldKey = null;
		String fieldKeyName = null;
		String methodName = null;
		String propName = null;
		Map modelMap = null;
		Method method = null;
		Class paramClass = null;
		Map<String, Field> classFieldMap = new HashMap<String, Field>();
		Field[] fields = cla.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			classFieldMap.put(fields[i].getName(), fields[i]);
		}

		for (int i = 0; i < resultMap.size(); i++) {

			Object obj = cla.newInstance();
			modelMap = (Map) resultMap.get(i);
			fieldKeySet = modelMap.keySet();
			fieldKey = fieldKeySet.iterator();
			while (fieldKey.hasNext()) {
				fieldKeyName = fieldKey.next().toString();

				propName = FieldUtil.fieldNameToModelPropName(fieldKeyName);
				methodName = FieldUtil.getSetMethodNameByPropName(propName);

				if (null != classFieldMap.get(propName)) {
					paramClass = classFieldMap.get(propName).getType();
					method = cla.getDeclaredMethod(methodName, paramClass);
					method.invoke(obj, modelMap.get(fieldKeyName));
				}

			}
			objList.add(obj);

		}

		return objList;

	}

	/**
	 * 通过class获取对象map
	 * 
	 * @param cla
	 * @param resultMap
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Map<Object, List<Object>> createListObjMapByClass(Class cla,
			List resultMap, String key) throws Exception {

		List<Object> objList = null;
		Map<Object, List<Object>> objMap = new HashMap<Object, List<Object>>();
		Set<String> fieldKeySet = null;
		Iterator fieldKey = null;
		String fieldKeyName = null;
		String methodName = null;
		String propName = null;
		Map modelMap = null;
		Method method = null;
		Class paramClass = null;
		Map<String, Field> classFieldMap = new HashMap<String, Field>();
		String keyValue = null;

		Field[] fields = cla.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			classFieldMap.put(fields[i].getName(), fields[i]);
		}

		for (int i = 0; i < resultMap.size(); i++) {

			Object obj = cla.newInstance();
			modelMap = (Map) resultMap.get(i);
			fieldKeySet = modelMap.keySet();
			fieldKey = fieldKeySet.iterator();
			while (fieldKey.hasNext()) {
				fieldKeyName = fieldKey.next().toString();

				propName = FieldUtil.fieldNameToModelPropName(fieldKeyName);
				methodName = FieldUtil.getSetMethodNameByPropName(propName);
				paramClass = classFieldMap.get(propName).getType();
				method = cla.getDeclaredMethod(methodName, paramClass);
				method.invoke(obj, modelMap.get(fieldKeyName));

				if (key.equals(propName)) {
					keyValue = String.valueOf(modelMap.get(fieldKeyName));
				}
			}

			if (objMap.containsKey(keyValue)) {
				objMap.get(keyValue).add(obj);
			} else {
				objList = new ArrayList<Object>();
				objList.add(obj);
				objMap.put(keyValue, objList);
			}

		}

		return objMap;

	}

	/**
	 * 通过class获取对象map
	 * 
	 * @param cla
	 * @param resultMap
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Map<Object, Object> createObjMapByClass(Class cla,
			List resultMap, String key) throws Exception {

		List<Object> objList = null;
		Map<Object, Object> objMap = new HashMap<Object, Object>();
		Set<String> fieldKeySet = null;
		Iterator fieldKey = null;
		String fieldKeyName = null;
		String methodName = null;
		String propName = null;
		Map modelMap = null;
		Method method = null;
		Class paramClass = null;
		Map<String, Field> classFieldMap = new HashMap<String, Field>();
		String keyValue = null;

		Field[] fields = cla.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			classFieldMap.put(fields[i].getName(), fields[i]);
		}

		for (int i = 0; i < resultMap.size(); i++) {

			Object obj = cla.newInstance();
			modelMap = (Map) resultMap.get(i);
			fieldKeySet = modelMap.keySet();
			fieldKey = fieldKeySet.iterator();
			while (fieldKey.hasNext()) {
				fieldKeyName = fieldKey.next().toString();

				propName = FieldUtil.fieldNameToModelPropName(fieldKeyName);
				methodName = FieldUtil.getSetMethodNameByPropName(propName);
				paramClass = classFieldMap.get(propName).getType();
				method = cla.getDeclaredMethod(methodName, paramClass);
				method.invoke(obj, modelMap.get(fieldKeyName));

				if (key.equals(propName)) {
					keyValue = String.valueOf(modelMap.get(fieldKeyName));
				}
			}

			objMap.put(keyValue, obj);

		}

		return objMap;

	}

	/**
	 * 通过表字段名称获取对象对应属性值
	 * 
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object getPropertyValueByFieldName(Class cla,
			String fieldName, Object obj) throws Exception {
		String methodName = FieldUtil.getGetMethodName(fieldName);

		Method method = cla.getDeclaredMethod(methodName, null);
		return method.invoke(obj, null);

	}

	// /**
	// * 获取更新fileds
	// * @param cla
	// * @param obj
	// * @return
	// * @throws Exception
	// */
	// public static String getUpdateFileds(Class cla,Object obj) throws
	// Exception {
	// StringBuffer updateFiledsBuffer=new StringBuffer();
	// Field[] fields = cla.getDeclaredFields();
	// String methodName = null;
	// Method method =null;
	//
	// for(int i=0;i<fields.length;i++){
	// methodName = FieldUtil.getGetMethodNameByPropName(fields[i].getName());
	// method = cla.getDeclaredMethod(methodName, null);
	// Object value=method.invoke(obj, null);
	// fields[i].getClass();
	//
	// updateFiledsBuffer.append(fields[i].getName());
	// updateFiledsBuffer.append("=");
	// updateFiledsBuffer.append(value);
	// updateFiledsBuffer.append(",");
	// }
	//
	// return updateFiledsBuffer.toString();
	//
	// }
}
