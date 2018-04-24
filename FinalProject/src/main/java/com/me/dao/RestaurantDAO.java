package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.CartItem;
import com.me.pojo.Restaurant;
import com.me.pojo.User;

public class RestaurantDAO extends DAO {

	public RestaurantDAO() {
		
	}
	
	public List<Restaurant> get() throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Restaurant");			
			List<Restaurant> rlist = (List<Restaurant>) q.list();
			System.out.println("aaa");
			commit();
			getSession().clear();
			return rlist;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get restaurant", e);
		}
	}
	
	public Restaurant register(Restaurant r) throws Exception {
		try {
			begin();
			System.out.println("aaa");
			getSession().save(r);
			commit();
//			getSession().clear();
			return r;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating restaurant: " + e.getMessage());
		}
	}
	
	public Restaurant get(long userId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where userId = :userId");
			q.setLong("userId", userId);	
			Restaurant r = (Restaurant) q.uniqueResult();
			commit();
//			getSession().clear();
			return r;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get restaurant " + userId, e);
		}
	}
	
	

}
