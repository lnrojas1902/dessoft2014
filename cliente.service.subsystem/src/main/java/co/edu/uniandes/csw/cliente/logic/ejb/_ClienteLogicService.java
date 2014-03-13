
package co.edu.uniandes.csw.cliente.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.logic.api._IClienteLogicService;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;

public abstract class _ClienteLogicService implements _IClienteLogicService {

	@Inject
	protected IClientePersistence persistance;

	public ClienteDTO createCliente(ClienteDTO cliente){
		return persistance.createCliente( cliente); 
    }

	public List<ClienteDTO> getClientes(){
		return persistance.getClientes(); 
	}

	public ClienteDTO getCliente(Long id){
		return persistance.getCliente(id); 
	}

	public void deleteCliente(Long id){
	    persistance.deleteCliente(id); 
	}

	public void updateCliente(ClienteDTO cliente){
	    persistance.updateCliente(cliente); 
	}	
}