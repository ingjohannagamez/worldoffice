package com.worldoffice.worldoffice.job;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.worldoffice.worldoffice.entity.Productos;
import com.worldoffice.worldoffice.service.ProductosService;

public class ProductosWriter implements ItemWriter<Productos> {
	
	@Autowired
	private ProductosService productosService;
	
	private static final Logger LOG = LogManager.getLogger(ProductosWriter.class);

	@Override
	public void write(List<? extends Productos> items) throws Exception {
		LOG.info("Escribiendo {} registros", items.size());
		productosService.crearProductos(items);
	}

}
