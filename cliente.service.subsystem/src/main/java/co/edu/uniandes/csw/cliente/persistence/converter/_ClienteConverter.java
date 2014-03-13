
package co.edu.uniandes.csw.cliente.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;

public abstract class _ClienteConverter {


	public static ClienteDTO entity2PersistenceDTO(ClienteEntity entity){
		if (entity != null) {
			ClienteDTO dto = new ClienteDTO();
				dto.setName(entity.getName());
				dto.setId(entity.getId());
				dto.setDocIdentidad(entity.getDocIdentidad());
				dto.setTipo(entity.getTipo());
				dto.setPassword(entity.getPassword());
				dto.setLogin(entity.getLogin());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ClienteEntity persistenceDTO2Entity(ClienteDTO dto){
		if(dto!=null){
			ClienteEntity entity=new ClienteEntity();
			entity.setName(dto.getName());
			entity.setId(dto.getId());
			entity.setDocIdentidad(dto.getDocIdentidad());
			entity.setTipo(dto.getTipo());
			entity.setPassword(dto.getPassword());
			entity.setLogin(dto.getLogin());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<ClienteDTO> entity2PersistenceDTOList(List<ClienteEntity> entities){
		List<ClienteDTO> dtos=new ArrayList<ClienteDTO>();
		for(ClienteEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ClienteEntity> persistenceDTO2EntityList(List<ClienteDTO> dtos){
		List<ClienteEntity> entities=new ArrayList<ClienteEntity>();
		for(ClienteDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}