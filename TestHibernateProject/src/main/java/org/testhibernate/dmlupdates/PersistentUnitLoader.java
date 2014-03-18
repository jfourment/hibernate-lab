package org.testhibernate.dmlupdates;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PersistentUnitLoader {
	static Log logger = LogFactory.getLog(PersistentUnitLoader.class);

	public static final String PERSISTENCE_ECLIPSELINK_UNIT_NAME = "pu-eclipselink";
	public static final String PERSISTENCE_HIBERNATE_UNIT_NAME = "pu-hibernate";
	
	//private static final String DATABASE_JDBC_URL = "jdbc:postgresql:DATABASE_NAME?stringtype=unspecified";
	private static final String DATABASE_JDBC_URL = "jdbc:h2:mem:testdb";
	
	private static final String DATABASE_USERNAME = "sa";
	private static final String DATABASE_PASSWORD = "";

	private static EntityManagerFactory factory;

	public static void createEntityManager() {
		factory = Persistence.createEntityManagerFactory("myPersistenceUnit", getProperties());
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static void closeEntityManager() {
		
		factory.close();
	}

	private static Properties getProperties() {
		Properties properties = new Properties();
		properties
				.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		//properties.put("javax.persistence.database-major-version", "9");
		//properties.put("javax.persistence.database-minor-version", "3");
		properties.put("javax.persistence.jdbc.url", DATABASE_JDBC_URL);
		properties.put("javax.persistence.jdbc.user", DATABASE_USERNAME);
		properties.put("javax.persistence.jdbc.password", DATABASE_PASSWORD);
		properties.put("javax.persistence.database-product-name", "H2");
		properties.put("javax.persistence.schema-generation.database.action",
				"drop-and-create");
		properties.put(
				"javax.persistence.schema-generation.create-database-schemas",
				"true");
		return properties;
	}
}
