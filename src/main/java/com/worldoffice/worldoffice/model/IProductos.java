package com.worldoffice.worldoffice.model;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.worldoffice.worldoffice.entity.Productos;

public interface IProductos extends CrudRepository<Productos, Integer>, PagingAndSortingRepository<Productos, Integer>,
		JpaSpecificationExecutor<Productos> {

	@Query("SELECT p FROM Productos p WHERE p.precio BETWEEN :precioMin AND :precioMax")
	Page<Productos> buscarRangoPrecio(@Param("precioMin") BigDecimal precioMin, @Param("precioMax") BigDecimal precioMax, Pageable pageable);

	@Query("SELECT p FROM Productos p WHERE p.nombre LIKE %:nombre% ORDER BY p.nombre ASC")
	Page<Productos> buscarNombre(@Param("nombre") String nombre, Pageable pageable);

	@Query("SELECT p FROM Productos p WHERE p.marca = :marca ORDER BY p.nombre ASC")
	Page<Productos> buscarMarca(@Param("marca") String marca, Pageable pageable);
	
	@Query("SELECT p FROM Productos p "
			+ "WHERE p.nombre = :nombre "
			+ "AND p.marca = :marca "
			+ "AND p.precio = :precio "
			+ "AND p.stock = :stock "
			+ "AND p.estado = :estado "
			+ "AND p.descuento = :descuento "
			+ "ORDER BY p.nombre ASC")
	Productos buscarDuplicado(@Param("nombre") String nombre, 
							  @Param("marca") String marca, 
							  @Param("precio") BigDecimal precio, 
							  @Param("stock") int stock, 
							  @Param("estado") String estado, 
							  @Param("descuento") BigDecimal descuento);
}
