package com.me.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.dao.CartDAO;
import com.me.dao.CustomerDAO;
import com.me.dao.MenuDAO;
import com.me.dao.OrderDAO;
import com.me.dao.RestaurantDAO;
import com.me.dao.UserDAO;
import com.me.pojo.Cart;
import com.me.pojo.CartItem;
import com.me.pojo.Menu;
import com.me.pojo.Order;
import com.me.pojo.OrderItem;
import com.me.pojo.Restaurant;
import com.me.pojo.User;

@Controller
public class RestaurantController {

	private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);
	
	@RequestMapping(value = "/restaurant/injectionError.htm", method = RequestMethod.GET)
	public String handleError() {

		return "injectionError";
	}

	@RequestMapping(value = "/restaurant/menu.htm", method = RequestMethod.GET)
	public String showMenu(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			List<Menu> mlist = menuDao.get(u.getUserId());
			request.getSession().setAttribute("mlist", mlist);
			return "menu";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	
	
	
	@RequestMapping(value = "/restaurant/addMenu.htm", method = RequestMethod.GET)
	public String showAddMenuForm(HttpServletRequest request, MenuDAO menuDao, ModelMap map) {
		return "addMenu";
	}
	
	@RequestMapping(value = "/restaurant/addMenu.htm", method = RequestMethod.POST)
	public String AddMenuForm(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, ModelMap map) {
		try {
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String stock = request.getParameter("stock");

			User u = (User) request.getSession().getAttribute("user");
			Restaurant restaurant = restaurantDao.get(u.getUserId());
			
			Menu m = new Menu();
			m.setName(name);
			m.setPrice(Double.parseDouble(price));
			m.setRestaurant(restaurant);
			m.setStock(Integer.parseInt(stock));			
			m.setStatus(1);
			Menu menu = menuDao.create(m);

			return "resSuccess";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/restaurant/updateMenu.htm", method = RequestMethod.GET)
	public String showUpdateMenuForm(HttpServletRequest request, MenuDAO menuDao, ModelMap map) {
		String menuId = request.getParameter("menuId");
		request.getSession().setAttribute("menuId", menuId);
		try {
			Menu m = menuDao.getMenu(Long.parseLong(menuId));
			request.getSession().setAttribute("menu", m);
//			map.addAttribute("menu", m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "updateMenu";
	}
	
	@RequestMapping(value = "/restaurant/updateMenu.htm", method = RequestMethod.POST)
	public String UpdateMenuForm(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, CartDAO cartDao, ModelMap map) {
		try {
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String stock = request.getParameter("stock");

			String menuId = (String) request.getSession().getAttribute("menuId");
			if(menuId != null) {
				try {
					Menu m = menuDao.getMenu(Long.parseLong(menuId));
					double previousPrice = m.getPrice();
//					Restaurant restaurant = m.getRestaurant();
					boolean flag = menuDao.updateMenu(Long.parseLong(menuId), name, Double.parseDouble(price), Integer.parseInt(stock));
					if(flag) {
						List<Cart> cartList = cartDao.getAllCart();
						for(Cart c : cartList) {
							CartItem item = cartDao.getByMenuUser(m.getMenuId(), c.getCartId());
							if(item != null) {
								cartDao.updateCart(c, c.getTotalQuantity(), c.getTotalPrice() + (Double.parseDouble(price) - previousPrice) * item.getQuantity());
							}
						}
						m = menuDao.getMenu(Long.parseLong(menuId));
						request.getSession().setAttribute("menu", m);
						return "resSuccess";
					}
					else {
						return "error";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/restaurant/deleteMenu.htm", method = RequestMethod.GET)
	public String deleteMenu(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, CartDAO cartDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			String menuId = request.getParameter("menuId");
			Menu menu = menuDao.getMenu(Long.parseLong(menuId));
			if(menu != null) {
//				menu.getRestaurant().getMenuSet().remove(menu);
//				menu.setRestaurant(null);
				List<CartItem> itemList = cartDao.getByMenu(menu.getMenuId());
				for(CartItem item : itemList) {
					Cart cart = cartDao.getCart(item.getCart().getCartId());
					cartDao.updateCart(cart, cart.getTotalQuantity() - item.getQuantity(), cart.getTotalPrice() - item.getQuantity() * item.getMenu().getPrice());
//					cart.setTotalPrice(item.getCart().getTotalPrice() - item.getMenu().getPrice() * item.getQuantity());
//					item.getCart().setTotalQuantity(item.getCart().getTotalQuantity() - item.getQuantity());
					
					cart.getItemSet().remove(item);
					item.setCart(null);
					Menu m = menuDao.getMenu(item.getMenu().getMenuId());
					m.getItemSet().remove(item);
					item.setMenu(null);
					
					cartDao.delete(item);
				}
				
				menuDao.delete(menu);
				return "resSuccess";
			}
			else {
				return "error";
			}			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	@RequestMapping(value = "/restaurant/res-info.htm", method = RequestMethod.GET)
	public String showInfoForm(HttpServletRequest request, RestaurantDAO restaurantDao) {
		
		
		return "res-info";
	}
	
	@RequestMapping(value = "/restaurant/res-info.htm", method = RequestMethod.POST)
	public String handleRestaurantInfo(HttpServletRequest request, RestaurantDAO restaurantDao, UserDAO userDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			
			String name = request.getParameter("name");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");

			u = userDao.updateUserInfo(u.getUserId(), address, phoneNumber, name);	
			request.getSession().setAttribute("user", u);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "resSuccess";
	}
	
	
	@RequestMapping(value = "/restaurant/received-orders.htm", method = RequestMethod.GET)
	public String showMyOrders(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, CustomerDAO customerDao, OrderDAO orderDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			List<Order> orderList = orderDao.getOrderByRes(u.getUserId());
			
			request.getSession().setAttribute("resorderList", orderList);
			return "received-orders";

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/restaurant/received-order-details.htm", method = RequestMethod.GET)
	public String showMyOrderDetail(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, CustomerDAO customerDao, OrderDAO orderDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			String orderId = request.getParameter("orderId");
			Order order = orderDao.getOrderById(Long.parseLong(orderId));
			request.getSession().setAttribute("rescurrentOrder", order);
						
			List<OrderItem> orderItemList = orderDao.getOrderItemList(Long.parseLong(orderId));
			
			request.getSession().setAttribute("resorderItemList", orderItemList);
			return "received-order-details";

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/restaurant/updateOrder.htm", method = RequestMethod.GET)
	public String updateOrder(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, CustomerDAO customerDao, OrderDAO orderDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			String orderId = request.getParameter("orderId");
			Order order = orderDao.getOrderById(Long.parseLong(orderId));
			orderDao.ResupdateOrder(order);

			return "resSuccess";			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
