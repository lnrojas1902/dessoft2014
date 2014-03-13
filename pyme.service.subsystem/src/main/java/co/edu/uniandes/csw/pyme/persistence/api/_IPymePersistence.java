
package co.edu.uniandes.csw.pyme.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;

public interface _IPymePersistence {

	public PymeDTO createPyme(PymeDTO detail);
	public List<PymeDTO> getPymes();
	public PymeDTO getPyme(Long id);
	public void deletePyme(Long id);
	public void updatePyme(PymeDTO detail);
	
}