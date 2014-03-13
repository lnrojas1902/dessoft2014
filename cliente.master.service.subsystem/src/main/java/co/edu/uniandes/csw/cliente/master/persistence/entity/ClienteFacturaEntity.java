package co.edu.uniandes.csw.cliente.master.persistence.entity;

import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(ClienteFacturaEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ClienteFacturaEntity.getFacturaListForCliente", query = "SELECT  u FROM ClienteFacturaEntity u WHERE u.clienteId=:clienteId"),
    @NamedQuery(name = "ClienteFacturaEntity.deleteClienteFactura", query = "DELETE FROM ClienteFacturaEntity u WHERE u.facturaId=:facturaId and  u.clienteId=:clienteId")
})
public class ClienteFacturaEntity implements Serializable {

    @Id
    @Column(name = "clienteId")
    private Long clienteId;
    @Id
    @Column(name = "facturaId")
    private Long facturaId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facturaId", referencedColumnName = "id")
    @JoinFetch
    private FacturaEntity facturaEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "clienteId", referencedColumnName = "id")
    @JoinFetch
    private ClienteEntity clienteEntity;

    public ClienteFacturaEntity() {
    }

    public ClienteFacturaEntity(Long clienteId, Long facturaId) {
        this.clienteId = clienteId;
        this.facturaId = facturaId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public FacturaEntity getFacturaEntity() {
        return facturaEntity;
    }

    public void setFacturaEntity(FacturaEntity facturaEntity) {
        this.facturaEntity = facturaEntity;
    }

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

}
