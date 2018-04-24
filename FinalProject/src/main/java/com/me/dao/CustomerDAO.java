package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Customer;
import com.me.pojo.Restaurant;

public class CustomerDAO  extends DAO {

	public CustomerDAO() {
		
	}
	
	public List<Customer> get() throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Customer");			
			List<Customer> clist = (List<Customer>) q.list();
			commit();
			getSession().clear();
			return clist;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get customer", e);
		}
	}
	
	public Customer register(Customer r) throws Exception {
		try {
			begin();
			getSession().save(r);
			commit();
//			getSession().clear();
			return r;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating customer: " + e.getMessage());
		}
	}
	
	public Customer get(long userId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where userId = :userId");
			q.setLong("userId", userId);	
			Customer r = (Customer) q.uniqueResult();
			commit();
//			getSession().clear();
			return r;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get customer " + userId, e);
		}
	}
}
