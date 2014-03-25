package org.testhibernate.jaxb;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "USER_VW")
@XmlRootElement(name = "user")

public class User implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "SM_DPND_ID")
	private SomeDependency someDependency;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "SM_OTH_TP_ID")
	private SomeOtherType someOtherType;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Profile profile;

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Stuff> stuffs; 
	
	@Id
    private Long id;
	

	//@JoinColumn(name = "PRFL_ID")
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public SomeOtherType getSomeOtherType() {
		return someOtherType;
	}

	public void setSomeOtherType(SomeOtherType otherType) {
		someOtherType = otherType;
	}

	//@XmlJavaTypeAdapter(value=org.testhibernate.jaxb.adapter.SomeDependencyAdapter.class)
	public SomeDependency getSomeDependency() {
		return someDependency;
	}

	public void setSomeDependency(SomeDependency dependency) {
		this.someDependency = dependency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Stuff> getStuffs() {
		return stuffs;
	}

	public void setStuffs(Set<Stuff> stuffs) {
		this.stuffs = stuffs;
	}


}