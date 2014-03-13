
package co.edu.uniandes.csw.cliente.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ClientePersistence extends _ClientePersistence  implements IClientePersistence {

}