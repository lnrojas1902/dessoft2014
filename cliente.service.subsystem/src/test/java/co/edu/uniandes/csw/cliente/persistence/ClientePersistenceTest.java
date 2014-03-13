
package co.edu.uniandes.csw.cliente.persistence;

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
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;

@RunWith(Arquillian.class)
public class ClientePersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ClientePersistence.class.getPackage())
				.addPackage(ClienteEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IClientePersistence clientePersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from ClienteEntity").executeUpdate();
	}

	private List<ClienteEntity> data=new ArrayList<ClienteEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ClienteEntity entity=new ClienteEntity();
			entity.setName(generateRandom(String.class));
			entity.setDocIdentidad(generateRandom(String.class));
			entity.setTipo(generateRandom(String.class));
			entity.setPassword(generateRandom(String.class));
			entity.setLogin(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createClienteTest(){
		ClienteDTO dto=new ClienteDTO();
		dto.setName(generateRandom(String.class));
		dto.setDocIdentidad(generateRandom(String.class));
		dto.setTipo(generateRandom(String.class));
		dto.setPassword(generateRandom(String.class));
		dto.setLogin(generateRandom(String.class));
		
		
		ClienteDTO result=clientePersistence.createCliente(dto);
		
		Assert.assertNotNull(result);
		
		ClienteEntity entity=em.find(ClienteEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getDocIdentidad(), entity.getDocIdentidad());	
		Assert.assertEquals(dto.getTipo(), entity.getTipo());	
		Assert.assertEquals(dto.getPassword(), entity.getPassword());	
		Assert.assertEquals(dto.getLogin(), entity.getLogin());	
	}
	
	@Test
	public void getClientesTest(){
		List<ClienteDTO> list=clientePersistence.getClientes();
		Assert.assertEquals(list.size(), data.size());
        for(ClienteDTO dto:list){
        	boolean found=false;
            for(ClienteEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getClienteTest(){
		ClienteEntity entity=data.get(0);
		ClienteDTO dto=clientePersistence.getCliente(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getDocIdentidad(), dto.getDocIdentidad());
		Assert.assertEquals(entity.getTipo(), dto.getTipo());
		Assert.assertEquals(entity.getPassword(), dto.getPassword());
		Assert.assertEquals(entity.getLogin(), dto.getLogin());
        
	}
	
	@Test
	public void deleteClienteTest(){
		ClienteEntity entity=data.get(0);
		clientePersistence.deleteCliente(entity.getId());
        ClienteEntity deleted=em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateClienteTest(){
		ClienteEntity entity=data.get(0);
		
		ClienteDTO dto=new ClienteDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setDocIdentidad(generateRandom(String.class));
		dto.setTipo(generateRandom(String.class));
		dto.setPassword(generateRandom(String.class));
		dto.setLogin(generateRandom(String.class));
		
		
		clientePersistence.updateCliente(dto);
		
		
		ClienteEntity resp=em.find(ClienteEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getDocIdentidad(), resp.getDocIdentidad());	
		Assert.assertEquals(dto.getTipo(), resp.getTipo());	
		Assert.assertEquals(dto.getPassword(), resp.getPassword());	
		Assert.assertEquals(dto.getLogin(), resp.getLogin());	
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