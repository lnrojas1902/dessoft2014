
package co.edu.uniandes.csw.factura.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _FacturaDTO {

	private String name;
	private Long id;
	private Integer valor;
	private String tipo;
	private String tipoDePago;
	private Date fechaDeRealizacion;
	private Date fechaEsperadaDeEntrega;
	private String direccionEntrega;

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
	public Integer getValor() {
		return valor;
	}
 
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
 
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipoDePago() {
		return tipoDePago;
	}
 
	public void setTipoDePago(String tipodepago) {
		this.tipoDePago = tipodepago;
	}
	public Date getFechaDeRealizacion() {
		return fechaDeRealizacion;
	}
 
	public void setFechaDeRealizacion(Date fechaderealizacion) {
		this.fechaDeRealizacion = fechaderealizacion;
	}
	public Date getFechaEsperadaDeEntrega() {
		return fechaEsperadaDeEntrega;
	}
 
	public void setFechaEsperadaDeEntrega(Date fechaesperadadeentrega) {
		this.fechaEsperadaDeEntrega = fechaesperadadeentrega;
	}
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
 
	public void setDireccionEntrega(String direccionentrega) {
		this.direccionEntrega = direccionentrega;
	}
	
}