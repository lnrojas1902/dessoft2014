
package co.edu.uniandes.csw.carritodecompras.logic.ejb;

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


import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.logic.api.ICarritoDeComprasLogicService;
import co.edu.uniandes.csw.carritodecompras.persistence.CarritoDeComprasPersistence;
import co.edu.uniandes.csw.carritodecompras.persistence.api.ICarritoDeComprasPersistence;
import co.edu.uniandes.csw.carritodecompras.persistence.entity.CarritoDeComprasEntity;

@RunWith(Arquillian.class)
public class CarritoDeComprasLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(CarritoDeComprasLogicService.class.getPackage())
				.addPackage(CarritoDeComprasPersistence.class.getPackage())
				.addPackage(CarritoDeComprasEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ICarritoDeComprasLogicService carritoDeComprasLogicService;
	
	@Inject
	private ICarritoDeComprasPersistence carritoDeComprasPersistence;	

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
		List<CarritoDeComprasDTO> dtos=carritoDeComprasPersistence.getCarritoDeComprass();
		for(CarritoDeComprasDTO dto:dtos){
			carritoDeComprasPersistence.deleteCarritoDeCompras(dto.getId());
		}
	}

	private List<CarritoDeComprasDTO> data=new ArrayList<CarritoDeComprasDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			CarritoDeComprasDTO pdto=new CarritoDeComprasDTO();
			pdto.setName(generateRandom(String.class));
			pdto=carritoDeComprasPersistence.createCarritoDeCompras(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createCarritoDeComprasTest(){
		CarritoDeComprasDTO ldto=new CarritoDeComprasDTO();
		ldto.setName(generateRandom(String.class));
		
		
		CarritoDeComprasDTO result=carritoDeComprasLogicService.createCarritoDeCompras(ldto);
		
		Assert.assertNotNull(result);
		
		CarritoDeComprasDTO pdto=carritoDeComprasPersistence.getCarritoDeCompras(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
	}
	
	@Test
	public void getCarritoDeComprassTest(){
		List<CarritoDeComprasDTO> list=carritoDeComprasLogicService.getCarritoDeComprass();
		Assert.assertEquals(list.size(), data.size());
        for(CarritoDeComprasDTO ldto:list){
        	boolean found=false;
            for(CarritoDeComprasDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getCarritoDeComprasTest(){
		CarritoDeComprasDTO pdto=data.get(0);
		CarritoDeComprasDTO ldto=carritoDeComprasLogicService.getCarritoDeCompras(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
        
	}
	
	@Test
	public void deleteCarritoDeComprasTest(){
		CarritoDeComprasDTO pdto=data.get(0);
		carritoDeComprasLogicService.deleteCarritoDeCompras(pdto.getId());
        CarritoDeComprasDTO deleted=carritoDeComprasPersistence.getCarritoDeCompras(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateCarritoDeComprasTest(){
		CarritoDeComprasDTO pdto=data.get(0);
		
		CarritoDeComprasDTO ldto=new CarritoDeComprasDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		
		
		carritoDeComprasLogicService.updateCarritoDeCompras(ldto);
		
		
		CarritoDeComprasDTO resp=carritoDeComprasPersistence.getCarritoDeCompras(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
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