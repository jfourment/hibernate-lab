package org.testhibernate.jaxb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SomeOtherType implements Serializable {

	@Id
	@Column(name = "SM_OTH_TP_ID")
    private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
