package co.edu.uniandes.csw.carritodecompras.master.logic.ejb;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.api.IItemPersistence;
import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.master.logic.api._ICarritoDeComprasMasterLogicService;
import co.edu.uniandes.csw.carritodecompras.master.logic.dto.CarritoDeComprasMasterDTO;
import co.edu.uniandes.csw.carritodecompras.master.persistence.api.ICarritoDeComprasMasterPersistence;
import co.edu.uniandes.csw.carritodecompras.master.persistence.entity.CarritoDeComprasItemEntity;
import co.edu.uniandes.csw.carritodecompras.persistence.api.ICarritoDeComprasPersistence;
import javax.inject.Inject;

public abstract class _CarritoDeComprasMasterLogicService implements _ICarritoDeComprasMasterLogicService {

    @Inject
    protected ICarritoDeComprasPersistence carritodecomprasPersistance;
    @Inject
    protected ICarritoDeComprasMasterPersistence carritodecomprasMasterPersistance;
    @Inject
    protected IItemPersistence itemPersistance;

    public CarritoDeComprasMasterDTO createMasterCarritoDeCompras(CarritoDeComprasMasterDTO carritodecompras) {
        CarritoDeComprasDTO persistedCarritoDeComprasDTO = carritodecomprasPersistance.createCarritoDeCompras(carritodecompras.getCarritoDeComprasEntity());
        if (carritodecompras.getCreateItem() != null) {
            for (ItemDTO itemDTO : carritodecompras.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                CarritoDeComprasItemEntity carritodecomprasItemEntity = new CarritoDeComprasItemEntity(persistedCarritoDeComprasDTO.getId(), persistedItemDTO.getId());
                carritodecomprasMasterPersistance.createCarritoDeComprasItem(carritodecomprasItemEntity);
            }
        }
        return carritodecompras;
    }

    public CarritoDeComprasMasterDTO getMasterCarritoDeCompras(Long id) {
        return carritodecomprasMasterPersistance.getCarritoDeCompras(id);
    }

    public void deleteMasterCarritoDeCompras(Long id) {
        carritodecomprasPersistance.deleteCarritoDeCompras(id);
    }

    public void updateMasterCarritoDeCompras(CarritoDeComprasMasterDTO carritodecompras) {
        carritodecomprasPersistance.updateCarritoDeCompras(carritodecompras.getCarritoDeComprasEntity());

        //---- FOR RELATIONSHIP
        // persist new item
        if (carritodecompras.getCreateItem() != null) {
            for (ItemDTO itemDTO : carritodecompras.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                CarritoDeComprasItemEntity carritodecomprasItemEntity = new CarritoDeComprasItemEntity(carritodecompras.getCarritoDeComprasEntity().getId(), persistedItemDTO.getId());
                carritodecomprasMasterPersistance.createCarritoDeComprasItem(carritodecomprasItemEntity);
            }
        }
        // update item
        if (carritodecompras.getUpdateItem() != null) {
            for (ItemDTO itemDTO : carritodecompras.getUpdateItem()) {
                itemPersistance.updateItem(itemDTO);
            }
        }
        // delete item
        if (carritodecompras.getDeleteItem() != null) {
            for (ItemDTO itemDTO : carritodecompras.getDeleteItem()) {
                carritodecomprasMasterPersistance.deleteCarritoDeComprasItem(carritodecompras.getCarritoDeComprasEntity().getId(), itemDTO.getId());
                itemPersistance.deleteItem(itemDTO.getId());
            }
        }
    }
}
