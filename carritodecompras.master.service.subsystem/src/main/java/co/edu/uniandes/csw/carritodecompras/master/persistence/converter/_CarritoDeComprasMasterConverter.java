package co.edu.uniandes.csw.carritodecompras.master.persistence.converter;
import co.edu.uniandes.csw.carritodecompras.master.persistence.entity.CarritoDeComprasItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.master.logic.dto.CarritoDeComprasMasterDTO;
import co.edu.uniandes.csw.carritodecompras.persistence.converter.CarritoDeComprasConverter;
import co.edu.uniandes.csw.carritodecompras.persistence.entity.CarritoDeComprasEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _CarritoDeComprasMasterConverter {

    public static CarritoDeComprasMasterDTO entity2PersistenceDTO(CarritoDeComprasEntity carritodecomprasEntity 
    ,List<CarritoDeComprasItemEntity> carritodecomprasItemEntity 
    ) {
        CarritoDeComprasDTO carritodecomprasDTO = CarritoDeComprasConverter.entity2PersistenceDTO(carritodecomprasEntity);
        ArrayList<ItemDTO> itemEntities = new ArrayList<ItemDTO>(carritodecomprasItemEntity.size());
        for (CarritoDeComprasItemEntity carritodecomprasItem : carritodecomprasItemEntity) {
            itemEntities.add(ItemConverter.entity2PersistenceDTO(carritodecomprasItem.getItemEntity()));
        }
        CarritoDeComprasMasterDTO respDTO  = new CarritoDeComprasMasterDTO();
        respDTO.setCarritoDeComprasEntity(carritodecomprasDTO);
        respDTO.setListItem(itemEntities);
        return respDTO;
    }

}