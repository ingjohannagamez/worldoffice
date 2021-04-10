package com.worldoffice.worldoffice.controller.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worldoffice.worldoffice.controller.ProductosController;
import com.worldoffice.worldoffice.service.ProductosService;

/**
 * @author pipo0
 *
 */
@RestController
@RequestMapping("/productos")
public class ProductosControllerImpl implements ProductosController, Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private ProductosService productosService;

	@GetMapping("/")
	public ResponseEntity<String> httpStatus() {
		return ResponseEntity.status(HttpStatus.OK).body("Servicio OK");
	}

	@Override
	@GetMapping("/buscarProductos")
	public ResponseEntity<String> buscarProductos() {
		ResponseEntity<String> respuesta = null;
		try {
			respuesta = productosService.buscarTodosLosProductos();
		} catch (Exception e) {
			logger.error("Error interno en buscarProductos");
			respuesta = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error Interno de BuscarProductos");
		}

		return respuesta;
	}

	@Override
	@GetMapping("/buscarRangoPrecio/{precioMin}/{precioMax}/{pagina}/{tamano}")
	public ResponseEntity<String> buscarRangoPrecio(@PathVariable(name = "precioMin") BigDecimal precioMin,
													@PathVariable(name = "precioMax") BigDecimal precioMax, 
													@PathVariable(name = "pagina") int pagina,
													@PathVariable(name = "tamano") int tamano) {
		ResponseEntity<String> respuesta = null;
		Pageable pageable = PageRequest.of(pagina, tamano);
		try {
			respuesta = productosService.buscarRangoPrecio(precioMin, precioMax, pageable);
		} catch (Exception e) {
			logger.error("Error interno en buscarRangoPrecio");
			respuesta = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error Interno de buscarRangoPrecio");
		}

		return respuesta;
	}

	@Override
	@GetMapping("/buscarNombre/{nombre}/{pagina}/{tamano}")
	public ResponseEntity<String> buscarNombre(@PathVariable(name = "nombre") String nombre,
											   @PathVariable(name = "pagina") int pagina, 
											   @PathVariable(name = "tamano") int tamano) {
		ResponseEntity<String> respuesta = null;
		Pageable pageable = PageRequest.of(pagina, tamano);
		try {
			respuesta = productosService.buscarNombre(nombre.toUpperCase(), pageable);
		} catch (Exception e) {
			logger.error("Error interno en buscarNombre");
			respuesta = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error Interno de buscarNombre");
		}

		return respuesta;
	}

	@Override
	@GetMapping("/buscarMarca/{marca}/{pagina}/{tamano}")
	public ResponseEntity<String> buscarMarca(@PathVariable(name = "marca") String marca,
											  @PathVariable(name = "pagina") int pagina, 
											  @PathVariable(name = "tamano") int tamano) {
		ResponseEntity<String> respuesta = null;
		Pageable pageable = PageRequest.of(pagina, tamano);
		try {
			respuesta = productosService.buscarMarca(marca.toUpperCase(), pageable);
		} catch (Exception e) {
			logger.error("Error interno en buscarMarca");
			respuesta = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error Interno de buscarMarca");
		}

		return respuesta;
	}

}
