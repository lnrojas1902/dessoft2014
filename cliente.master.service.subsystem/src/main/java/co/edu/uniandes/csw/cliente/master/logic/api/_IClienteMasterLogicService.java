 
package co.edu.uniandes.csw.cliente.master.logic.api;

import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;

public interface _IClienteMasterLogicService {

	public ClienteMasterDTO createMasterCliente(ClienteMasterDTO detail);
    public void updateMasterCliente(ClienteMasterDTO detail);
	public void deleteMasterCliente(Long id); 
	public ClienteMasterDTO getMasterCliente(Long id);
        
}