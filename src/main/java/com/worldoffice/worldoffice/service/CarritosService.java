package com.worldoffice.worldoffice.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.worldoffice.worldoffice.entity.CarritoCompras;

public interface CarritosService {

	ResponseEntity<String> agregarProducto(CarritoCompras entity) throws Exception;

	ResponseEntity<String> consultarProductosCarrito(Pageable pageable) throws Exception;

	void deleteCarrito() throws Exception;

	void aplicarCompra() throws Exception;

}
