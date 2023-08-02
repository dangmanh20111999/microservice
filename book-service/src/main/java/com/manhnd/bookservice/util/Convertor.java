package com.manhnd.bookservice.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;

public class Convertor {

	public static String objectMapperConvertObjectToString(Object data) {
		ObjectMapper om = new ObjectMapper();
		om.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String json = "";
		try {
			json = om.writeValueAsString(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
