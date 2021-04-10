package com.worldoffice.worldoffice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.worldoffice.worldoffice.entity.CarritoCompras;

/**
 * @author Johann Andres Agamez Ferres
 * @version 1.0
 */
public interface CarritosController {

	ResponseEntity<String> agregarProducto(@RequestBody CarritoCompras entity);
	
	ResponseEntity<String> consultarProductosCarrito(int pagina, int tamano);
	
	void deleteCarrito();
	
	void aplicarCompra();

}
