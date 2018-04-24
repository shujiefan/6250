package com.me.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "customer_table")
@PrimaryKeyJoinColumn(name = "userId")
public class Customer extends User{
		
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="customer")
	private Set<Order> orderSet = new HashSet<Order>();
	

	@OneToOne(cascade = CascadeType.ALL, mappedBy="customer")
	private Cart cart;

	public Set<Order> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}


	
	
	

}
