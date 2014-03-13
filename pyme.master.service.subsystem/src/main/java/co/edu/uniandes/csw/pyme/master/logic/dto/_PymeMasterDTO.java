package co.edu.uniandes.csw.pyme.master.logic.dto;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _PymeMasterDTO {

 
    protected PymeDTO pymeEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public PymeDTO getPymeEntity() {
        return pymeEntity;
    }

    public void setPymeEntity(PymeDTO pymeEntity) {
        this.pymeEntity = pymeEntity;
    }
    
    public List<ClienteDTO> createCliente;
    public List<ClienteDTO> updateCliente;
    public List<ClienteDTO> deleteCliente;
    public List<ClienteDTO> listCliente;	
    public List<FacturaDTO> createFactura;
    public List<FacturaDTO> updateFactura;
    public List<FacturaDTO> deleteFactura;
    public List<FacturaDTO> listFactura;	
	
	
	
    public List<ClienteDTO> getCreateCliente(){ return createCliente; };
    public void setCreateCliente(List<ClienteDTO> createCliente){ this.createCliente=createCliente; };
    public List<ClienteDTO> getUpdateCliente(){ return updateCliente; };
    public void setUpdateCliente(List<ClienteDTO> updateCliente){ this.updateCliente=updateCliente; };
    public List<ClienteDTO> getDeleteCliente(){ return deleteCliente; };
    public void setDeleteCliente(List<ClienteDTO> deleteCliente){ this.deleteCliente=deleteCliente; };
    public List<ClienteDTO> getListCliente(){ return listCliente; };
    public void setListCliente(List<ClienteDTO> listCliente){ this.listCliente=listCliente; };	
    public List<FacturaDTO> getCreateFactura(){ return createFactura; };
    public void setCreateFactura(List<FacturaDTO> createFactura){ this.createFactura=createFactura; };
    public List<FacturaDTO> getUpdateFactura(){ return updateFactura; };
    public void setUpdateFactura(List<FacturaDTO> updateFactura){ this.updateFactura=updateFactura; };
    public List<FacturaDTO> getDeleteFactura(){ return deleteFactura; };
    public void setDeleteFactura(List<FacturaDTO> deleteFactura){ this.deleteFactura=deleteFactura; };
    public List<FacturaDTO> getListFactura(){ return listFactura; };
    public void setListFactura(List<FacturaDTO> listFactura){ this.listFactura=listFactura; };	
	
	
}

