package org.testhibernate.dmlupdates;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="CART")
public class Cart implements Serializable {

    @Id
    //@GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="cart")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CartItem> items;

    public Cart() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}


}

