package com.jun.plugin.system.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {

	/**
	 * 获取树状节点(List遍历实现)
	 *
	 * @param list                要生成树的list列表
	 * @param childrenFieldName   实体类中保存子节点的属性名
	 * @param parentFieldName     保存父级编号的字段
	 * @param nextParentFieldName 查询子集时的存储父级编号值的字段
	 * @param parentId            父级编号
	 * @param clazz               实体类class
	 * @param <T>                 实体类类型
	 * @return 返回树型List
	 * @throws IllegalAccessException    使用class初始化类时异常
	 * @throws InstantiationException    使用class初始化类时异常
	 * @throws InvocationTargetException invoke执行失败
	 * @throws NoSuchFieldException      未查找到对应属性值
	 * @throws NoSuchMethodException     未查找到对应方法
	 */
	public static <T> List<T> getTree(List<T> list, String childrenFieldName, String parentFieldName,
			String nextParentFieldName, Object parentId, Class<T> clazz) throws IllegalAccessException,
			InstantiationException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {

//		当没有数据时直接返回
		if (list == null || list.size() == 0) {
			return null;
		}

		List<T> resList = new ArrayList<>();

//		获取父级编号的方法
		String parentGetMethodName = "get" + parentFieldName.substring(0, 1).toUpperCase()
				+ parentFieldName.substring(1);
//		获取向下递归时的父级编号的值
		String nextParentMethodName = "get" + nextParentFieldName.substring(0, 1).toUpperCase()
				+ nextParentFieldName.substring(1);

//		字段和set方法对应MAP
		Map<String, SetAndGetMethod> fieldMap = new HashMap<>();

		Field[] fields = clazz.getDeclaredFields();
//		获取所有的字段信息，并生成对应的get和set方法名
		for (Field field : fields) {
			String name = field.getName();
			String s1 = name.substring(0, 1);
			String s2 = name.substring(1);

			fieldMap.put(field.getName(),
					new SetAndGetMethod("set" + s1.toUpperCase() + s2, "get" + s1.toUpperCase() + s2));
		}

		for (T t : list) {
//			当传入的父级编号和对象存储的父级编号一致时，新建一个对象复制属性
			if (parentId.equals(clazz.getMethod(parentGetMethodName).invoke(t))) {
				T t1 = clazz.newInstance();
//				遍历所有属性
				for (Map.Entry<String, SetAndGetMethod> entry : fieldMap.entrySet()) {
					String fieldName = entry.getKey();
					Field field = clazz.getDeclaredField(fieldName);
					SetAndGetMethod setAndGetMethod = entry.getValue();

//					对应属性的set方法
					Method setMethod;
//					对应属性的get方法
					Method getMethod;
					try {
						setMethod = clazz.getMethod(setAndGetMethod.getSetMethod(), field.getType());
						getMethod = clazz.getMethod(setAndGetMethod.getGetMethod());
					} catch (NoSuchMethodException e) {
						continue;
					}
					setMethod.setAccessible(true);
					getMethod.setAccessible(true);

//					当属性名和传入的存储子节点的属性名不一致时，直接复制属性值
					if (!fieldName.equals(childrenFieldName)) {
						setMethod.invoke(t1, getMethod.invoke(t));
					}
//					当属性名和传入的存储子节点的属性名一直时，此时生成对应的子节点
					else {
						List<T> temp = new ArrayList<>(list);
						temp.remove(t);
						setMethod.invoke(t1, getTree(temp, childrenFieldName, parentFieldName, nextParentFieldName,
								clazz.getMethod(nextParentMethodName).invoke(t), clazz));
					}
				}
				resList.add(t1);
			}

		}
		return resList.size() > 0 ? resList : null;
	}

//	public static <T> List<T> getTree2(List<T> list, String childrenFieldName, String parentFieldName, String nextParentFieldName, Object parentId, Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//		//		当没有数据时直接返回
//		if (list == null || list.size() == 0) {
//			return null;
//		}
//
//		List<T> resList = new ArrayList<>();
//
////		获取父级编号的方法
//		String parentGetMethodName = "get" + parentFieldName.substring(0, 1).toUpperCase() + parentFieldName.substring(1);
////		获取向下递归时的父级编号的值
//		String nextParentMethodName = "get" + nextParentFieldName.substring(0, 1).toUpperCase() + nextParentFieldName.substring(1);
//
////		字段和set方法对应MAP
//		Map<String, SetAndGetMethod> fieldMap = new HashMap<>();
//
//		Field[] fields = clazz.getDeclaredFields();
////		获取所有的字段信息，并生成对应的get和set方法名
//		for (Field field : fields) {
//			String name = field.getName();
//			String s1 = name.substring(0, 1);
//			String s2 = name.substring(1);
//
//			fieldMap.put(field.getName(),
//					new SetAndGetMethod("set" + s1.toUpperCase() + s2,
//							"get" + s1.toUpperCase() + s2));
//		}
//
//		Map<Object, List<T>> map = new HashMap<>();
//
//		for (T t : list) {
//			Object invoke = clazz.getMethod(parentGetMethodName).invoke(t);
//			if (map.get(invoke) == null) {
//				List<T> tmpList = new ArrayList<>();
//				tmpList.add(t);
//				map.put(invoke, tmpList);
//			} else {
//				List<T> tmpList = map.get(invoke);
//				tmpList.add(t);
//				map.put(invoke, tmpList);
//			}
//		}
//
//		List<T> tList = map.get(parentId);
//
//		for (T t : tList) {
//			resList.add(t);
//		}
//
//		map.remove(parentId);
//
//	}
//
//	public static <T> List<T> getListByMap(Map<Object,List<T>> map,Object parentId,String nextParentFieldName,Class<T> clazz){
//		if(map.get(parentId)==null){
//			return null;
//		}
//		List<T> resList = new ArrayList<>();
//		map.
//	}

}
