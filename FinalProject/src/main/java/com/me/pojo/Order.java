package com.me.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
//@PrimaryKeyJoinColumn(name = "userId")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId", unique = true, nullable = false)
    private long orderId;
	

	@ManyToOne
	@JoinColumn(name = "restaurant_userId")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "customer_userId")
	private Customer customer;
	
	@Column(name = "totalPrice")
	private double totalPrice;
	
	@Column(name = "totalQuantity")
	private int totalQuantity;
	
	@Column(name = "orderStatus")
	private String orderStatus;
	
	@OneToMany
	private Set<OrderItem> itemSet = new HashSet<OrderItem>();

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set<OrderItem> getItemSet() {
		return itemSet;
	}

	public void setItemSet(Set<OrderItem> itemSet) {
		this.itemSet = itemSet;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
}
