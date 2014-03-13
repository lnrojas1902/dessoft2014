
package co.edu.uniandes.csw.carritodecompras.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.persistence.entity.CarritoDeComprasEntity;

public abstract class _CarritoDeComprasConverter {


	public static CarritoDeComprasDTO entity2PersistenceDTO(CarritoDeComprasEntity entity){
		if (entity != null) {
			CarritoDeComprasDTO dto = new CarritoDeComprasDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
			return dto;
		}else{
			return null;
		}
	}
	
	public static CarritoDeComprasEntity persistenceDTO2Entity(CarritoDeComprasDTO dto){
		if(dto!=null){
			CarritoDeComprasEntity entity=new CarritoDeComprasEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<CarritoDeComprasDTO> entity2PersistenceDTOList(List<CarritoDeComprasEntity> entities){
		List<CarritoDeComprasDTO> dtos=new ArrayList<CarritoDeComprasDTO>();
		for(CarritoDeComprasEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<CarritoDeComprasEntity> persistenceDTO2EntityList(List<CarritoDeComprasDTO> dtos){
		List<CarritoDeComprasEntity> entities=new ArrayList<CarritoDeComprasEntity>();
		for(CarritoDeComprasDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}