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

    public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(long id, Cart cart, Product pro, Date addedDate,
			Date removedDate) {
		super();
		this.id = id;
		this.cart = cart;
		this.pro = pro;
		this.addedDate = addedDate;
		this.removedDate = removedDate;
	}

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