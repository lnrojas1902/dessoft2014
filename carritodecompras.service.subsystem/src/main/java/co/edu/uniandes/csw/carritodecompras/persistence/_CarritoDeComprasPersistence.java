
package co.edu.uniandes.csw.carritodecompras.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.persistence.api._ICarritoDeComprasPersistence;
import co.edu.uniandes.csw.carritodecompras.persistence.converter.CarritoDeComprasConverter;
import co.edu.uniandes.csw.carritodecompras.persistence.entity.CarritoDeComprasEntity;

public abstract class _CarritoDeComprasPersistence implements _ICarritoDeComprasPersistence {

	@PersistenceContext(unitName="CarritoDeComprasPU")
	protected EntityManager entityManager;
	
	public CarritoDeComprasDTO createCarritoDeCompras(CarritoDeComprasDTO carritoDeCompras) {
		CarritoDeComprasEntity entity=CarritoDeComprasConverter.persistenceDTO2Entity(carritoDeCompras);
		entityManager.persist(entity);
		return CarritoDeComprasConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<CarritoDeComprasDTO> getCarritoDeComprass() {
		Query q = entityManager.createQuery("select u from CarritoDeComprasEntity u");
		return CarritoDeComprasConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public CarritoDeComprasDTO getCarritoDeCompras(Long id) {
		return CarritoDeComprasConverter.entity2PersistenceDTO(entityManager.find(CarritoDeComprasEntity.class, id));
	}

	public void deleteCarritoDeCompras(Long id) {
		CarritoDeComprasEntity entity=entityManager.find(CarritoDeComprasEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateCarritoDeCompras(CarritoDeComprasDTO detail) {
		CarritoDeComprasEntity entity=entityManager.merge(CarritoDeComprasConverter.persistenceDTO2Entity(detail));
		CarritoDeComprasConverter.entity2PersistenceDTO(entity);
	}

}