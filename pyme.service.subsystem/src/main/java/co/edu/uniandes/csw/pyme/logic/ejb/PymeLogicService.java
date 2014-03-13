
package co.edu.uniandes.csw.pyme.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.pyme.logic.api.IPymeLogicService;

@Default
@Stateless
@LocalBean
public class PymeLogicService extends _PymeLogicService implements IPymeLogicService {

}