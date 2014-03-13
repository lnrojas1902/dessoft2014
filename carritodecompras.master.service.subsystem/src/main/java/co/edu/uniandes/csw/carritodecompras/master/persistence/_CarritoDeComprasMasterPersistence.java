package co.edu.uniandes.csw.carritodecompras.master.persistence;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.carritodecompras.master.persistence.entity.CarritoDeComprasItemEntity;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.master.logic.dto.CarritoDeComprasMasterDTO;
import co.edu.uniandes.csw.carritodecompras.master.persistence.api._ICarritoDeComprasMasterPersistence;
import co.edu.uniandes.csw.carritodecompras.persistence.api.ICarritoDeComprasPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _CarritoDeComprasMasterPersistence implements _ICarritoDeComprasMasterPersistence {

    @PersistenceContext(unitName = "CarritoDeComprasMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected ICarritoDeComprasPersistence carritodecomprasPersistence;  

    public CarritoDeComprasMasterDTO getCarritoDeCompras(Long carritodecomprasId) {
        CarritoDeComprasMasterDTO carritodecomprasMasterDTO = new CarritoDeComprasMasterDTO();
        CarritoDeComprasDTO carritodecompras = carritodecomprasPersistence.getCarritoDeCompras(carritodecomprasId);
        carritodecomprasMasterDTO.setCarritoDeComprasEntity(carritodecompras);
        carritodecomprasMasterDTO.setListItem(getItemListForCarritoDeCompras(carritodecomprasId));
        return carritodecomprasMasterDTO;
    }

    public CarritoDeComprasItemEntity createCarritoDeComprasItem(CarritoDeComprasItemEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteCarritoDeComprasItem(Long carritodecomprasId, Long itemId) {
        Query q = entityManager.createNamedQuery("CarritoDeComprasItemEntity.deleteCarritoDeComprasItem");
        q.setParameter("carritodecomprasId", carritodecomprasId);
        q.setParameter("itemId", itemId);
        q.executeUpdate();
    }

    public List<ItemDTO> getItemListForCarritoDeCompras(Long carritodecomprasId) {
        ArrayList<ItemDTO> resp = new ArrayList<ItemDTO>();
        Query q = entityManager.createNamedQuery("CarritoDeComprasItemEntity.getItemListForCarritoDeCompras");
        q.setParameter("carritodecomprasId", carritodecomprasId);
        List<CarritoDeComprasItemEntity> qResult =  q.getResultList();
        for (CarritoDeComprasItemEntity carritodecomprasItemEntity : qResult) { 
            if(carritodecomprasItemEntity.getItemEntity()==null){
                entityManager.refresh(carritodecomprasItemEntity);
            }
            resp.add(ItemConverter.entity2PersistenceDTO(carritodecomprasItemEntity.getItemEntity()));
        }
        return resp;
    }

}
