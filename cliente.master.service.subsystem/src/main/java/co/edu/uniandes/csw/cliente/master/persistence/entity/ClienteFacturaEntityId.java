package co.edu.uniandes.csw.cliente.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class ClienteFacturaEntityId implements Serializable{

    private Long clienteId;
    private Long facturaId;

    @Override
    public int hashCode() {
        return (int) (clienteId + facturaId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ClienteFacturaEntityId) {
            ClienteFacturaEntityId otherId = (ClienteFacturaEntityId) object;
            return (otherId.clienteId == this.clienteId) && (otherId.facturaId == this.facturaId);
        }
        return false;
    }

}
