package co.edu.uniandes.csw.cliente.master.persistence.api;

import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import java.util.List;

public interface _IClienteMasterPersistence {

    public ClienteFacturaEntity createClienteFactura(ClienteFacturaEntity entity);

    public void deleteClienteFactura(Long clienteId, Long facturaId);
    
    public List<FacturaDTO> getFacturaListForCliente(Long clienteId);
    
    public ClienteMasterDTO getCliente(Long clienteId);

}
