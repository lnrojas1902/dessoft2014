package co.edu.uniandes.csw.cliente.master.logic.ejb;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.master.logic.api._IClienteMasterLogicService;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import co.edu.uniandes.csw.cliente.master.persistence.api.IClienteMasterPersistence;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteFacturaEntity;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import javax.inject.Inject;

public abstract class _ClienteMasterLogicService implements _IClienteMasterLogicService {

    @Inject
    protected IClientePersistence clientePersistance;
    @Inject
    protected IClienteMasterPersistence clienteMasterPersistance;
    @Inject
    protected IFacturaPersistence facturaPersistance;

    public ClienteMasterDTO createMasterCliente(ClienteMasterDTO cliente) {
        ClienteDTO persistedClienteDTO = clientePersistance.createCliente(cliente.getClienteEntity());
        if (cliente.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : cliente.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                ClienteFacturaEntity clienteFacturaEntity = new ClienteFacturaEntity(persistedClienteDTO.getId(), persistedFacturaDTO.getId());
                clienteMasterPersistance.createClienteFactura(clienteFacturaEntity);
            }
        }
        return cliente;
    }

    public ClienteMasterDTO getMasterCliente(Long id) {
        return clienteMasterPersistance.getCliente(id);
    }

    public void deleteMasterCliente(Long id) {
        clientePersistance.deleteCliente(id);
    }

    public void updateMasterCliente(ClienteMasterDTO cliente) {
        clientePersistance.updateCliente(cliente.getClienteEntity());

        //---- FOR RELATIONSHIP
        // persist new factura
        if (cliente.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : cliente.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                ClienteFacturaEntity clienteFacturaEntity = new ClienteFacturaEntity(cliente.getClienteEntity().getId(), persistedFacturaDTO.getId());
                clienteMasterPersistance.createClienteFactura(clienteFacturaEntity);
            }
        }
        // update factura
        if (cliente.getUpdateFactura() != null) {
            for (FacturaDTO facturaDTO : cliente.getUpdateFactura()) {
                facturaPersistance.updateFactura(facturaDTO);
            }
        }
        // delete factura
        if (cliente.getDeleteFactura() != null) {
            for (FacturaDTO facturaDTO : cliente.getDeleteFactura()) {
                clienteMasterPersistance.deleteClienteFactura(cliente.getClienteEntity().getId(), facturaDTO.getId());
                facturaPersistance.deleteFactura(facturaDTO.getId());
            }
        }
    }
}
