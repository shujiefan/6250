package com.me.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Menu;
import com.me.pojo.Restaurant;
import com.me.pojo.User;

public class MenuDAO extends DAO {
	
	public MenuDAO() {
		
	}
	
	public List<Menu> get(long userId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Menu where userId = :userId and status = 1");
			q.setLong("userId", userId);
			List<Menu> rlist = (List<Menu>) q.list();
			commit();
			getSession().clear();
			return rlist;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get menu", e);
		}
	}
	
	
	public List<Menu> Userget(long userId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Menu where userId = :userId and status = 1 and stock > 0");
			q.setLong("userId", userId);
			List<Menu> rlist = (List<Menu>) q.list();
			commit();
			getSession().clear();
			return rlist;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get menu", e);
		}
	}
	

	
	public Menu getMenu(long menuId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Menu where menuId = :menuId");
			q.setLong("menuId", menuId);
			Menu m = (Menu) q.uniqueResult();
			System.out.println("aaa");
			commit();
//			getSession().clear();
			return m;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get menu", e);
		}
	}
	
	public Menu create(Menu r) throws Exception {
		try {
			begin();
			System.out.println("aaa");
			getSession().save(r);
			commit();
//			getSession().clear();
			return r;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating menu: " + e.getMessage());
		}
	}
	
	public boolean updateMenu(long menuId, String name, double price, int stock) throws Exception {
		try {
			begin();
			System.out.println("aaa DAO");
			Query q = getSession().createQuery("from Menu where menuId = :menuId");
			q.setLong("menuId", menuId);
			Menu m = (Menu) q.uniqueResult();
			if(m!=null){				
				m.setName(name);
				m.setPrice(price);
				m.setStock(stock);
				getSession().update(m);
				commit();
//				getSession().clear();
				return true;
			}else{
				return false;
			}
			

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating menu: " + e.getMessage());
		}
	
	}
	
	
	public boolean updateMenuStock(Menu menu, int stock) throws Exception {
		try {
			begin();				
			menu.setStock(stock);
			getSession().update(menu);
			commit();
//				getSession().clear();
			return true;
			
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating menu: " + e.getMessage());
		}
	
	}
	
	//set the menu status to 0, not delete it directly
	public void delete(Menu m) throws Exception {
        try {
            begin();
            m.setStatus(0);
            getSession().update(m);
            commit();
//            getSession().clear();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while deleting menu " + e.getMessage());
        }
    }
}
