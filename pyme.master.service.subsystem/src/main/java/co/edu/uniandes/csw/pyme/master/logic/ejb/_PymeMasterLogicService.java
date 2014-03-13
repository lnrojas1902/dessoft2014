package co.edu.uniandes.csw.pyme.master.logic.ejb;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.master.logic.api._IPymeMasterLogicService;
import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;
import co.edu.uniandes.csw.pyme.master.persistence.api.IPymeMasterPersistence;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeClienteEntity;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeFacturaEntity;
import co.edu.uniandes.csw.pyme.persistence.api.IPymePersistence;
import javax.inject.Inject;

public abstract class _PymeMasterLogicService implements _IPymeMasterLogicService {

    @Inject
    protected IPymePersistence pymePersistance;
    @Inject
    protected IPymeMasterPersistence pymeMasterPersistance;
    @Inject
    protected IClientePersistence clientePersistance;
    @Inject
    protected IFacturaPersistence facturaPersistance;

    public PymeMasterDTO createMasterPyme(PymeMasterDTO pyme) {
        PymeDTO persistedPymeDTO = pymePersistance.createPyme(pyme.getPymeEntity());
        if (pyme.getCreateCliente() != null) {
            for (ClienteDTO clienteDTO : pyme.getCreateCliente()) {
                ClienteDTO persistedClienteDTO = clientePersistance.createCliente(clienteDTO);
                PymeClienteEntity pymeClienteEntity = new PymeClienteEntity(persistedPymeDTO.getId(), persistedClienteDTO.getId());
                pymeMasterPersistance.createPymeCliente(pymeClienteEntity);
            }
        }
        if (pyme.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : pyme.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                PymeFacturaEntity pymeFacturaEntity = new PymeFacturaEntity(persistedPymeDTO.getId(), persistedFacturaDTO.getId());
                pymeMasterPersistance.createPymeFactura(pymeFacturaEntity);
            }
        }
        return pyme;
    }

    public PymeMasterDTO getMasterPyme(Long id) {
        return pymeMasterPersistance.getPyme(id);
    }

    public void deleteMasterPyme(Long id) {
        pymePersistance.deletePyme(id);
    }

    public void updateMasterPyme(PymeMasterDTO pyme) {
        pymePersistance.updatePyme(pyme.getPymeEntity());

        //---- FOR RELATIONSHIP
        // persist new cliente
        if (pyme.getCreateCliente() != null) {
            for (ClienteDTO clienteDTO : pyme.getCreateCliente()) {
                ClienteDTO persistedClienteDTO = clientePersistance.createCliente(clienteDTO);
                PymeClienteEntity pymeClienteEntity = new PymeClienteEntity(pyme.getPymeEntity().getId(), persistedClienteDTO.getId());
                pymeMasterPersistance.createPymeCliente(pymeClienteEntity);
            }
        }
        // update cliente
        if (pyme.getUpdateCliente() != null) {
            for (ClienteDTO clienteDTO : pyme.getUpdateCliente()) {
                clientePersistance.updateCliente(clienteDTO);
            }
        }
        // delete cliente
        if (pyme.getDeleteCliente() != null) {
            for (ClienteDTO clienteDTO : pyme.getDeleteCliente()) {
                pymeMasterPersistance.deletePymeCliente(pyme.getPymeEntity().getId(), clienteDTO.getId());
                clientePersistance.deleteCliente(clienteDTO.getId());
            }
        }
        // persist new factura
        if (pyme.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : pyme.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                PymeFacturaEntity pymeFacturaEntity = new PymeFacturaEntity(pyme.getPymeEntity().getId(), persistedFacturaDTO.getId());
                pymeMasterPersistance.createPymeFactura(pymeFacturaEntity);
            }
        }
        // update factura
        if (pyme.getUpdateFactura() != null) {
            for (FacturaDTO facturaDTO : pyme.getUpdateFactura()) {
                facturaPersistance.updateFactura(facturaDTO);
            }
        }
        // delete factura
        if (pyme.getDeleteFactura() != null) {
            for (FacturaDTO facturaDTO : pyme.getDeleteFactura()) {
                pymeMasterPersistance.deletePymeFactura(pyme.getPymeEntity().getId(), facturaDTO.getId());
                facturaPersistance.deleteFactura(facturaDTO.getId());
            }
        }
    }
}
