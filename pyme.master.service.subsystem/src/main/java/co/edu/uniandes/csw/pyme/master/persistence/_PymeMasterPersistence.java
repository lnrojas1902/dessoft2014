package co.edu.uniandes.csw.pyme.master.persistence;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeClienteEntity;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeFacturaEntity;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;
import co.edu.uniandes.csw.pyme.master.persistence.api._IPymeMasterPersistence;
import co.edu.uniandes.csw.pyme.persistence.api.IPymePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _PymeMasterPersistence implements _IPymeMasterPersistence {

    @PersistenceContext(unitName = "PymeMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IPymePersistence pymePersistence;  

    public PymeMasterDTO getPyme(Long pymeId) {
        PymeMasterDTO pymeMasterDTO = new PymeMasterDTO();
        PymeDTO pyme = pymePersistence.getPyme(pymeId);
        pymeMasterDTO.setPymeEntity(pyme);
        pymeMasterDTO.setListCliente(getClienteListForPyme(pymeId));
        pymeMasterDTO.setListFactura(getFacturaListForPyme(pymeId));
        return pymeMasterDTO;
    }

    public PymeClienteEntity createPymeCliente(PymeClienteEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deletePymeCliente(Long pymeId, Long clienteId) {
        Query q = entityManager.createNamedQuery("PymeClienteEntity.deletePymeCliente");
        q.setParameter("pymeId", pymeId);
        q.setParameter("clienteId", clienteId);
        q.executeUpdate();
    }

    public List<ClienteDTO> getClienteListForPyme(Long pymeId) {
        ArrayList<ClienteDTO> resp = new ArrayList<ClienteDTO>();
        Query q = entityManager.createNamedQuery("PymeClienteEntity.getClienteListForPyme");
        q.setParameter("pymeId", pymeId);
        List<PymeClienteEntity> qResult =  q.getResultList();
        for (PymeClienteEntity pymeClienteEntity : qResult) { 
            if(pymeClienteEntity.getClienteEntity()==null){
                entityManager.refresh(pymeClienteEntity);
            }
            resp.add(ClienteConverter.entity2PersistenceDTO(pymeClienteEntity.getClienteEntity()));
        }
        return resp;
    }
    public PymeFacturaEntity createPymeFactura(PymeFacturaEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deletePymeFactura(Long pymeId, Long facturaId) {
        Query q = entityManager.createNamedQuery("PymeFacturaEntity.deletePymeFactura");
        q.setParameter("pymeId", pymeId);
        q.setParameter("facturaId", facturaId);
        q.executeUpdate();
    }

    public List<FacturaDTO> getFacturaListForPyme(Long pymeId) {
        ArrayList<FacturaDTO> resp = new ArrayList<FacturaDTO>();
        Query q = entityManager.createNamedQuery("PymeFacturaEntity.getFacturaListForPyme");
        q.setParameter("pymeId", pymeId);
        List<PymeFacturaEntity> qResult =  q.getResultList();
        for (PymeFacturaEntity pymeFacturaEntity : qResult) { 
            if(pymeFacturaEntity.getFacturaEntity()==null){
                entityManager.refresh(pymeFacturaEntity);
            }
            resp.add(FacturaConverter.entity2PersistenceDTO(pymeFacturaEntity.getFacturaEntity()));
        }
        return resp;
    }

}
