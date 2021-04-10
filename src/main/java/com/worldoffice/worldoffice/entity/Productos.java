package com.worldoffice.worldoffice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author Johann Agamez
 */
@Entity
@Table(name = "productos")
public class Productos implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "nombre", length = 500, nullable = false)
	private String nombre;
	@Column(name = "marca", length = 200, nullable = false)
	private String marca;
	@Column(name = "precio", nullable = false)
	private BigDecimal precio;
	@Column(name = "stock", nullable = false)
	private int stock;
	@Column(name = "estado", length = 5, nullable = false)
	private String estado;
	@Column(name = "descuento", nullable = false)
	private BigDecimal descuento;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaingreso")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date fechaingreso;
	@OneToMany(mappedBy = "idproducto", fetch = FetchType.EAGER)
    private List<CarritoCompras> carritoCompras;

	public Productos() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Productos)) {
			return false;
		}
		Productos other = (Productos) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	public boolean validaCamposVaciosInsert() {
		return !(nombre == null || marca == null || fechaingreso == null || precio == null || stock > 0 || estado == null || descuento == null);
	}

	public boolean validaCamposVaciosUpdate() {
		return !(id == null || nombre == null || marca == null || fechaingreso == null || precio == null || stock >= 0 || estado == null || descuento == null);
	}

}