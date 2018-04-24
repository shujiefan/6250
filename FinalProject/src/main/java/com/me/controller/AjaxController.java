package com.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.dao.MenuDAO;
import com.me.dao.RestaurantDAO;
import com.me.pojo.Menu;
import com.me.pojo.Restaurant;

@Controller
public class AjaxController {
	public AjaxController(){
		
	}
	
//	@RequestMapping(value = "/ajaxservice.htm", method = RequestMethod.POST)
//	@ResponseBody
//	public List<Menu> ajaxService(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao)
//	{
//		String queryString = request.getParameter("menuName");
////		String result = "";
//		try {
//			List<Restaurant> resList = restaurantDao.get();
//			List<Menu> menuList = new ArrayList();
//			for(Restaurant r : resList) {
//				List<Menu> mList = menuDao.get(r.getUserId());
//				for(Menu m : mList) {
//					if(m.getName().toLowerCase().contains(queryString.toLowerCase())){
//						menuList.add(m);
//					}
//				}
//			}
//			return menuList;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	@RequestMapping(value = "/ajaxservice.htm", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxService(HttpServletRequest request, RestaurantDAO restaurantDao, MenuDAO menuDao)
	{
		String queryString = request.getParameter("menuName");
		String result = "";
		try {
			List<Restaurant> resList = restaurantDao.get();
			List<Menu> menuList = new ArrayList();
			for(Restaurant r : resList) {
				List<Menu> mList = menuDao.get(r.getUserId());
				for(Menu m : mList) {
					if(m.getName().toLowerCase().contains(queryString.toLowerCase())){
						result += "<form action='addToCart.htm' method=\"GET\">\r\n" + 
								"			<div class=\"list-group\">\r\n" + 
								"				<div class=\"list-group-item\">\r\n" + 
								"					<div class=\"list-group-item-heading\">\r\n" + 
								"						<strong>" + m.getName() + "</strong> &nbsp;&nbsp;&nbsp; $" + m.getPrice() + "&nbsp;&nbsp;&nbsp;Restaurant:" + r.getName() + "\r\n" + 
								"					</div>\r\n" + 
								"					<select name=\"quantity\">\r\n" + 
								"						<option value=\"1\">1</option>\r\n" + 
								"						<option value=\"2\">2</option>\r\n" + 
								"						<option value=\"3\">3</option>\r\n" + 
								"						<option value=\"4\">4</option>\r\n" + 
								"						<option value=\"5\">5</option>\r\n" + 
								"					</select> \r\n" + 
								"					<input type=\"hidden\" name=\"menuId\" value=\"" + m.getMenuId() + "\">&nbsp;&nbsp;\r\n" + 
								"					<input type=\"submit\" class=\"btn btn-default\" value=\"Add To Cart\"/>\r\n" + 
								"				</div>\r\n" + 
								"			</div>\r\n" + 
								"		</form>";
					}
				}
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
