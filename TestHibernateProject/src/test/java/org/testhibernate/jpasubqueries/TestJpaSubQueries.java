package org.testhibernate.jpasubqueries;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testhibernate.dmlupdates.Cart;
import org.testhibernate.dmlupdates.CartItem;
import org.testhibernate.dmlupdates.PersistentUnitLoader;

public class TestJpaSubQueries {

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
	
	@AfterClass
	public static void destroy() {
		PersistentUnitLoader.closeEntityManager();
	}
	
	@Test
	public void test() {
		
		EntityManager entityManager = PersistentUnitLoader.getEntityManager();
		entityManager.getTransaction().begin();
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();
		Root<Cart> root = criteriaQuery.from(Cart.class);
		
		//EntityType<Cart> entityType = entityManager.getMetamodel().entity(Cart.class);
		//Root<Cart> root = criteriaQuery.getRoots().
		//criteriaQuery.distinct(true);
		
		ListJoin join = root.joinList("items");
		
		//criteriaQuery.multiselect(root, join).distinct(true);
		//criteriaQuery.where(criteriaBuilder.equal(join.index(), 1));
		
		//Join<CartItem, Cart> catJoin = root.join(CartItem_.catId, JoinType.INNER);
		//root.fetch("cart", JoinType.LEFT);
		
		criteriaQuery.orderBy(criteriaBuilder.desc(join.get("id")));               
		
		List<Cart> list = entityManager.createQuery(criteriaQuery).setMaxResults(5).getResultList();
		
		assertEquals("Should be 1", 1, list.size());
		
		entityManager.getTransaction().commit();
	}

	
	public void insertFakeData(EntityManager pEntityManager) {
		Cart c = new Cart();
		c.setItems(new ArrayList<CartItem>());
		
		for (int i = 0; i < 10; i++) {
			CartItem item = new CartItem();
			item.setId(i);
			//item.setRemovedDate(Calendar.getInstance().getTime());
			item.setCart(c);
			c.getItems().add(item);	
		}
		c.setId(new Long(1));
		pEntityManager.persist(c);
		
	}
}
