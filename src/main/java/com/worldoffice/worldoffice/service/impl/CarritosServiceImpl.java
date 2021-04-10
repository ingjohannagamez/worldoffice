package com.worldoffice.worldoffice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worldoffice.worldoffice.entity.CarritoCompras;
import com.worldoffice.worldoffice.entity.Productos;
import com.worldoffice.worldoffice.model.ICarritos;
import com.worldoffice.worldoffice.model.IProductos;
import com.worldoffice.worldoffice.service.CarritosService;
import com.worldoffice.worldoffice.util.Util;

@Service
public class CarritosServiceImpl extends Util implements CarritosService {

	@Autowired
	private ICarritos iCarritos;
	
	@Autowired
	private IProductos iProductos;

	@Override
	@Transactional
	public ResponseEntity<String> agregarProducto(CarritoCompras entity) throws Exception {
		ResponseEntity<String> respuesta = null;
		Optional<Productos> productoTemp = iProductos.findById(entity.getIdproducto().getId());
		
		CarritoCompras carritoCompras = (productoTemp != null && (entity.getCantidad() <= productoTemp.get().getStock()))
				? iCarritos.save(entity)
				: null;
	
		if (carritoCompras != null && carritoCompras.getId() != null) {
			respuesta = ResponseEntity.ok(this.convertToJson(carritoCompras));
		} else {
			respuesta = ResponseEntity.status(HttpStatus.CONFLICT).body("No se han podido agregar el producto asegurate que no superes el stock: " + productoTemp.get().getStock());
		}

		return respuesta;
	}

	@Override
	public ResponseEntity<String> consultarProductosCarrito(Pageable pageable) throws Exception {
		Page<CarritoCompras> listCarritoCompras = iCarritos.consultarProductosCarrito(pageable);
		return ResponseEntity
				.ok((listCarritoCompras != null && !listCarritoCompras.isEmpty()) ? this.convertToJson(listCarritoCompras.getContent())
						: "No hay registros en carritos");
	}

	@Override
	public void deleteCarrito() throws Exception {
		iCarritos.deleteAll();
	}

	@Override
	public void aplicarCompra() throws Exception {	
		List<CarritoCompras> lisTemp = (List<CarritoCompras>) iCarritos.findAll();
		
		for(CarritoCompras carritoCompras : lisTemp) {
			carritoCompras.getIdproducto().setStock(carritoCompras.getIdproducto().getStock() - carritoCompras.getCantidad());
			carritoCompras.setEstado("VENDIDO");
			
			iCarritos.save(carritoCompras);
		}
	}

}
