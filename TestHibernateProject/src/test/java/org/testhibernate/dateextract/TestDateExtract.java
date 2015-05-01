package org.testhibernate.dateextract;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testhibernate.customid.MyObject;
import org.testhibernate.dmlupdates.PersistentUnitLoader;

public class TestDateExtract {

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
		
		entityManager.createQuery("from Stuff where extract(wk from actionTime)=extract(wk from current_date())").getResultList();
		entityManager.getTransaction().commit();
	}
	
	@Test
	public void test1() {
		
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.createQuery("from Stuff").setFirstResult( 5 ).setMaxResults( 10 ).getResultList();
		entityManager.getTransaction().commit();
	}

}
