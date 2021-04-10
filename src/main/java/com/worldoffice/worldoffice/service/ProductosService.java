package com.worldoffice.worldoffice.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.worldoffice.worldoffice.entity.Productos;

public interface ProductosService {

	ResponseEntity<String> buscarTodosLosProductos() throws Exception;
	
	ResponseEntity<String> buscarRangoPrecio(BigDecimal precioMin, BigDecimal precioMax, Pageable pageable) throws Exception;

	ResponseEntity<String> buscarNombre(String nombre, Pageable pageable) throws Exception;

	ResponseEntity<String> buscarMarca(String marca, Pageable pageable) throws Exception;
	
	void crearProductos(List<? extends Productos> items) throws Exception;

	boolean buscarDuplicado(Productos productosTemp) throws Exception;
}
