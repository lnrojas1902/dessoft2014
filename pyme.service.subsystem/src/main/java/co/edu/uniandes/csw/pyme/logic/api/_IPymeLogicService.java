
package co.edu.uniandes.csw.pyme.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;

public interface _IPymeLogicService {

	public PymeDTO createPyme(PymeDTO detail);
	public List<PymeDTO> getPymes();
	public PymeDTO getPyme(Long id);
	public void deletePyme(Long id);
	public void updatePyme(PymeDTO detail);
	
}