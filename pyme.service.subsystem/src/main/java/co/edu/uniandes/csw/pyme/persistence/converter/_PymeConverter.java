
package co.edu.uniandes.csw.pyme.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;

public abstract class _PymeConverter {


	public static PymeDTO entity2PersistenceDTO(PymeEntity entity){
		if (entity != null) {
			PymeDTO dto = new PymeDTO();
				dto.setName(entity.getName());
				dto.setDescripcion(entity.getDescripcion());
				dto.setIdCatalogo(entity.getIdCatalogo());
				dto.setId(entity.getId());
				dto.setClienteId(entity.getClienteId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static PymeEntity persistenceDTO2Entity(PymeDTO dto){
		if(dto!=null){
			PymeEntity entity=new PymeEntity();
			entity.setName(dto.getName());
			entity.setDescripcion(dto.getDescripcion());
			entity.setIdCatalogo(dto.getIdCatalogo());
			entity.setId(dto.getId());
			entity.setClienteId(dto.getClienteId());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<PymeDTO> entity2PersistenceDTOList(List<PymeEntity> entities){
		List<PymeDTO> dtos=new ArrayList<PymeDTO>();
		for(PymeEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<PymeEntity> persistenceDTO2EntityList(List<PymeDTO> dtos){
		List<PymeEntity> entities=new ArrayList<PymeEntity>();
		for(PymeDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}