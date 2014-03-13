
package co.edu.uniandes.csw.item.logic.ejb;

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


import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.logic.api.IItemLogicService;
import co.edu.uniandes.csw.item.persistence.ItemPersistence;
import co.edu.uniandes.csw.item.persistence.api.IItemPersistence;
import co.edu.uniandes.csw.item.persistence.entity.ItemEntity;

@RunWith(Arquillian.class)
public class ItemLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ItemLogicService.class.getPackage())
				.addPackage(ItemPersistence.class.getPackage())
				.addPackage(ItemEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IItemLogicService itemLogicService;
	
	@Inject
	private IItemPersistence itemPersistence;	

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
		List<ItemDTO> dtos=itemPersistence.getItems();
		for(ItemDTO dto:dtos){
			itemPersistence.deleteItem(dto.getId());
		}
	}

	private List<ItemDTO> data=new ArrayList<ItemDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ItemDTO pdto=new ItemDTO();
			pdto.setIdProducto(generateRandom(Long.class));
			pdto.setName(generateRandom(String.class));
			pdto.setCantidad(generateRandom(Integer.class));
			pdto.setEstado(generateRandom(String.class));
			pdto.setDescuento(generateRandom(Double.class));
			pdto=itemPersistence.createItem(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createItemTest(){
		ItemDTO ldto=new ItemDTO();
		ldto.setIdProducto(generateRandom(Long.class));
		ldto.setName(generateRandom(String.class));
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setEstado(generateRandom(String.class));
		ldto.setDescuento(generateRandom(Double.class));
		
		
		ItemDTO result=itemLogicService.createItem(ldto);
		
		Assert.assertNotNull(result);
		
		ItemDTO pdto=itemPersistence.getItem(result.getId());
		
		Assert.assertEquals(ldto.getIdProducto(), pdto.getIdProducto());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getCantidad(), pdto.getCantidad());	
		Assert.assertEquals(ldto.getEstado(), pdto.getEstado());	
		Assert.assertEquals(ldto.getDescuento(), pdto.getDescuento());	
	}
	
	@Test
	public void getItemsTest(){
		List<ItemDTO> list=itemLogicService.getItems();
		Assert.assertEquals(list.size(), data.size());
        for(ItemDTO ldto:list){
        	boolean found=false;
            for(ItemDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getItemTest(){
		ItemDTO pdto=data.get(0);
		ItemDTO ldto=itemLogicService.getItem(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getIdProducto(), ldto.getIdProducto());
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getCantidad(), ldto.getCantidad());
		Assert.assertEquals(pdto.getEstado(), ldto.getEstado());
		Assert.assertEquals(pdto.getDescuento(), ldto.getDescuento());
        
	}
	
	@Test
	public void deleteItemTest(){
		ItemDTO pdto=data.get(0);
		itemLogicService.deleteItem(pdto.getId());
        ItemDTO deleted=itemPersistence.getItem(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateItemTest(){
		ItemDTO pdto=data.get(0);
		
		ItemDTO ldto=new ItemDTO();
		ldto.setId(pdto.getId());
		ldto.setIdProducto(generateRandom(Long.class));
		ldto.setName(generateRandom(String.class));
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setEstado(generateRandom(String.class));
		ldto.setDescuento(generateRandom(Double.class));
		
		
		itemLogicService.updateItem(ldto);
		
		
		ItemDTO resp=itemPersistence.getItem(pdto.getId());
		
		Assert.assertEquals(ldto.getIdProducto(), resp.getIdProducto());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getCantidad(), resp.getCantidad());	
		Assert.assertEquals(ldto.getEstado(), resp.getEstado());	
		Assert.assertEquals(ldto.getDescuento(), resp.getDescuento());	
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