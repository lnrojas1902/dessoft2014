
package co.edu.uniandes.csw.pyme.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.pyme.persistence.api.IPymePersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class PymePersistence extends _PymePersistence  implements IPymePersistence {

}