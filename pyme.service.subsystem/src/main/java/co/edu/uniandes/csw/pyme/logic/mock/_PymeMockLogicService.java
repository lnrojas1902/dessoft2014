
package co.edu.uniandes.csw.pyme.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.logic.api._IPymeLogicService;

public abstract class _PymeMockLogicService implements _IPymeLogicService {

	private Long id= new Long(1);
	protected List<PymeDTO> data=new ArrayList<PymeDTO>();

	public PymeDTO createPyme(PymeDTO pyme){
		id++;
		pyme.setId(id);
		return pyme;
    }

	public List<PymeDTO> getPymes(){
		return data; 
	}

	public PymeDTO getPyme(Long id){
		for(PymeDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deletePyme(Long id){
	    PymeDTO delete=null;
		for(PymeDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updatePyme(PymeDTO pyme){
	    PymeDTO delete=null;
		for(PymeDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(pyme);
		} 
	}	
}