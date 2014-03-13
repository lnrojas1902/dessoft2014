package co.edu.uniandes.csw.carritodecompras.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.carritodecompras.logic.api.ICarritoDeComprasLogicService;
import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;


public abstract class _CarritoDeComprasService {

	@Inject
	protected ICarritoDeComprasLogicService carritoDeComprasLogicService;
	
	@POST
	public CarritoDeComprasDTO createCarritoDeCompras(CarritoDeComprasDTO carritoDeCompras){
		return carritoDeComprasLogicService.createCarritoDeCompras(carritoDeCompras);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteCarritoDeCompras(@PathParam("id") Long id){
		carritoDeComprasLogicService.deleteCarritoDeCompras(id);
	}
	
	@GET
	public List<CarritoDeComprasDTO> getCarritoDeComprass(){
		return carritoDeComprasLogicService.getCarritoDeComprass();
	}
	
	@GET
	@Path("{id}")
	public CarritoDeComprasDTO getCarritoDeCompras(@PathParam("id") Long id){
		return carritoDeComprasLogicService.getCarritoDeCompras(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateCarritoDeCompras(@PathParam("id") Long id, CarritoDeComprasDTO carritoDeCompras){
		carritoDeComprasLogicService.updateCarritoDeCompras(carritoDeCompras);
	}
	
}