package org.testhibernate.jaxb;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testhibernate.dmlupdates.PersistentUnitLoader;

public class TestHibernateAndJAXB {

	
	@BeforeClass
	public static void initEntityManagerFactory() {
		PersistentUnitLoader.createEntityManager();
	}
	
	@Before
	public void initData() {
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();
		entityManager.getTransaction().begin();
		
		insertFakeData(entityManager);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	
	@Test
	public void testPersistAndLoad() {
		
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();	
		
		entityManager.getTransaction().begin();
		
		List<User> userList = loadUsers(entityManager);
		
		assertEquals("Found one user", 1, userList.size());
		
		assertEquals("Found first user with ID 1", 1, userList.size());
		
		
		entityManager.close();
	}
	
	@Test
	public void testJaxb() {
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();
		
		
		entityManager.getTransaction().begin();
		
		List<User> userList = loadUsers(entityManager);
		
		assertEquals("Found one user", 1, userList.size());
		
		assertEquals("Found first user with ID 1", 1, userList.size());
		
		entityManager.close();
		
	}
	
	private void insertFakeData(EntityManager entityManager) {
		
		
		User user = new User();
		user.setId(1L);
		Profile profile = new Profile();
		profile.setId(1L);
		user.setProfile(profile);
		SomeDependency dependency = new SomeDependency();
		dependency.setId(1L);
		user.setSomeDependency(dependency);
		SomeOtherType otherType = new SomeOtherType();
		otherType.setId(1L);
		user.setSomeOtherType(otherType);
		entityManager.persist(user);
	}
	
	private List<User> loadUsers(EntityManager entityManager) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		criteria.distinct(true);
		Root<User> user = criteria.from(User.class);
		Join<User, Profile> profileJoin = user.join("profile", JoinType.INNER);
		user.fetch("someOtherType", JoinType.LEFT);
		criteria.select(user);
//		Predicate inPredicate = profileJoin.get("profileType").in(types);
//		criteria.where(inPredicate);
		final TypedQuery<User> query = entityManager.createQuery(criteria);
		return query.getResultList();
	}

	@AfterClass
	public static void destroy() {
		PersistentUnitLoader.closeEntityManager();
	}

}
