package org.testhibernate.customid;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testhibernate.customid.MyObject;
import org.testhibernate.dmlupdates.PersistentUnitLoader;

public class TestCustomIdGenerator {

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
	
	@AfterClass
	public static void destroy() {
		PersistentUnitLoader.closeEntityManager();
	}
	
	@Test
	public void test() {
		
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();
		entityManager.getTransaction().begin();
		
		MyObject obj = new MyObject();
		obj.setField1(45);
		obj.setField2(13);
		
		entityManager.persist(obj);
		entityManager.getTransaction().commit();
	}

}
