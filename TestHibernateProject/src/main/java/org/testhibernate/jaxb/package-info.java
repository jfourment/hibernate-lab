@XmlJavaTypeAdapter(value=org.testhibernate.jaxb.adapter.HibernateProxyAdapter.class, type=PersistentSet.class)
package org.testhibernate.jaxb;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.collection.internal.PersistentSet;

