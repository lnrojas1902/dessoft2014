
package co.edu.uniandes.csw.carritodecompras.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.carritodecompras.persistence.api.ICarritoDeComprasPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class CarritoDeComprasPersistence extends _CarritoDeComprasPersistence  implements ICarritoDeComprasPersistence {

}