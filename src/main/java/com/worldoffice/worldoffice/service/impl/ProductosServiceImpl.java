package com.worldoffice.worldoffice.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worldoffice.worldoffice.entity.Productos;
import com.worldoffice.worldoffice.model.IProductos;
import com.worldoffice.worldoffice.service.ProductosService;
import com.worldoffice.worldoffice.util.Util;

@Service
public class ProductosServiceImpl extends Util implements ProductosService {

	@Autowired
	private IProductos iProductos;

	@Override
	public ResponseEntity<String> buscarTodosLosProductos() throws Exception {
		List<Productos> listProductos = (List<Productos>) iProductos.findAll();
		return ResponseEntity.ok((listProductos != null && !listProductos.isEmpty()) ? this.convertToJson(listProductos)
				: "No hay registros en productos");
	}

	@Override
	public ResponseEntity<String> buscarRangoPrecio(BigDecimal precioMin, BigDecimal precioMax, Pageable pageable)
			throws Exception {
		Page<Productos> listProductos = iProductos.buscarRangoPrecio(precioMin, precioMax, pageable);
		return ResponseEntity
				.ok((listProductos != null && !listProductos.isEmpty()) ? this.convertToJson(listProductos.getContent())
						: "No hay registros en productos con el filtro de precios");
	}

	@Override
	public ResponseEntity<String> buscarNombre(String nombre, Pageable pageable) throws Exception {
		Page<Productos> listProductos = iProductos.buscarNombre(nombre, pageable);
		return ResponseEntity
				.ok((listProductos != null && !listProductos.isEmpty()) ? this.convertToJson(listProductos.getContent())
						: "No hay registros en productos con el filtro de nombres");
	}

	@Override
	public ResponseEntity<String> buscarMarca(String marca, Pageable pageable) throws Exception {
		Page<Productos> listProductos = iProductos.buscarMarca(marca, pageable);
		return ResponseEntity
				.ok((listProductos != null && !listProductos.isEmpty()) ? this.convertToJson(listProductos.getContent())
						: "No hay registros en productos con el filtro de marca");
	}

	@Override
	@Transactional
	public void crearProductos(List<? extends Productos> items) throws Exception {
		iProductos.saveAll(items);
	}

	@Override
	public boolean buscarDuplicado(Productos productosTemp) throws Exception {
		Productos producto = iProductos.buscarDuplicado(
				productosTemp.getNombre(), 
				productosTemp.getMarca(), 
				productosTemp.getPrecio(), 
				productosTemp.getStock(), 
				productosTemp.getEstado(), 
				productosTemp.getDescuento());
		return producto != null;
	}

}
