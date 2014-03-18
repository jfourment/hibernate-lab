package org.testhibernate.customid;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MyObject {

	@Id
	@GenericGenerator(name="MyCustomGenerator", strategy="org.testhibernate.customid.CustomIdGenerator" )
	@GeneratedValue(generator="MyCustomGenerator" )

	@Column(name = "PM_ID", nullable = false, length=12)
	private long pmId;

	private Integer field1;
	
	private Integer field2;
	
	public long getPmId() {
		return pmId;
	}

	public void setPmId(long pmId) {
		this.pmId = pmId;
	}

	public Integer getField1() {
		return field1;
	}

	public void setField1(Integer field1) {
		this.field1 = field1;
	}

	public Integer getField2() {
		return field2;
	}

	public void setField2(Integer field2) {
		this.field2 = field2;
	}
	
	
}
