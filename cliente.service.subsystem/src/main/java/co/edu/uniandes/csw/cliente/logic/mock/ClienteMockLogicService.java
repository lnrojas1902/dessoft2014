
package co.edu.uniandes.csw.cliente.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.cliente.logic.api.IClienteLogicService;

@Alternative
@Singleton
public class ClienteMockLogicService extends _ClienteMockLogicService implements IClienteLogicService {
	
}