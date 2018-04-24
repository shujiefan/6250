package com.me.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_table")
@PrimaryKeyJoinColumn(name = "userId")
public class Restaurant extends User {
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="restaurant")
	private Set<Menu> menuSet = new HashSet<Menu>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="restaurant")
	private Set<Order> orderSet = new HashSet<Order>();
	

	
	public Restaurant() {
		
	}



	public Set<Menu> getMenuSet() {
		return menuSet;
	}



	public void setMenuSet(Set<Menu> menuSet) {
		this.menuSet = menuSet;
	}



	public Set<Order> getOrderSet() {
		return orderSet;
	}



	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}





	
	





	
	
	
}
