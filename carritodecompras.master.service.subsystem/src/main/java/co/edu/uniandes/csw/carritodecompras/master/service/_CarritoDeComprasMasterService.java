package co.edu.uniandes.csw.carritodecompras.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.carritodecompras.master.logic.api.ICarritoDeComprasMasterLogicService;
import co.edu.uniandes.csw.carritodecompras.master.logic.dto.CarritoDeComprasMasterDTO;

public abstract class _CarritoDeComprasMasterService {

    @Inject
    protected ICarritoDeComprasMasterLogicService carritodecomprasLogicService;

    @POST
    public CarritoDeComprasMasterDTO createCarritoDeCompras(CarritoDeComprasMasterDTO carritodecompras) {
        return carritodecomprasLogicService.createMasterCarritoDeCompras(carritodecompras);
    }

    @DELETE
    @Path("{id}")
    public void deleteCarritoDeCompras(@PathParam("id") Long id) {
        carritodecomprasLogicService.deleteMasterCarritoDeCompras(id);
    }
    
    @GET
    @Path("{id}")
    public CarritoDeComprasMasterDTO getCarritoDeCompras(@PathParam("id") Long id) {
        return carritodecomprasLogicService.getMasterCarritoDeCompras(id);
    }

    @PUT
    @Path("{id}")
    public void updateCarritoDeCompras(@PathParam("id") Long id, CarritoDeComprasMasterDTO carritodecompras) {
        carritodecomprasLogicService.updateMasterCarritoDeCompras(carritodecompras);
    }

}
