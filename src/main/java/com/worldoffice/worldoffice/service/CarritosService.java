package com.worldoffice.worldoffice.service;

import org.springframework.http.ResponseEntity;

import com.worldoffice.worldoffice.entity.CarritoCompras;

public interface CarritosService {

	ResponseEntity<String> agregarProducto(CarritoCompras entity) throws Exception;

}
