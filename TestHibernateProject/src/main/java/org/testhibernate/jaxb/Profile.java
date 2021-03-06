package org.testhibernate.jaxb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="PROFILE")
@XmlRootElement(name="profile")
public class Profile  implements Serializable {

	@Id
	@Column(name = "PRFL_ID")
    private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
