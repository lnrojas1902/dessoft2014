package co.edu.uniandes.csw.carritodecompras.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class CarritoDeComprasItemEntityId implements Serializable{

    private Long carritodecomprasId;
    private Long itemId;

    @Override
    public int hashCode() {
        return (int) (carritodecomprasId + itemId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CarritoDeComprasItemEntityId) {
            CarritoDeComprasItemEntityId otherId = (CarritoDeComprasItemEntityId) object;
            return (otherId.carritodecomprasId == this.carritodecomprasId) && (otherId.itemId == this.itemId);
        }
        return false;
    }

}
