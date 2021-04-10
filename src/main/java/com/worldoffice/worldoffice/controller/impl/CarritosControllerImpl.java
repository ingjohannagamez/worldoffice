package com.worldoffice.worldoffice.controller.impl;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worldoffice.worldoffice.controller.CarritosController;
import com.worldoffice.worldoffice.entity.CarritoCompras;
import com.worldoffice.worldoffice.service.CarritosService;

/**
 * @author pipo0
 *
 */
@RestController
@RequestMapping("/carritos")
public class CarritosControllerImpl implements CarritosController, Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private CarritosService carritosService;

	@GetMapping("/")
	public ResponseEntity<String> httpStatus() {
		return ResponseEntity.status(HttpStatus.OK).body("Servicio OK");
	}

	@Override
	@PostMapping("/agregarProducto")
	public ResponseEntity<String> agregarProducto(@RequestBody CarritoCompras entity) {
		ResponseEntity<String> respuesta = null;
		try {
			if (entity.validaCamposVaciosInsert()) {
				respuesta = carritosService.agregarProducto(entity);
			} else {
				respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("No se han digitado correctamente todos los datos del carrito");
			}
		} catch (Exception e) {
			logger.error("Error interno en buscarProductos");
			respuesta = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error Interno de BuscarProductos");
		}

		return respuesta;
	}

}
