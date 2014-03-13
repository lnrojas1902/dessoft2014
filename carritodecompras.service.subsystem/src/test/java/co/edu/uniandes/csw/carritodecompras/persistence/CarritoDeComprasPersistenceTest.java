
package co.edu.uniandes.csw.carritodecompras.persistence;

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
import co.edu.uniandes.csw.carritodecompras.persistence.api.ICarritoDeComprasPersistence;
import co.edu.uniandes.csw.carritodecompras.persistence.entity.CarritoDeComprasEntity;

@RunWith(Arquillian.class)
public class CarritoDeComprasPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(CarritoDeComprasPersistence.class.getPackage())
				.addPackage(CarritoDeComprasEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ICarritoDeComprasPersistence carritoDeComprasPersistence;

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
		em.createQuery("delete from CarritoDeComprasEntity").executeUpdate();
	}

	private List<CarritoDeComprasEntity> data=new ArrayList<CarritoDeComprasEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			CarritoDeComprasEntity entity=new CarritoDeComprasEntity();
			entity.setName(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createCarritoDeComprasTest(){
		CarritoDeComprasDTO dto=new CarritoDeComprasDTO();
		dto.setName(generateRandom(String.class));
		
		
		CarritoDeComprasDTO result=carritoDeComprasPersistence.createCarritoDeCompras(dto);
		
		Assert.assertNotNull(result);
		
		CarritoDeComprasEntity entity=em.find(CarritoDeComprasEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
	}
	
	@Test
	public void getCarritoDeComprassTest(){
		List<CarritoDeComprasDTO> list=carritoDeComprasPersistence.getCarritoDeComprass();
		Assert.assertEquals(list.size(), data.size());
        for(CarritoDeComprasDTO dto:list){
        	boolean found=false;
            for(CarritoDeComprasEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getCarritoDeComprasTest(){
		CarritoDeComprasEntity entity=data.get(0);
		CarritoDeComprasDTO dto=carritoDeComprasPersistence.getCarritoDeCompras(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
        
	}
	
	@Test
	public void deleteCarritoDeComprasTest(){
		CarritoDeComprasEntity entity=data.get(0);
		carritoDeComprasPersistence.deleteCarritoDeCompras(entity.getId());
        CarritoDeComprasEntity deleted=em.find(CarritoDeComprasEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateCarritoDeComprasTest(){
		CarritoDeComprasEntity entity=data.get(0);
		
		CarritoDeComprasDTO dto=new CarritoDeComprasDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		
		
		carritoDeComprasPersistence.updateCarritoDeCompras(dto);
		
		
		CarritoDeComprasEntity resp=em.find(CarritoDeComprasEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
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