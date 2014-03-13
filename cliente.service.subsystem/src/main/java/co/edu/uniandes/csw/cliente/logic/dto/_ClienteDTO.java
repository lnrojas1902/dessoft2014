
package co.edu.uniandes.csw.cliente.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _ClienteDTO {

	private String name;
	private Long id;
	private String docIdentidad;
	private String tipo;
	private String password;
	private String login;

	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocIdentidad() {
		return docIdentidad;
	}
 
	public void setDocIdentidad(String docidentidad) {
		this.docIdentidad = docidentidad;
	}
	public String getTipo() {
		return tipo;
	}
 
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
 
	public void setLogin(String login) {
		this.login = login;
	}
	
}