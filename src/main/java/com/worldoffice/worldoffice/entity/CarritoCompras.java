package com.worldoffice.worldoffice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author Johann Agamez
 */
@Entity
@Table(name = "carritos")
public class CarritoCompras implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@JoinColumn(name = "idproducto", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Productos idproducto;
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	@Column(name = "estado", length = 10, nullable = false)
	private String estado;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaingreso")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date fechaingreso;

	public CarritoCompras() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Productos getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Productos idproducto) {
		this.idproducto = idproducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean validaCamposVaciosInsert() {
			return !(idproducto == null || cantidad == 0);
	}

}