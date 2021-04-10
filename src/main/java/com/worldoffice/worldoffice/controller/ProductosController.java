package com.worldoffice.worldoffice.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;

/**
 * @author Johann Andres Agamez Ferres
 * @version 1.0
 */
public interface ProductosController {

	ResponseEntity<String> buscarProductos();
	
	ResponseEntity<String> buscarRangoPrecio(BigDecimal precioMin, BigDecimal precioMax, int pagina, int tamano);
	
	ResponseEntity<String> buscarNombre(String nombre, int pagina, int tamano);
	
	ResponseEntity<String> buscarMarca(String marca, int pagina, int tamano);

}
