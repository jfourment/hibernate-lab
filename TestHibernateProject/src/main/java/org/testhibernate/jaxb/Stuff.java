package org.testhibernate.jaxb;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stuff implements Serializable {

	@Id
	@Column(name = "STUFF_ID")
    private Long id;

	@Column(name = "ACTION_TIME")
	private Date actionTime;
	
	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
