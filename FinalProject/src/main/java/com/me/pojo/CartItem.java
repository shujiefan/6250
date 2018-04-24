package com.me.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cartItem_table")
public class CartItem {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemId", unique = true, nullable = false)
	private long itemId;
	

	
	@ManyToOne
	@JoinColumn(name = "customer_cartId")
	private Cart cart;
	
	
	@ManyToOne
	@JoinColumn(name = "restaurant_menuId")
	private Menu menu;
	
	@Column(name = "quantity")
	private int quantity;
	
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="cartItem")
	private OrderItem orderItem;
	
	@Column(name = "status")
	private int status;



	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
	
//	public Restaurant getRestaurant() {
//		return restaurant;
//	}
//
//	public void setRestaurant(Restaurant restaurant) {
//		this.restaurant = restaurant;
//	}





	
}
