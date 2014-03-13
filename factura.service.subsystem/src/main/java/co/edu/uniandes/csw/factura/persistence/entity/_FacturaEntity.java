
package co.edu.uniandes.csw.factura.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _FacturaEntity {

	private String name;
	@Id
	@GeneratedValue(generator = "Factura")
	private Long id;
	private Integer valor;
	private String tipo;
	private String tipoDePago;
	@Temporal(TemporalType.DATE)
	private Date fechaDeRealizacion;
	@Temporal(TemporalType.DATE)
	private Date fechaEsperadaDeEntrega;
	private String direccionEntrega;

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Integer getValor(){
		return valor;
	}
	
	public void setValor(Integer valor){
		this.valor = valor;
	}
	public String getTipo(){
		return tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	public String getTipoDePago(){
		return tipoDePago;
	}
	
	public void setTipoDePago(String tipoDePago){
		this.tipoDePago = tipoDePago;
	}
	public Date getFechaDeRealizacion(){
		return fechaDeRealizacion;
	}
	
	public void setFechaDeRealizacion(Date fechaDeRealizacion){
		this.fechaDeRealizacion = fechaDeRealizacion;
	}
	public Date getFechaEsperadaDeEntrega(){
		return fechaEsperadaDeEntrega;
	}
	
	public void setFechaEsperadaDeEntrega(Date fechaEsperadaDeEntrega){
		this.fechaEsperadaDeEntrega = fechaEsperadaDeEntrega;
	}
	public String getDireccionEntrega(){
		return direccionEntrega;
	}
	
	public void setDireccionEntrega(String direccionEntrega){
		this.direccionEntrega = direccionEntrega;
	}
}