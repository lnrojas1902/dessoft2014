package co.edu.uniandes.csw.pyme.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.pyme.master.logic.api.IPymeMasterLogicService;
import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;

public abstract class _PymeMasterService {

    @Inject
    protected IPymeMasterLogicService pymeLogicService;

    @POST
    public PymeMasterDTO createPyme(PymeMasterDTO pyme) {
        return pymeLogicService.createMasterPyme(pyme);
    }

    @DELETE
    @Path("{id}")
    public void deletePyme(@PathParam("id") Long id) {
        pymeLogicService.deleteMasterPyme(id);
    }
    
    @GET
    @Path("{id}")
    public PymeMasterDTO getPyme(@PathParam("id") Long id) {
        return pymeLogicService.getMasterPyme(id);
    }

    @PUT
    @Path("{id}")
    public void updatePyme(@PathParam("id") Long id, PymeMasterDTO pyme) {
        pymeLogicService.updateMasterPyme(pyme);
    }

}
