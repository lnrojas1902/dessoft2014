 
package co.edu.uniandes.csw.pyme.master.logic.api;

import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;

public interface _IPymeMasterLogicService {

	public PymeMasterDTO createMasterPyme(PymeMasterDTO detail);
    public void updateMasterPyme(PymeMasterDTO detail);
	public void deleteMasterPyme(Long id); 
	public PymeMasterDTO getMasterPyme(Long id);
        
}