package org.stackoverflow.dml;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testhibernate.dmlupdates.Cart;
import org.testhibernate.dmlupdates.CartItem;
import org.testhibernate.dmlupdates.PersistentUnitLoader;
import org.testhibernate.dmlupdates.Product;


public class TestUpdateDML {

	@BeforeClass
	public static void initEntityManagerFactory() {
		PersistentUnitLoader.createEntityManager();
	}
	
	@Before
	public void initData() {
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();
		entityManager.getTransaction().begin();
		
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Test
	public void testUpdate1() {
		
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("UPDATE CartItem c SET c.removedDate = :currentDateTime "
                + " WHERE c.id IN (Select Cart.items.id From Cart"
                + " WHERE Cart.id = :cartId"
                + " AND Cart.items.pro.id = :pro"
                + " AND Cart.items.removedDate is null)");
		query.setParameter("currentDateTime", Calendar.getInstance().getTime());
		query.setParameter("cartId", 1);
		query.setParameter("pro", 1);
	
		int result = query.executeUpdate();
		
		assertEquals("Should be 1", 1, result);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Test
	public void testUpdate2() {
		
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();
		entityManager.getTransaction().begin();
		
		insertFakeData(entityManager);
		entityManager.getTransaction().commit();
		
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("UPDATE CartItem c SET c.removedDate = :currentDateTime "
                + " WHERE c.cart.id = :cartId" 
                + " AND c.pro.id = :pro"
                + " AND c.removedDate is null");
		query.setParameter("currentDateTime", Calendar.getInstance().getTime());
		long cartId = 1;
		long proId = 1;
		query.setParameter("cartId", cartId);
		query.setParameter("pro", proId);
	
		int result = query.executeUpdate();
		
		assertEquals("Should be 1", 1, result);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@AfterClass
	public static void destroy() {
		PersistentUnitLoader.closeEntityManager();
	}

	
	public void insertFakeData(EntityManager pEntityManager) {
		Cart c = new Cart();
		c.setItems(new ArrayList<CartItem>());
		CartItem item = new CartItem();
		item.setId(1);
		//item.setRemovedDate(Calendar.getInstance().getTime());
		item.setCart(c);
		c.getItems().add(item);
		c.setId(new Long(1));
		Product prod = new Product();
		prod.setId(1);
		prod.setName("PRODUCT 1");
		item.setPro(prod);
		pEntityManager.persist(c);
		
	}
}
