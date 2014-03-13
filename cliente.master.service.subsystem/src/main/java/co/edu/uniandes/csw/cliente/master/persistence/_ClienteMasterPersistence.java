package co.edu.uniandes.csw.cliente.master.persistence;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteFacturaEntity;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import co.edu.uniandes.csw.cliente.master.persistence.api._IClienteMasterPersistence;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _ClienteMasterPersistence implements _IClienteMasterPersistence {

    @PersistenceContext(unitName = "ClienteMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IClientePersistence clientePersistence;  

    public ClienteMasterDTO getCliente(Long clienteId) {
        ClienteMasterDTO clienteMasterDTO = new ClienteMasterDTO();
        ClienteDTO cliente = clientePersistence.getCliente(clienteId);
        clienteMasterDTO.setClienteEntity(cliente);
        clienteMasterDTO.setListFactura(getFacturaListForCliente(clienteId));
        return clienteMasterDTO;
    }

    public ClienteFacturaEntity createClienteFactura(ClienteFacturaEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteClienteFactura(Long clienteId, Long facturaId) {
        Query q = entityManager.createNamedQuery("ClienteFacturaEntity.deleteClienteFactura");
        q.setParameter("clienteId", clienteId);
        q.setParameter("facturaId", facturaId);
        q.executeUpdate();
    }

    public List<FacturaDTO> getFacturaListForCliente(Long clienteId) {
        ArrayList<FacturaDTO> resp = new ArrayList<FacturaDTO>();
        Query q = entityManager.createNamedQuery("ClienteFacturaEntity.getFacturaListForCliente");
        q.setParameter("clienteId", clienteId);
        List<ClienteFacturaEntity> qResult =  q.getResultList();
        for (ClienteFacturaEntity clienteFacturaEntity : qResult) { 
            if(clienteFacturaEntity.getFacturaEntity()==null){
                entityManager.refresh(clienteFacturaEntity);
            }
            resp.add(FacturaConverter.entity2PersistenceDTO(clienteFacturaEntity.getFacturaEntity()));
        }
        return resp;
    }

}
