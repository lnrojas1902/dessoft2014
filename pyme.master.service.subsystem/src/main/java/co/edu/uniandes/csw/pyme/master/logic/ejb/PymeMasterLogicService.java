package co.edu.uniandes.csw.pyme.master.logic.ejb;

import co.edu.uniandes.csw.pyme.master.logic.api.IPymeMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class PymeMasterLogicService extends _PymeMasterLogicService implements IPymeMasterLogicService {

}