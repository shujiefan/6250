package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.pojo.Cart;
import com.me.pojo.CartItem;
import com.me.pojo.Customer;
import com.me.pojo.Menu;

public class CartDAO extends DAO {
	
	public CartDAO() {
		
	}
	
	public List<CartItem> get(long customer_cartId) throws Exception {
		try {
			getSession().clear();
			begin();
			Query q = getSession().createQuery("from CartItem where customer_cartId = :customer_cartId and status = 1");			
			q.setLong("customer_cartId", customer_cartId);
			List<CartItem> clist = (List<CartItem>) q.list();
			commit();
			getSession();
			return clist;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get cart items ", e);
		}
	}
	
	public CartItem getByMenuUser(long restaurant_menuId, long customer_cartId) throws Exception {
		try {
			getSession().clear();
			begin();
			Query q = getSession().createQuery("from CartItem where restaurant_menuId = :restaurant_menuId and customer_cartId = :customer_cartId and status = 1");			
			q.setLong("restaurant_menuId", restaurant_menuId);
			q.setLong("customer_cartId", customer_cartId);
			CartItem clist = (CartItem) q.uniqueResult();
			getSession();
			commit();
//			getSession().clear();
			return clist;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get cart items ", e);
		}
	}
	
	
	public List<CartItem> getByMenu(long restaurant_menuId) throws Exception {
		try {
			begin();
			getSession().clear();
			Query q = getSession().createQuery("from CartItem where restaurant_menuId = :restaurant_menuId and status = 1");			
			q.setLong("restaurant_menuId", restaurant_menuId);
			List<CartItem> clist = (List<CartItem>) q.list();
			getSession();
			commit();
//			getSession().clear();
			return clist;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get cart items ", e);
		}
	}
	

	
	public Cart getCart(long cartId) throws Exception {
		try {
//			getSession().clear();
			begin();
			Query q = getSession().createQuery("from Cart where cartId = :cartId");	
			q.setLong("cartId", cartId);
			Cart c = (Cart) q.uniqueResult();
//			getSession();
			commit();
//			getSession().clear();
			return c;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get cart ", e);
		} 
	}
	
	public List<Cart> getAllCart() throws Exception {
		try {
			getSession().clear();
			begin();
			Query q = getSession().createQuery("from Cart");	
			List<Cart> c = (List<Cart>) q.list();
			getSession();
			commit();
//			getSession().clear();
			return c;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get cart ", e);
		} 
	}
	
	

	
	
	
	
	
//	public Cart getCartByItem(long itemId) throws Exception {
//		try {
//			begin();
//			Query q = getSession().createQuery("from CartItem where itemId = :itemId");	
//			q.setLong("itemId", itemId);
//			Cart c = (Cart) q.uniqueResult();
//			commit();
////			getSession().clear();
//			return c;
//		} catch (HibernateException e) {
//			rollback();
//			throw new Exception("Could not get cart ", e);
//		}
//	}
	
	
	public CartItem updateCartItem(CartItem c, int quantity) throws Exception {
		try {
			getSession().clear();
			begin();
			c.setQuantity(quantity);
			getSession().update(c);
			commit();
			getSession();
			return c;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get cart ", e);
		}
	}
	
//	public CartItem updateCartItemPrice(CartItem c, double price) throws Exception {
//		try {
//			getSession().clear();
//			begin();
//			c.
//			getSession().update(c);
//			commit();
//			getSession();
//			return c;
//		} catch (HibernateException e) {
//			rollback();
//			throw new Exception("Could not get cart ", e);
//		}
//	}
	
	
	public CartItem getCartItem(long itemId) throws Exception {
		try {
			getSession().clear();
			begin();
//			getSession().clear();
			Query q = getSession().createQuery("from CartItem where itemId = :itemId and status = 1");
			q.setLong("itemId", itemId);
			CartItem m = (CartItem) q.uniqueResult();
			getSession();
			commit();
//			getSession().clear();
			return m;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while geting cartItem: " + e.getMessage());
		}
	}
	
	
	
	public CartItem create(CartItem c) throws Exception {
		try {
			begin();
			getSession().save(c);
			commit();
//			getSession().clear();
			return c;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating cartItem: " + e.getMessage());
		}
	}
	
	public Cart createCart(Cart c) throws Exception {
		try {
			begin();
			getSession().save(c);
			commit();
//			getSession().clear();
			return c;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating cart: " + e.getMessage());
		}
	}
	
	public void delete(CartItem c) throws Exception {
        try {
        	getSession().clear();
        	begin();
            getSession().delete(c);
            getSession();
            commit();
//            getSession().clear();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while deleting cart item " + e.getMessage());
        }
    }
	
	
	public void updateStatus(CartItem c) throws Exception {
        try {
        	getSession().clear();
        	begin();
        	c.setStatus(0);
            getSession().update(c);
            getSession();
            commit();
//            getSession().clear();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while deleting cart item " + e.getMessage());
        }
    }
	
	
	public Cart updateCart(Cart m, int quantity, double price) throws Exception {
		try {
			begin();	
			getSession().clear();
//			Query q = getSession().createQuery("from Cart where cartId = :cartId");
//			q.setLong("cartId", cartId);
//			Cart m = (Cart) q.uniqueResult();
			m.setTotalQuantity(quantity);
			m.setTotalPrice(price);
			getSession().update(m);
			getSession();
			commit();			
			return m;


		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating cart item: " + e.getMessage());
		}
	
	}

}
