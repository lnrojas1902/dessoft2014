package co.edu.uniandes.csw.carritodecompras.master.persistence.entity;

import co.edu.uniandes.csw.item.persistence.entity.ItemEntity;
import co.edu.uniandes.csw.carritodecompras.persistence.entity.CarritoDeComprasEntity;
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
@IdClass(CarritoDeComprasItemEntityId.class)
@NamedQueries({
    @NamedQuery(name = "CarritoDeComprasItemEntity.getItemListForCarritoDeCompras", query = "SELECT  u FROM CarritoDeComprasItemEntity u WHERE u.carritodecomprasId=:carritodecomprasId"),
    @NamedQuery(name = "CarritoDeComprasItemEntity.deleteCarritoDeComprasItem", query = "DELETE FROM CarritoDeComprasItemEntity u WHERE u.itemId=:itemId and  u.carritodecomprasId=:carritodecomprasId")
})
public class CarritoDeComprasItemEntity implements Serializable {

    @Id
    @Column(name = "carritodecomprasId")
    private Long carritodecomprasId;
    @Id
    @Column(name = "itemId")
    private Long itemId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "itemId", referencedColumnName = "id")
    @JoinFetch
    private ItemEntity itemEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "carritodecomprasId", referencedColumnName = "id")
    @JoinFetch
    private CarritoDeComprasEntity carritodecomprasEntity;

    public CarritoDeComprasItemEntity() {
    }

    public CarritoDeComprasItemEntity(Long carritodecomprasId, Long itemId) {
        this.carritodecomprasId = carritodecomprasId;
        this.itemId = itemId;
    }

    public Long getCarritoDeComprasId() {
        return carritodecomprasId;
    }

    public void setCarritoDeComprasId(Long carritodecomprasId) {
        this.carritodecomprasId = carritodecomprasId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public CarritoDeComprasEntity getCarritoDeComprasEntity() {
        return carritodecomprasEntity;
    }

    public void setCarritoDeComprasEntity(CarritoDeComprasEntity carritodecomprasEntity) {
        this.carritodecomprasEntity = carritodecomprasEntity;
    }

}
