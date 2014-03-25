package org.testhibernate.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.hibernate.Hibernate;

public class HibernateProxyAdapter extends XmlAdapter<Object, Object> {

	@Override
	public Object marshal(Object arg0) throws Exception {
		if (Hibernate.isInitialized(arg0)) {
			System.out.println("Entered adapter with object "+arg0 );
			return arg0;
		}
		return null;
	}

	@Override
	public Object unmarshal(Object arg0) throws Exception {
		return arg0;
	}
}
