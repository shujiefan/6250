package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Restaurant;
import com.me.pojo.User;



public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String userEmail, String password) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :useremail and password = :password");
			q.setString("useremail", userEmail);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userEmail, e);
		}
	}
	
	public List<User> getList() throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User");		
			List<User> user = (List<User>) q.list();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user ", e);
		}
	}
	
	public User get(String userEmail){
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", userEmail);
			User user = (User) q.uniqueResult();
			return user;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
			return null;
		
	}
	

	public User register(User u) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(u);
			commit();
			return u;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	
	public boolean updateUser(String email) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", email);
			User user = (User) q.uniqueResult();
			if(user!=null){
				user.setStatus(1);
				getSession().update(user);
				commit();
				return true;
			}else{
				return false;
			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	
	}


	public User updateUserInfo(long userId, String address, String phoneNumber, String name) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where userId = :userId");
			q.setLong("userId", userId);
			User r = (User) q.uniqueResult();
			r.setAddress(address);
			r.setName(name);
			r.setPhoneNumber(phoneNumber);
			getSession().update(r);
			commit();
//			getSession().clear();
			return r;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating user info: " + e.getMessage());
		}
	}
}