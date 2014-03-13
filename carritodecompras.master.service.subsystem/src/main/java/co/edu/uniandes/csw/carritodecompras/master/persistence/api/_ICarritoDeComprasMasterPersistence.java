package co.edu.uniandes.csw.carritodecompras.master.persistence.api;

import co.edu.uniandes.csw.carritodecompras.master.persistence.entity.CarritoDeComprasItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.carritodecompras.master.logic.dto.CarritoDeComprasMasterDTO;
import java.util.List;

public interface _ICarritoDeComprasMasterPersistence {

    public CarritoDeComprasItemEntity createCarritoDeComprasItem(CarritoDeComprasItemEntity entity);

    public void deleteCarritoDeComprasItem(Long carritodecomprasId, Long itemId);
    
    public List<ItemDTO> getItemListForCarritoDeCompras(Long carritodecomprasId);
    
    public CarritoDeComprasMasterDTO getCarritoDeCompras(Long carritodecomprasId);

}
