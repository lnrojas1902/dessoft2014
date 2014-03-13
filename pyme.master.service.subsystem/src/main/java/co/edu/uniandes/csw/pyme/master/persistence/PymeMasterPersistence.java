package co.edu.uniandes.csw.pyme.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.pyme.master.persistence.api.IPymeMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class PymeMasterPersistence extends _PymeMasterPersistence  implements IPymeMasterPersistence {

}