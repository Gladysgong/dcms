package com.web.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON和JAVA的POJO的相互转换
 *
 * @author qgl
 */
public final class JSONUtil {

	/**
	 * 将String转换成JSON
	 */
	public static String string2json(String key, String value) {
		JSONObject object = new JSONObject();
		object.put(key, value);
		return object.toString();
	}

	/**
	 * 将JSON转换成数组,其中valueClz为数组中存放的对象的Class
	 */
	public static Object json2Array(String json, Class valueClz) {
		return JSONArray.parseArray(json, valueClz);
	}

	// 将数组转换成JSON
	public static String array2json(Object object) {
		return JSONArray.toJSONString(object);
	}

	// 将POJO转换成JSON
	public static String object2Json(Object object) {
		return JSONObject.toJSONString(object);
	}

	// 将JSON转换成POJO,其中beanClz为POJO的Class
	public static Object json2Object(String json, Class beanClz) {
		return JSONObject.parseObject(json, beanClz);
	}

	// 将list转换成json
	public static <T> String list2Json(List<T> list) {
		return JSONArray.toJSONString(list);
	}

	// json转换成list
	public static <T> List<T> json2List(String json, Class beanClz) {
		return JSONArray.parseArray(json, beanClz);
	}
}