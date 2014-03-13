package co.edu.uniandes.csw.pyme.master.persistence.entity;

import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;
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
@IdClass(PymeFacturaEntityId.class)
@NamedQueries({
    @NamedQuery(name = "PymeFacturaEntity.getFacturaListForPyme", query = "SELECT  u FROM PymeFacturaEntity u WHERE u.pymeId=:pymeId"),
    @NamedQuery(name = "PymeFacturaEntity.deletePymeFactura", query = "DELETE FROM PymeFacturaEntity u WHERE u.facturaId=:facturaId and  u.pymeId=:pymeId")
})
public class PymeFacturaEntity implements Serializable {

    @Id
    @Column(name = "pymeId")
    private Long pymeId;
    @Id
    @Column(name = "facturaId")
    private Long facturaId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facturaId", referencedColumnName = "id")
    @JoinFetch
    private FacturaEntity facturaEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "pymeId", referencedColumnName = "id")
    @JoinFetch
    private PymeEntity pymeEntity;

    public PymeFacturaEntity() {
    }

    public PymeFacturaEntity(Long pymeId, Long facturaId) {
        this.pymeId = pymeId;
        this.facturaId = facturaId;
    }

    public Long getPymeId() {
        return pymeId;
    }

    public void setPymeId(Long pymeId) {
        this.pymeId = pymeId;
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

    public PymeEntity getPymeEntity() {
        return pymeEntity;
    }

    public void setPymeEntity(PymeEntity pymeEntity) {
        this.pymeEntity = pymeEntity;
    }

}
