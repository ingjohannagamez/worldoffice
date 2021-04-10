package com.worldoffice.worldoffice.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.worldoffice.worldoffice.dto.ProductosDTO;
import com.worldoffice.worldoffice.entity.Productos;
import com.worldoffice.worldoffice.service.ProductosService;

@Scope("step")
public class ProductosProcessor implements ItemProcessor<ProductosDTO, Productos> {
	
	@Autowired
	private ProductosService productosService;

	@Override
	public Productos process(ProductosDTO productosDTO) throws Exception {
		Productos productosTemp = new Productos();
		try {
			if (!Objects.isNull(productosDTO) && validacionString(productosDTO)) {
				productosTemp.setNombre(productosDTO.getNombre().toUpperCase());
				productosTemp.setMarca(productosDTO.getMarca().toUpperCase());
				productosTemp.setPrecio(new BigDecimal(productosDTO.getPrecio()));
				productosTemp.setStock(Integer.parseInt(productosDTO.getStock()));
				productosTemp.setEstado(productosDTO.getEstado().toUpperCase());
				productosTemp.setDescuento(new BigDecimal(productosDTO.getDescuento()));
				productosTemp.setFechaingreso(new Date());
				
				if(validarDatosDB(productosTemp)) {
					productosTemp = null;
				} 
			} else {
				productosTemp = null;
			}
		} catch (NumberFormatException e) {
			productosTemp = null;
		}

		return productosTemp;
	}

	private boolean validacionString(ProductosDTO productosDTO) {
		boolean respuesta = true;
		
		if((productosDTO.getNombre() == null || productosDTO.getNombre().isEmpty()) 
				|| (productosDTO.getMarca() == null || productosDTO.getMarca().isEmpty()) 
				|| (productosDTO.getEstado() == null || productosDTO.getEstado().isEmpty())) {
			respuesta = false;
		}
		
		return respuesta;
	}
	
	private boolean validarDatosDB(Productos productosTemp) throws Exception {		
		return productosService.buscarDuplicado(productosTemp);
	}
}
