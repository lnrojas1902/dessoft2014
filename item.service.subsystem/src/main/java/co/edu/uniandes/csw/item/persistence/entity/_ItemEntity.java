
package co.edu.uniandes.csw.item.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ItemEntity {

	@Id
	@GeneratedValue(generator = "Item")
	private Long id;
	private Long idProducto;
	private String name;
	private Integer cantidad;
	private String estado;
	private Double descuento;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getIdProducto(){
		return idProducto;
	}
	
	public void setIdProducto(Long idProducto){
		this.idProducto = idProducto;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Integer getCantidad(){
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad){
		this.cantidad = cantidad;
	}
	public String getEstado(){
		return estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
	public Double getDescuento(){
		return descuento;
	}
	
	public void setDescuento(Double descuento){
		this.descuento = descuento;
	}
}