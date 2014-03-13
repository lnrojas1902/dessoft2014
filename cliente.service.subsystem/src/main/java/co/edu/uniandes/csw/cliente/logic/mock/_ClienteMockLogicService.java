
package co.edu.uniandes.csw.cliente.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.logic.api._IClienteLogicService;

public abstract class _ClienteMockLogicService implements _IClienteLogicService {

	private Long id= new Long(1);
	protected List<ClienteDTO> data=new ArrayList<ClienteDTO>();

	public ClienteDTO createCliente(ClienteDTO cliente){
		id++;
		cliente.setId(id);
		return cliente;
    }

	public List<ClienteDTO> getClientes(){
		return data; 
	}

	public ClienteDTO getCliente(Long id){
		for(ClienteDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteCliente(Long id){
	    ClienteDTO delete=null;
		for(ClienteDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateCliente(ClienteDTO cliente){
	    ClienteDTO delete=null;
		for(ClienteDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(cliente);
		} 
	}	
}