
package co.edu.uniandes.csw.pyme.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _PymeEntity {

	private String name;
	private String descripcion;
	private Long idCatalogo;
	@Id
	@GeneratedValue(generator = "Pyme")
	private Long id;
	private Long clienteId;

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	public Long getIdCatalogo(){
		return idCatalogo;
	}
	
	public void setIdCatalogo(Long idCatalogo){
		this.idCatalogo = idCatalogo;
	}
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getClienteId(){
		return clienteId;
	}
	
	public void setClienteId(Long clienteId){
		this.clienteId = clienteId;
	}
}