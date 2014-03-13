
package co.edu.uniandes.csw.pyme.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _PymeDTO {

	private String name;
	private String descripcion;
	private Long idCatalogo;
	private Long id;
	private Long clienteId;

	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public String getDescripcion() {
		return descripcion;
	}
 
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getIdCatalogo() {
		return idCatalogo;
	}
 
	public void setIdCatalogo(Long idcatalogo) {
		this.idCatalogo = idcatalogo;
	}
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClienteId() {
		return clienteId;
	}
 
	public void setClienteId(Long clienteid) {
		this.clienteId = clienteid;
	}
	
}