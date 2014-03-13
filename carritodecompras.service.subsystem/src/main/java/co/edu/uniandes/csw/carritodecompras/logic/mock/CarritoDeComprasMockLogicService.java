
package co.edu.uniandes.csw.carritodecompras.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.carritodecompras.logic.api.ICarritoDeComprasLogicService;

@Alternative
@Singleton
public class CarritoDeComprasMockLogicService extends _CarritoDeComprasMockLogicService implements ICarritoDeComprasLogicService {
	
}