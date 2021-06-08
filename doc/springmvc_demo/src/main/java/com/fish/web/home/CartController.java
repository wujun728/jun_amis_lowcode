package com.fish.web.home;

import com.fish.domain.Book;
import com.fish.domain.Order;
import com.fish.service.CartService;
import com.fish.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/23
 * Time: 22:22
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("title", "购物车");
        List<Book> list = new ArrayList<>();
        if (session.getAttribute("ids") != null) {
            Set<Integer> set = (Set<Integer>) session.getAttribute("ids");
            for (int i : set) {
                Book book = indexService.getBookById(i);
                list.add(book);
            }
        }

        modelMap.addAttribute("books", list);
        return "front/cart";
    }

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("title", "订单管理");
        modelMap.addAttribute("orders", cartService.getAll());
        return "back/cart";
    }

    @RequestMapping(value = "/cart/add/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String addToCart(@PathVariable("id") int id, HttpSession session) {
        Set<Integer> set;
        if (session.getAttribute("ids") == null) {
            set = new HashSet<>();
            set.add(id);
            session.setAttribute("ids", set);
        } else {
            set = (Set<Integer>) session.getAttribute("ids");
            set.add(id);
        }
        return set.toString();
    }

    @RequestMapping(value = "/cart/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, HttpSession session, ModelMap modelMap) {
        Set<Integer> set = (Set<Integer>) session.getAttribute("ids");
        if (set.remove(new Integer(id))) {
            modelMap.addAttribute("listError", "移除成功");
        } else {
            modelMap.addAttribute("listError", "移除失败");
        }
        return "front/cart";
    }

    @RequestMapping(value = "/admin/cart/{id}", method = RequestMethod.GET)
    public String done(@PathVariable("id") int id, ModelMap modelMap) {
        if (cartService.done(id)) {
            modelMap.addAttribute("cartSuccess", "完成");
        } else {
            modelMap.addAttribute("cartError", "操作失败");
        }
        modelMap.addAttribute("orders", cartService.getAll());
        return "back/cart";
    }

    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public String newList(ModelMap modelMap, CartCommand cartCommand, HttpSession session) {
        Order order = new Order();
        order.setAddress(cartCommand.getAddress());
        order.setEmail(cartCommand.getEmail());
        order.setName(cartCommand.getUsername());
        order.setNote(cartCommand.getNote());
        order.setOrderId(new Date().toString() + new Random().nextInt(1000));
        order.setPhone(cartCommand.getPhone());
        String price = cartCommand.getPrice();
        order.setPrice(Double.valueOf(price.substring(0, price.length() - 1)));
        order.setTime(new Date());
        if (cartService.save(order)) {
            modelMap.addAttribute("listError", "购买成功");
        } else {
            modelMap.addAttribute("listError", "购买失败");
        }
        session.invalidate();
        return "front/cart";
    }

    @RequestMapping(value = "/admin/orders/output", method = RequestMethod.GET)
    public ModelAndView output(ModelMap modelMap) {
        modelMap.addAttribute("orders", cartService.getAll());
        return new ModelAndView("excelView", "orders", cartService.getAll());
    }
}
