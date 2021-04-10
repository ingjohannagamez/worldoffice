package com.worldoffice.worldoffice.model;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.worldoffice.worldoffice.entity.CarritoCompras;

public interface ICarritos extends CrudRepository<CarritoCompras, Integer>, PagingAndSortingRepository<CarritoCompras, Integer>,
		JpaSpecificationExecutor<CarritoCompras> {

}
