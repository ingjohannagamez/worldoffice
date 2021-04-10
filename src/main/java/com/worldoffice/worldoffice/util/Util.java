package com.worldoffice.worldoffice.util;

import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldoffice.worldoffice.entity.CarritoCompras;
import com.worldoffice.worldoffice.entity.Productos;

public class Util {
	
	public String convertToJson(List<Productos> productos) throws Exception {
		ObjectMapper map = null;
		String jsonInString;
		map = new ObjectMapper();
		jsonInString = map.writerWithDefaultPrettyPrinter().writeValueAsString(productos);

		return jsonInString;
	}
	
	public String convertToJson(Productos productos) throws Exception {
		ObjectMapper map = null;
		String jsonInString;
		map = new ObjectMapper();
		jsonInString = map.writerWithDefaultPrettyPrinter().writeValueAsString(productos);

		return jsonInString;
	}
	
	public String convertToJson(CarritoCompras carritoCompras) throws Exception {
		ObjectMapper map = null;
		String jsonInString;
		map = new ObjectMapper();
		jsonInString = map.writerWithDefaultPrettyPrinter().writeValueAsString(carritoCompras);

		return jsonInString;
	}
	
	public static String convertDate(long fecha, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(fecha).toString();
	}

}
