
package co.edu.uniandes.csw.carritodecompras.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.carritodecompras.logic.api.ICarritoDeComprasLogicService;

@Default
@Stateless
@LocalBean
public class CarritoDeComprasLogicService extends _CarritoDeComprasLogicService implements ICarritoDeComprasLogicService {

}