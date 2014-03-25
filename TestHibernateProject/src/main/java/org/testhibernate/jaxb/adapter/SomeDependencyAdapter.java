package org.testhibernate.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.hibernate.Hibernate;
import org.testhibernate.jaxb.SomeDependency;

public class SomeDependencyAdapter extends XmlAdapter<SomeDependency, SomeDependency> {

	@Override
	public SomeDependency marshal(SomeDependency arg0) throws Exception {
		if (Hibernate.isInitialized(arg0)) {
			return arg0;
		}
		return null;
	}

	@Override
	public SomeDependency unmarshal(SomeDependency arg0) throws Exception {
		return arg0;
	}

	
	
}
