
package co.edu.uniandes.csw.factura.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;

public abstract class _FacturaConverter {


	public static FacturaDTO entity2PersistenceDTO(FacturaEntity entity){
		if (entity != null) {
			FacturaDTO dto = new FacturaDTO();
				dto.setName(entity.getName());
				dto.setId(entity.getId());
				dto.setValor(entity.getValor());
				dto.setTipo(entity.getTipo());
				dto.setTipoDePago(entity.getTipoDePago());
				dto.setFechaDeRealizacion(entity.getFechaDeRealizacion());
				dto.setFechaEsperadaDeEntrega(entity.getFechaEsperadaDeEntrega());
				dto.setDireccionEntrega(entity.getDireccionEntrega());
			return dto;
		}else{
			return null;
		}
	}
	
	public static FacturaEntity persistenceDTO2Entity(FacturaDTO dto){
		if(dto!=null){
			FacturaEntity entity=new FacturaEntity();
			entity.setName(dto.getName());
			entity.setId(dto.getId());
			entity.setValor(dto.getValor());
			entity.setTipo(dto.getTipo());
			entity.setTipoDePago(dto.getTipoDePago());
			entity.setFechaDeRealizacion(dto.getFechaDeRealizacion());
			entity.setFechaEsperadaDeEntrega(dto.getFechaEsperadaDeEntrega());
			entity.setDireccionEntrega(dto.getDireccionEntrega());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<FacturaDTO> entity2PersistenceDTOList(List<FacturaEntity> entities){
		List<FacturaDTO> dtos=new ArrayList<FacturaDTO>();
		for(FacturaEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<FacturaEntity> persistenceDTO2EntityList(List<FacturaDTO> dtos){
		List<FacturaEntity> entities=new ArrayList<FacturaEntity>();
		for(FacturaDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}