
package co.edu.uniandes.csw.cliente.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.logic.api.IClienteLogicService;
import co.edu.uniandes.csw.cliente.persistence.ClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;

@RunWith(Arquillian.class)
public class ClienteLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ClienteLogicService.class.getPackage())
				.addPackage(ClientePersistence.class.getPackage())
				.addPackage(ClienteEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IClienteLogicService clienteLogicService;
	
	@Inject
	private IClientePersistence clientePersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<ClienteDTO> dtos=clientePersistence.getClientes();
		for(ClienteDTO dto:dtos){
			clientePersistence.deleteCliente(dto.getId());
		}
	}

	private List<ClienteDTO> data=new ArrayList<ClienteDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ClienteDTO pdto=new ClienteDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setDocIdentidad(generateRandom(String.class));
			pdto.setTipo(generateRandom(String.class));
			pdto.setPassword(generateRandom(String.class));
			pdto.setLogin(generateRandom(String.class));
			pdto=clientePersistence.createCliente(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createClienteTest(){
		ClienteDTO ldto=new ClienteDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setDocIdentidad(generateRandom(String.class));
		ldto.setTipo(generateRandom(String.class));
		ldto.setPassword(generateRandom(String.class));
		ldto.setLogin(generateRandom(String.class));
		
		
		ClienteDTO result=clienteLogicService.createCliente(ldto);
		
		Assert.assertNotNull(result);
		
		ClienteDTO pdto=clientePersistence.getCliente(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getDocIdentidad(), pdto.getDocIdentidad());	
		Assert.assertEquals(ldto.getTipo(), pdto.getTipo());	
		Assert.assertEquals(ldto.getPassword(), pdto.getPassword());	
		Assert.assertEquals(ldto.getLogin(), pdto.getLogin());	
	}
	
	@Test
	public void getClientesTest(){
		List<ClienteDTO> list=clienteLogicService.getClientes();
		Assert.assertEquals(list.size(), data.size());
        for(ClienteDTO ldto:list){
        	boolean found=false;
            for(ClienteDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getClienteTest(){
		ClienteDTO pdto=data.get(0);
		ClienteDTO ldto=clienteLogicService.getCliente(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getDocIdentidad(), ldto.getDocIdentidad());
		Assert.assertEquals(pdto.getTipo(), ldto.getTipo());
		Assert.assertEquals(pdto.getPassword(), ldto.getPassword());
		Assert.assertEquals(pdto.getLogin(), ldto.getLogin());
        
	}
	
	@Test
	public void deleteClienteTest(){
		ClienteDTO pdto=data.get(0);
		clienteLogicService.deleteCliente(pdto.getId());
        ClienteDTO deleted=clientePersistence.getCliente(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateClienteTest(){
		ClienteDTO pdto=data.get(0);
		
		ClienteDTO ldto=new ClienteDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setDocIdentidad(generateRandom(String.class));
		ldto.setTipo(generateRandom(String.class));
		ldto.setPassword(generateRandom(String.class));
		ldto.setLogin(generateRandom(String.class));
		
		
		clienteLogicService.updateCliente(ldto);
		
		
		ClienteDTO resp=clientePersistence.getCliente(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getDocIdentidad(), resp.getDocIdentidad());	
		Assert.assertEquals(ldto.getTipo(), resp.getTipo());	
		Assert.assertEquals(ldto.getPassword(), resp.getPassword());	
		Assert.assertEquals(ldto.getLogin(), resp.getLogin());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}