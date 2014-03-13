
package co.edu.uniandes.csw.pyme.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.logic.api._IPymeLogicService;
import co.edu.uniandes.csw.pyme.persistence.api.IPymePersistence;

public abstract class _PymeLogicService implements _IPymeLogicService {

	@Inject
	protected IPymePersistence persistance;

	public PymeDTO createPyme(PymeDTO pyme){
		return persistance.createPyme( pyme); 
    }

	public List<PymeDTO> getPymes(){
		return persistance.getPymes(); 
	}

	public PymeDTO getPyme(Long id){
		return persistance.getPyme(id); 
	}

	public void deletePyme(Long id){
	    persistance.deletePyme(id); 
	}

	public void updatePyme(PymeDTO pyme){
	    persistance.updatePyme(pyme); 
	}	
}