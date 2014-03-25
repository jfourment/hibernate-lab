package org.testhibernate.jaxb;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testhibernate.dmlupdates.PersistentUnitLoader;

public class TestHibernateAndJAXB {

	
	@BeforeClass
	public static void initEntityManagerFactory() {
		PersistentUnitLoader.createEntityManager();
		initData();
	}
	
	//@Before
	public static void initData() {
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
		
		assertEquals("Found first user with ID 1", 1L, userList.get(0).getId().longValue());
		
		entityManager.close();
	}
	
	@Test
	public void testJaxb() {
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();
		
		
		entityManager.getTransaction().begin();
		
		List<User> userList = loadUsers(entityManager);
		
		assertEquals("Found one user", 1, userList.size());
		
		assertEquals("Found first user with ID 1", 1L, userList.get(0).getId().longValue());
		
		entityManager.close();
		PersistentUnitLoader.closeEntityManager();
		
		assertEquals("Found first user with profile ID 1", 1L, userList.get(0).getProfile().getId().longValue());
		
		System.out.println("Transform to XML...");
		 // create JAXB context and instantiate marshaller
	    JAXBContext context;
		try {
			context = JAXBContext.newInstance(User.class, Profile.class, SomeDependency.class, SomeOtherType.class);
			Marshaller m = context.createMarshaller();
			//m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// Write to System.out
		    m.marshal(userList.get(0), System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    // get variables from our xml file, created before
	    System.out.println();
	    System.out.println("Output from our XML File: ");
		
	}
	
	private static void insertFakeData(EntityManager entityManager) {
		
		
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
		user.setStuffs(new HashSet<Stuff>());
		Stuff stuff = new Stuff();
		stuff.setId(2L);
		user.getStuffs().add(stuff);
		entityManager.persist(user);
	}
	
	private List<User> loadUsers(EntityManager entityManager) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		criteria.distinct(true);
		Root<User> user = criteria.from(User.class);
		user.fetch("profile", JoinType.LEFT);
		//Join<User, Profile> profileJoin = user.join("profile", JoinType.INNER);
		user.fetch("someOtherType", JoinType.LEFT);
		//user.fetch("someDependency", JoinType.LEFT);
		user.fetch("stuffs", JoinType.LEFT);
		criteria.select(user);
		final TypedQuery<User> query = entityManager.createQuery(criteria);
		return query.getResultList();
	}

	@AfterClass
	public static void destroy() {
		PersistentUnitLoader.closeEntityManager();
	}

}
