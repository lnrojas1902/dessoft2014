package co.edu.uniandes.csw.cliente.master.logic.ejb;

import co.edu.uniandes.csw.cliente.master.logic.api.IClienteMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class ClienteMasterLogicService extends _ClienteMasterLogicService implements IClienteMasterLogicService {

}