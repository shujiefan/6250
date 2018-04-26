package com.me.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.dao.CartDAO;
import com.me.dao.CustomerDAO;
import com.me.dao.MenuDAO;
import com.me.dao.OrderDAO;
import com.me.dao.RestaurantDAO;
import com.me.dao.UserDAO;
import com.me.pojo.Cart;
import com.me.pojo.CartItem;
import com.me.pojo.Customer;
import com.me.pojo.Menu;
import com.me.pojo.Order;
import com.me.pojo.OrderItem;
import com.me.pojo.Restaurant;
import com.me.pojo.User;

@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@RequestMapping(value = "/customer/injectionError.htm", method = RequestMethod.GET)
	public String handleError() {

		return "injectionError";
	}
	

	@RequestMapping(value = "/customer/restaurants-list.htm", method = RequestMethod.GET)
	public String handleRestaurantList(HttpServletRequest request, RestaurantDAO restaurantDao, ModelMap map) {

		try {
			List<Restaurant> resList = restaurantDao.get();
			request.getSession().setAttribute("resList", resList);
//			map.addAttribute("rlist", rlist);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "restaurants-list";

	}
	
	
	@RequestMapping(value = "/customer/restaurant-menu.htm", method = RequestMethod.GET)
	public String showMenu(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, ModelMap map) {

		try {
			String id = request.getParameter("resId");
			
			Restaurant r = restaurantDao.get(Long.parseLong(id));
			List<Menu> menuList = menuDao.Userget(Long.parseLong(id));
			
			request.getSession().setAttribute("menuList", menuList);
			request.getSession().setAttribute("restaurant", r);
//			map.addAttribute("mlist", mlist);
//			map.addAttribute("restaurant", r);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "restaurant-menu";
	}
	
	@RequestMapping(value = "/customer/my-orders.htm", method = RequestMethod.GET)
	public String showMyOrders(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, CustomerDAO customerDao, OrderDAO orderDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			List<Order> orderList = orderDao.getOrder(u.getUserId());
			
			request.getSession().setAttribute("orderList", orderList);
			return "my-orders";

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/customer/order-details.htm", method = RequestMethod.GET)
	public String showMyOrderDetail(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, CustomerDAO customerDao, OrderDAO orderDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			String orderId = request.getParameter("orderId");
			Order order = orderDao.getOrderById(Long.parseLong(orderId));
			request.getSession().setAttribute("currentOrder", order);
						
			List<OrderItem> orderItemList = orderDao.getOrderItemList(Long.parseLong(orderId));
			
			request.getSession().setAttribute("orderItemList", orderItemList);
			return "order-details";

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/customer/updateOrder.htm", method = RequestMethod.GET)
	public String updateOrder(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao, CustomerDAO customerDao, OrderDAO orderDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			String orderId = request.getParameter("orderId");
			Order order = orderDao.getOrderById(Long.parseLong(orderId));
			orderDao.updateOrder(order);
			request.getSession().setAttribute("result", "Updated Successfully");

			return "cusSuccessView";			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/customer/viewCart.htm", method = RequestMethod.GET)
	public String viewCart(HttpServletRequest request, CartDAO cartDao, CustomerDAO customerDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			Customer c = customerDao.get(u.getUserId());
			
			if(c.getCart() != null) {
//				Set<CartItem> itemSet = c.getCart().getItemSet();
				List<CartItem> itemSet = cartDao.get(u.getUserId());
				System.out.println("size: " + itemSet.size());
				Cart cart = cartDao.getCart(u.getUserId());
				request.getSession().setAttribute("itemSet", itemSet);
				Set<Restaurant> resSet = new HashSet<Restaurant>();
				for(CartItem item : itemSet) {
					resSet.add(item.getMenu().getRestaurant());
				}
				System.out.println("res size: " + resSet.size());
				Map<Restaurant, Set<CartItem>> itemMap = new HashMap<Restaurant, Set<CartItem>>();
				
				for(Restaurant r : resSet) {
					Set<CartItem> list = new HashSet<CartItem>();
					for(CartItem item : itemSet) {
						Restaurant res = item.getMenu().getRestaurant();
						if(res == r) {
							list.add(item);
						}
					}
					itemMap.put(r, list);
					
				}
				System.out.println("map: " + itemMap.keySet());
				request.getSession().setAttribute("cart", cart);
				request.getSession().setAttribute("itemMap", itemMap);
				
//				map.addAttribute("itemSet", itemSet);
			}
			else {
				request.getSession().setAttribute("itemSet", new HashSet<CartItem>());
				request.getSession().setAttribute("cart", new Cart());
//				map.addAttribute("itemSet", new HashSet<CartItem>());
			}
			return "viewCart";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	@RequestMapping(value = "/customer/addToCart.htm", method = RequestMethod.GET)
	public String addToCart(HttpServletRequest request, CartDAO cartDao, CustomerDAO customerDao, MenuDAO menuDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			Customer c = customerDao.get(u.getUserId());
			String menuId = request.getParameter("menuId");
			String quantity = request.getParameter("quantity");
			Menu menu = menuDao.getMenu(Long.parseLong(menuId));
//			Cart cart = cartDao.getCart(u.getUserId());
			
			CartItem i = cartDao.getByMenuUser(menu.getMenuId(), u.getUserId());
			if(i == null) {
				Cart cart = cartDao.getCart(u.getUserId());
				CartItem item = new CartItem();
				item.setMenu(menu);
				item.setCart(cart);
				item.setQuantity(Integer.parseInt(quantity));
				item.setStatus(1);
				
				cartDao.create(item);
				System.out.println("previous quantity: " + cart.getTotalQuantity() );
				System.out.println("item quantity: " + item.getQuantity());
				System.out.println("previous price: " + cart.getTotalPrice());
				System.out.println("item price: " + item.getQuantity() * item.getMenu().getPrice());
				cartDao.updateCart(cart, cart.getTotalQuantity() + item.getQuantity(), cart.getTotalPrice() + item.getQuantity() * item.getMenu().getPrice());
				
	//			cart.setTotalPrice(cart.getTotalPrice() + item.getQuantity() * item.getMenu().getPrice());
	//			cart.setTotalQuantity(cart.getTotalQuantity() + item.getQuantity());				
			}
			else {
				Cart cart = cartDao.getCart(u.getUserId());
				cartDao.updateCartItem(i, Integer.parseInt(quantity) + i.getQuantity());
				cartDao.updateCart(cart, cart.getTotalQuantity() + Integer.parseInt(quantity), cart.getTotalPrice() + Integer.parseInt(quantity) * i.getMenu().getPrice());
			}
//			map.addAttribute("result2", "Added Successfully");			
			request.getSession().setAttribute("result", "Added Successfully");
			
			return "cusSuccessView";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	@RequestMapping(value = "/customer/removeItem.htm", method = RequestMethod.GET)
	public String removeItem(HttpServletRequest request, RestaurantDAO restaurantDao, CartDAO cartDao, MenuDAO menuDao, ModelMap map) {

		try {
			String itemId = request.getParameter("itemId");
			CartItem item = cartDao.getCartItem(Long.parseLong(itemId));
			if(item != null) {
				Cart cart = cartDao.getCart(item.getCart().getCartId());
//				System.out.println("previous quantity: " + item.getCart().getTotalQuantity());
//				System.out.println("item quantity: " + item.getQuantity());
//				System.out.println("previous price: " + item.getCart().getTotalPrice());
//				System.out.println("item price: " + item.getQuantity() * item.getMenu().getPrice());
				cartDao.updateCart(cart, cart.getTotalQuantity() - item.getQuantity(), cart.getTotalPrice() - item.getMenu().getPrice() * item.getQuantity());
//				item.getCart().setTotalPrice(item.getCart().getTotalPrice() - item.getMenu().getPrice() * item.getQuantity());
//				item.getCart().setTotalQuantity(item.getCart().getTotalQuantity() - item.getQuantity());
				
				cart.getItemSet().remove(item);
				item.setCart(null);
				Menu menu = menuDao.getMenu(item.getMenu().getMenuId());
				menu.getItemSet().remove(item);
				item.setMenu(null);
				cartDao.delete(item);
				
//				map.addAttribute("result3", "Removed Successfully");
				request.getSession().setAttribute("result", "Removed Successfully");
				return "cusSuccessView";
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
	
	
	
	@RequestMapping(value = "/customer/placeOrder.htm", method = RequestMethod.POST)
	public String placeOrder(HttpServletRequest request, RestaurantDAO restaurantDao, CartDAO cartDao, MenuDAO menuDao, CustomerDAO customerDao, OrderDAO orderDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			Customer c = customerDao.get(u.getUserId());
			Map<Restaurant, Set<CartItem>> itemMap = (Map<Restaurant, Set<CartItem>>) request.getSession().getAttribute("itemMap");
			
			if(itemMap.size() == 0) {
				return "error";
			}
			else {
				Set<CartItem> itemNotAddedSet = new HashSet<CartItem>();
				Set<OrderItem> itemAddedSet = new HashSet<OrderItem>();
				boolean flag = false;
				for(Restaurant r : itemMap.keySet()) {
					flag = false;
					Set<OrderItem> itemSet = new HashSet<OrderItem>();
					for(CartItem ci : itemMap.get(r)) {
						OrderItem oi = new OrderItem();
						Menu menu = menuDao.getMenu(ci.getMenu().getMenuId());
						if(ci.getQuantity() <= menu.getStock()) {
							oi.setMenu(menu);
							oi.setMenuName(menu.getName());
							oi.setMenuPrice(menu.getPrice());
							oi.setCartItem(ci);
							oi.setOrder(null);
							oi.setQuantity(ci.getQuantity());
							flag = true;
							orderDao.create(oi);
							itemSet.add(oi);
							itemAddedSet.add(oi);
							menuDao.updateMenuStock(menu, menu.getStock() - ci.getQuantity());
						}
						else {
							itemNotAddedSet.add(ci);
						}
					}
					int quantity = 0;
					double price = 0;
					for(OrderItem item : itemSet) {
						quantity += item.getQuantity();
						price += item.getQuantity() * item.getMenuPrice();
					}
					
					if(flag) {
						Order order = new Order();
						order.setCustomer(c);
						order.setOrderStatus("Processing");
						order.setRestaurant(r);
						order.setItemSet(itemSet);
						order.setTotalQuantity(quantity);
						order.setTotalPrice(price);
						
						orderDao.createOrder(order);
						for(OrderItem item : itemSet) {
							orderDao.update(item, order);
						}	
					}
									
				}
				List<CartItem> cartItemList = cartDao.get(u.getUserId());
				for(CartItem i : cartItemList) {
					cartDao.updateStatus(i);
				}
				cartDao.updateCart(cartDao.getCart(u.getUserId()), 0, 0);
				cartDao.get(u.getUserId());
				if(itemNotAddedSet.size() == 0 & itemAddedSet.size() > 0) {
					request.getSession().setAttribute("result", "Order placed Successfully");
				}
				if(itemAddedSet.size() == 0) {
					request.getSession().setAttribute("result", "failed to place order");
				}
				else if(itemNotAddedSet.size() < itemAddedSet.size() & itemNotAddedSet.size() != 0) {
					int itemNotAddedSetsize = itemNotAddedSet.size();
					request.getSession().setAttribute("itemNotAddedSet", itemNotAddedSetsize);
					request.getSession().setAttribute("result", "failed to place all");
				}
				return "cusSuccessView";
				
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/customer/customer-info.htm", method = RequestMethod.GET)
	public String showInfoForm(HttpServletRequest request, UserDAO userDao) {
				
		return "customer-info";
	}
	
	@RequestMapping(value = "/customer/customer-info.htm", method = RequestMethod.POST)
	public String handleRestaurantInfo(HttpServletRequest request, RestaurantDAO restaurantDao, UserDAO userDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			
			String name = request.getParameter("name");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");

			u = userDao.updateUserInfo(u.getUserId(), address, phoneNumber, name);	
			request.getSession().setAttribute("user", u);
			request.getSession().setAttribute("result", "Update Customer Information Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "cusSuccessView";
	}
	

	
	
}
