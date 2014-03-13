package co.edu.uniandes.csw.cliente.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.cliente.master.logic.api.IClienteMasterLogicService;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;

public abstract class _ClienteMasterService {

    @Inject
    protected IClienteMasterLogicService clienteLogicService;

    @POST
    public ClienteMasterDTO createCliente(ClienteMasterDTO cliente) {
        return clienteLogicService.createMasterCliente(cliente);
    }

    @DELETE
    @Path("{id}")
    public void deleteCliente(@PathParam("id") Long id) {
        clienteLogicService.deleteMasterCliente(id);
    }
    
    @GET
    @Path("{id}")
    public ClienteMasterDTO getCliente(@PathParam("id") Long id) {
        return clienteLogicService.getMasterCliente(id);
    }

    @PUT
    @Path("{id}")
    public void updateCliente(@PathParam("id") Long id, ClienteMasterDTO cliente) {
        clienteLogicService.updateMasterCliente(cliente);
    }

}
