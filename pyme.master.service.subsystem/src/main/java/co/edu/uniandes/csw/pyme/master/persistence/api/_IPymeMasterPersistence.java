package co.edu.uniandes.csw.pyme.master.persistence.api;

import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeClienteEntity;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;
import java.util.List;

public interface _IPymeMasterPersistence {

    public PymeClienteEntity createPymeCliente(PymeClienteEntity entity);

    public void deletePymeCliente(Long pymeId, Long clienteId);
    
    public List<ClienteDTO> getClienteListForPyme(Long pymeId);
    public PymeFacturaEntity createPymeFactura(PymeFacturaEntity entity);

    public void deletePymeFactura(Long pymeId, Long facturaId);
    
    public List<FacturaDTO> getFacturaListForPyme(Long pymeId);
    
    public PymeMasterDTO getPyme(Long pymeId);

}
