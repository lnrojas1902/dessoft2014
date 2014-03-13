package co.edu.uniandes.csw.carritodecompras.master.logic.ejb;

import co.edu.uniandes.csw.carritodecompras.master.logic.api.ICarritoDeComprasMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class CarritoDeComprasMasterLogicService extends _CarritoDeComprasMasterLogicService implements ICarritoDeComprasMasterLogicService {

}