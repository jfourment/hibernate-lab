package org.testhibernate.dmlupdates;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="CART_ITEM")
public class CartItem {

    @Id
    //@GeneratedValue
    private long id;

    @ManyToOne
    private Cart cart;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Product pro;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date addedDate;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date removedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getPro() {
		return pro;
	}

	public void setPro(Product pro) {
		this.pro = pro;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Date getRemovedDate() {
		return removedDate;
	}

	public void setRemovedDate(Date removedDate) {
		this.removedDate = removedDate;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

    

}