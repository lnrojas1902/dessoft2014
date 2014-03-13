package co.edu.uniandes.csw.cliente.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.cliente.logic.api.IClienteLogicService;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;


public abstract class _ClienteService {

	@Inject
	protected IClienteLogicService clienteLogicService;
	
	@POST
	public ClienteDTO createCliente(ClienteDTO cliente){
		return clienteLogicService.createCliente(cliente);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteCliente(@PathParam("id") Long id){
		clienteLogicService.deleteCliente(id);
	}
	
	@GET
	public List<ClienteDTO> getClientes(){
		return clienteLogicService.getClientes();
	}
	
	@GET
	@Path("{id}")
	public ClienteDTO getCliente(@PathParam("id") Long id){
		return clienteLogicService.getCliente(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateCliente(@PathParam("id") Long id, ClienteDTO cliente){
		clienteLogicService.updateCliente(cliente);
	}
	
}