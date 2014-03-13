
package co.edu.uniandes.csw.cliente.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.cliente.logic.api.IClienteLogicService;

@Default
@Stateless
@LocalBean
public class ClienteLogicService extends _ClienteLogicService implements IClienteLogicService {

}