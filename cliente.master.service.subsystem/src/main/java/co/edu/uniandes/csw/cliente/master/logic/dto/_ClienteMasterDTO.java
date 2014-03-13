package co.edu.uniandes.csw.cliente.master.logic.dto;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _ClienteMasterDTO {

 
    protected ClienteDTO clienteEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ClienteDTO getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteDTO clienteEntity) {
        this.clienteEntity = clienteEntity;
    }
    
    public List<FacturaDTO> createFactura;
    public List<FacturaDTO> updateFactura;
    public List<FacturaDTO> deleteFactura;
    public List<FacturaDTO> listFactura;	
	
	
	
    public List<FacturaDTO> getCreateFactura(){ return createFactura; };
    public void setCreateFactura(List<FacturaDTO> createFactura){ this.createFactura=createFactura; };
    public List<FacturaDTO> getUpdateFactura(){ return updateFactura; };
    public void setUpdateFactura(List<FacturaDTO> updateFactura){ this.updateFactura=updateFactura; };
    public List<FacturaDTO> getDeleteFactura(){ return deleteFactura; };
    public void setDeleteFactura(List<FacturaDTO> deleteFactura){ this.deleteFactura=deleteFactura; };
    public List<FacturaDTO> getListFactura(){ return listFactura; };
    public void setListFactura(List<FacturaDTO> listFactura){ this.listFactura=listFactura; };	
	
	
}

