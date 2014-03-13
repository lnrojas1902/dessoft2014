
package co.edu.uniandes.csw.cliente.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.persistence.api._IClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;

public abstract class _ClientePersistence implements _IClientePersistence {

	@PersistenceContext(unitName="ClientePU")
	protected EntityManager entityManager;
	
	public ClienteDTO createCliente(ClienteDTO cliente) {
		ClienteEntity entity=ClienteConverter.persistenceDTO2Entity(cliente);
		entityManager.persist(entity);
		return ClienteConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ClienteDTO> getClientes() {
		Query q = entityManager.createQuery("select u from ClienteEntity u");
		return ClienteConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ClienteDTO getCliente(Long id) {
		return ClienteConverter.entity2PersistenceDTO(entityManager.find(ClienteEntity.class, id));
	}

	public void deleteCliente(Long id) {
		ClienteEntity entity=entityManager.find(ClienteEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateCliente(ClienteDTO detail) {
		ClienteEntity entity=entityManager.merge(ClienteConverter.persistenceDTO2Entity(detail));
		ClienteConverter.entity2PersistenceDTO(entity);
	}

}