package co.edu.uniandes.csw.pyme.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class PymeClienteEntityId implements Serializable{

    private Long pymeId;
    private Long clienteId;

    @Override
    public int hashCode() {
        return (int) (pymeId + clienteId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof PymeClienteEntityId) {
            PymeClienteEntityId otherId = (PymeClienteEntityId) object;
            return (otherId.pymeId == this.pymeId) && (otherId.clienteId == this.clienteId);
        }
        return false;
    }

}
