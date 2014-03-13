
package co.edu.uniandes.csw.pyme.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.pyme.logic.api.IPymeLogicService;

@Alternative
@Singleton
public class PymeMockLogicService extends _PymeMockLogicService implements IPymeLogicService {
	
}