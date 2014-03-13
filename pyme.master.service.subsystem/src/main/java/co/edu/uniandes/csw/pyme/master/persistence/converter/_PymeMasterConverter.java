package co.edu.uniandes.csw.pyme.master.persistence.converter;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeClienteEntity;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;
import co.edu.uniandes.csw.pyme.persistence.converter.PymeConverter;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _PymeMasterConverter {

    public static PymeMasterDTO entity2PersistenceDTO(PymeEntity pymeEntity 
    ,List<PymeClienteEntity> pymeClienteEntity 
    ,List<PymeFacturaEntity> pymeFacturaEntity 
    ) {
        PymeDTO pymeDTO = PymeConverter.entity2PersistenceDTO(pymeEntity);
        ArrayList<ClienteDTO> clienteEntities = new ArrayList<ClienteDTO>(pymeClienteEntity.size());
        for (PymeClienteEntity pymeCliente : pymeClienteEntity) {
            clienteEntities.add(ClienteConverter.entity2PersistenceDTO(pymeCliente.getClienteEntity()));
        }
        ArrayList<FacturaDTO> facturaEntities = new ArrayList<FacturaDTO>(pymeFacturaEntity.size());
        for (PymeFacturaEntity pymeFactura : pymeFacturaEntity) {
            facturaEntities.add(FacturaConverter.entity2PersistenceDTO(pymeFactura.getFacturaEntity()));
        }
        PymeMasterDTO respDTO  = new PymeMasterDTO();
        respDTO.setPymeEntity(pymeDTO);
        respDTO.setListCliente(clienteEntities);
        respDTO.setListFactura(facturaEntities);
        return respDTO;
    }

}