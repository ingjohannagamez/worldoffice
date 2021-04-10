package com.worldoffice.worldoffice.util;

import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	
	public String convertToJson(List<?> lisTemp) throws Exception {
		ObjectMapper map = null;
		String jsonInString;
		map = new ObjectMapper();
		jsonInString = map.writerWithDefaultPrettyPrinter().writeValueAsString(lisTemp);

		return jsonInString;
	}
	
	public <T> String convertToJson(T objTemp) throws Exception {
		ObjectMapper map = null;
		String jsonInString;
		map = new ObjectMapper();
		jsonInString = map.writerWithDefaultPrettyPrinter().writeValueAsString(objTemp);

		return jsonInString;
	}
	
	public static String convertDate(long fecha, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(fecha).toString();
	}

}
