package co.edu.uniandes.csw.cliente.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.cliente.master.persistence.api.IClienteMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class ClienteMasterPersistence extends _ClienteMasterPersistence  implements IClienteMasterPersistence {

}