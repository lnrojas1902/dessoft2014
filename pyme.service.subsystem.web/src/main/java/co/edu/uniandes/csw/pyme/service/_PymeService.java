package co.edu.uniandes.csw.pyme.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.pyme.logic.api.IPymeLogicService;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;


public abstract class _PymeService {

	@Inject
	protected IPymeLogicService pymeLogicService;
	
	@POST
	public PymeDTO createPyme(PymeDTO pyme){
		return pymeLogicService.createPyme(pyme);
	}
	
	@DELETE
	@Path("{id}")
	public void deletePyme(@PathParam("id") Long id){
		pymeLogicService.deletePyme(id);
	}
	
	@GET
	public List<PymeDTO> getPymes(){
		return pymeLogicService.getPymes();
	}
	
	@GET
	@Path("{id}")
	public PymeDTO getPyme(@PathParam("id") Long id){
		return pymeLogicService.getPyme(id);
	}
	
	@PUT
    @Path("{id}")
	public void updatePyme(@PathParam("id") Long id, PymeDTO pyme){
		pymeLogicService.updatePyme(pyme);
	}
	
}