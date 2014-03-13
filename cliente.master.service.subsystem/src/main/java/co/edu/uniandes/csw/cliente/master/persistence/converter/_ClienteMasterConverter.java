package co.edu.uniandes.csw.cliente.master.persistence.converter;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _ClienteMasterConverter {

    public static ClienteMasterDTO entity2PersistenceDTO(ClienteEntity clienteEntity 
    ,List<ClienteFacturaEntity> clienteFacturaEntity 
    ) {
        ClienteDTO clienteDTO = ClienteConverter.entity2PersistenceDTO(clienteEntity);
        ArrayList<FacturaDTO> facturaEntities = new ArrayList<FacturaDTO>(clienteFacturaEntity.size());
        for (ClienteFacturaEntity clienteFactura : clienteFacturaEntity) {
            facturaEntities.add(FacturaConverter.entity2PersistenceDTO(clienteFactura.getFacturaEntity()));
        }
        ClienteMasterDTO respDTO  = new ClienteMasterDTO();
        respDTO.setClienteEntity(clienteDTO);
        respDTO.setListFactura(facturaEntities);
        return respDTO;
    }

}