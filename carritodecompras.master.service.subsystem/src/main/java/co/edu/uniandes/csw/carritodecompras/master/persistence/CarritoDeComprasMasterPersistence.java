package co.edu.uniandes.csw.carritodecompras.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.carritodecompras.master.persistence.api.ICarritoDeComprasMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class CarritoDeComprasMasterPersistence extends _CarritoDeComprasMasterPersistence  implements ICarritoDeComprasMasterPersistence {

}