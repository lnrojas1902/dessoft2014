
package co.edu.uniandes.csw.pyme.logic.ejb;

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


import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.logic.api.IPymeLogicService;
import co.edu.uniandes.csw.pyme.persistence.PymePersistence;
import co.edu.uniandes.csw.pyme.persistence.api.IPymePersistence;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;

@RunWith(Arquillian.class)
public class PymeLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(PymeLogicService.class.getPackage())
				.addPackage(PymePersistence.class.getPackage())
				.addPackage(PymeEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IPymeLogicService pymeLogicService;
	
	@Inject
	private IPymePersistence pymePersistence;	

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
		List<PymeDTO> dtos=pymePersistence.getPymes();
		for(PymeDTO dto:dtos){
			pymePersistence.deletePyme(dto.getId());
		}
	}

	private List<PymeDTO> data=new ArrayList<PymeDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			PymeDTO pdto=new PymeDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setDescripcion(generateRandom(String.class));
			pdto.setIdCatalogo(generateRandom(Long.class));
			pdto.setClienteId(generateRandom(Long.class));
			pdto=pymePersistence.createPyme(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createPymeTest(){
		PymeDTO ldto=new PymeDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setDescripcion(generateRandom(String.class));
		ldto.setIdCatalogo(generateRandom(Long.class));
		ldto.setClienteId(generateRandom(Long.class));
		
		
		PymeDTO result=pymeLogicService.createPyme(ldto);
		
		Assert.assertNotNull(result);
		
		PymeDTO pdto=pymePersistence.getPyme(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getDescripcion(), pdto.getDescripcion());	
		Assert.assertEquals(ldto.getIdCatalogo(), pdto.getIdCatalogo());	
		Assert.assertEquals(ldto.getClienteId(), pdto.getClienteId());	
	}
	
	@Test
	public void getPymesTest(){
		List<PymeDTO> list=pymeLogicService.getPymes();
		Assert.assertEquals(list.size(), data.size());
        for(PymeDTO ldto:list){
        	boolean found=false;
            for(PymeDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getPymeTest(){
		PymeDTO pdto=data.get(0);
		PymeDTO ldto=pymeLogicService.getPyme(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getDescripcion(), ldto.getDescripcion());
		Assert.assertEquals(pdto.getIdCatalogo(), ldto.getIdCatalogo());
		Assert.assertEquals(pdto.getClienteId(), ldto.getClienteId());
        
	}
	
	@Test
	public void deletePymeTest(){
		PymeDTO pdto=data.get(0);
		pymeLogicService.deletePyme(pdto.getId());
        PymeDTO deleted=pymePersistence.getPyme(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updatePymeTest(){
		PymeDTO pdto=data.get(0);
		
		PymeDTO ldto=new PymeDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setDescripcion(generateRandom(String.class));
		ldto.setIdCatalogo(generateRandom(Long.class));
		ldto.setClienteId(generateRandom(Long.class));
		
		
		pymeLogicService.updatePyme(ldto);
		
		
		PymeDTO resp=pymePersistence.getPyme(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getDescripcion(), resp.getDescripcion());	
		Assert.assertEquals(ldto.getIdCatalogo(), resp.getIdCatalogo());	
		Assert.assertEquals(ldto.getClienteId(), resp.getClienteId());	
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