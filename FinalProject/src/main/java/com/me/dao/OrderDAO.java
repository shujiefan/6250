package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.CartItem;
import com.me.pojo.Order;
import com.me.pojo.OrderItem;

public class OrderDAO extends DAO {
	public List<Order> getOrder(long customer_userId) throws Exception {
		try {
			getSession().clear();
			begin();
//			getSession().clear();
			Query q = getSession().createQuery("from Order where customer_userId = :customer_userId");
			q.setLong("customer_userId", customer_userId);
			List<Order> m = (List<Order>) q.list();
			getSession();
			commit();
//			getSession().clear();
			return m;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while geting order: " + e.getMessage());
		}
	}
	
	
	public List<Order> getOrderByRes(long restaurant_userId) throws Exception {
		try {
			getSession().clear();
			begin();
//			getSession().clear();
			Query q = getSession().createQuery("from Order where restaurant_userId = :restaurant_userId");
			q.setLong("restaurant_userId", restaurant_userId);
			List<Order> m = (List<Order>) q.list();
			getSession();
			commit();
//			getSession().clear();
			return m;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while geting order: " + e.getMessage());
		}
	}
	
	
	public Order getOrderById(long orderId) throws Exception {
		try {
			getSession().clear();
			begin();
//			getSession().clear();
			Query q = getSession().createQuery("from Order where orderId = :orderId");
			q.setLong("orderId", orderId);
			Order m = (Order) q.uniqueResult();
			getSession();
			commit();
//			getSession().clear();
			return m;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while geting order: " + e.getMessage());
		}
	}
	
	
	public List<OrderItem> getOrderItemList(long order_orderId) throws Exception {
		try {
			getSession().clear();
			begin();
//			getSession().clear();
			Query q = getSession().createQuery("from OrderItem where order_orderId = :order_orderId");
			q.setLong("order_orderId", order_orderId);
			List<OrderItem> m = (List<OrderItem>) q.list();
			getSession();
			commit();
//			getSession().clear();
			return m;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while geting order item: " + e.getMessage());
		}
	}
	
	
	public OrderItem create(OrderItem c) throws Exception {
		try {
			begin();
			getSession().save(c);
			commit();
//			getSession().clear();
			return c;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating orderItem: " + e.getMessage());
		}
	}
	
	public Order createOrder(Order c) throws Exception {
		try {
			begin();
			getSession().save(c);
			commit();
//			getSession().clear();
			return c;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating order: " + e.getMessage());
		}
	}
	
	public OrderItem update(OrderItem c, Order order) throws Exception {
		try {
			begin();
			c.setOrder(order);
			getSession().update(c);
			commit();
//			getSession().clear();
			return c;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating orderItem: " + e.getMessage());
		}
	}
	
	
	public Order updateOrder(Order order) throws Exception {
		try {
			begin();
			order.setOrderStatus("Customer Received Food");
			getSession().update(order);
			commit();
//			getSession().clear();
			return order;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating order: " + e.getMessage());
		}
	}
	
	public Order ResupdateOrder(Order order) throws Exception {
		try {
			begin();
			order.setOrderStatus("Food under delivering");
			getSession().update(order);
			commit();
//			getSession().clear();
			return order;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating order: " + e.getMessage());
		}
	}
}
