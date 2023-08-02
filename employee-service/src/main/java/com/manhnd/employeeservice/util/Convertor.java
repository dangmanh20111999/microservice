package com.manhnd.employeeservice.util;

import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;

import com.google.gson.Gson;
import com.manhnd.employeeservice.constant.Constants;

public class Convertor {

	public static <T> T objectMapperConvertStringToObject(String data, Class<T> clazz) {
		ObjectMapper om = new ObjectMapper();
		om.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T obj = null;
		try {
			obj = om.readValue(data, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	public static String convertObjectToJson(Object data) {
		String json = Constants.STRING_EMPTY;
		if(ObjectUtils.isNotEmpty(data)) {
			json = new Gson().toJson(data);
		}
		return json;
	}
	public static <T> T convertStringToObject(String data, Class<T> clazz) {
		T obj = new Gson().fromJson(data, clazz);
		return obj;
	}
	public static <T> List<T> objectMapperConvertStringToList(String data, Class<T[]> clazz) {
		ObjectMapper om = new ObjectMapper();
		om.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T[] obj = null;
		try {
			obj = om.readValue(data, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Arrays.asList(obj);
	}
}
