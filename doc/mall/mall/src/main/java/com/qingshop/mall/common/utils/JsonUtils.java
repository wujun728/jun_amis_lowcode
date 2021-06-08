package com.qingshop.mall.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具类
 */
public class JsonUtils {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	private JsonUtils() {

	}

	public static ObjectMapper getInstance() {
		return objectMapper;
	}

	/**
	 * object转化json
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String beanToJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * json转对象
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToBean(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * json转map
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> jsonToMap(String jsonStr) throws Exception {
		return objectMapper.readValue(jsonStr, Map.class);
	}

	/**
	 * json转map，指定类型
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> jsonToMap(String jsonStr, Class<T> clazz) throws Exception {
		Map<String, Map<String, Object>> map = (Map<String, Map<String, Object>>) objectMapper.readValue(jsonStr,
				new TypeReference<Map<String, T>>() {
				});
		Map<String, T> result = new HashMap<String, T>();
		for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), mapToBean(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * json数组转list，指定类型
	 * 
	 * @param jsonArrayStr
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonToList(String jsonArrayStr, Class<T> clazz) throws Exception {
		List<Map<String, Object>> list = (List<Map<String, Object>>) objectMapper.readValue(jsonArrayStr,
				new TypeReference<List<T>>() {
				});
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(mapToBean(map, clazz));
		}
		return result;
	}

	/**
	 * map转化对象
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T mapToBean(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}

	/**
	 * 获取集合形式的对象
	 * 
	 * @param json
	 * @param t    @see <br>
	 *             com.fasterxml.jackson.core.type.TypeReference<br>
	 *             如果要获取User 类型的 数组, 则参数为： new TypeRefernce<List<User>>(){}
	 * @return
	 */
	public static synchronized List<?> toList(String json, TypeReference<?> t) {
		try {
			return (List<?>) objectMapper.readValue(json, t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> Collection<T> parseArray(String json, Class<T> clazz) {
		Collection<T> result = null;
		try {
			result = objectMapper.readValue(json,
					objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
