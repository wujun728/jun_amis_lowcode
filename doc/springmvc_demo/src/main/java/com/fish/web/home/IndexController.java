package com.fish.web.home;

import com.fish.cons.REST;
import com.fish.domain.Book;
import com.fish.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/16
 * Time: 21:47
 */
@Controller
@RequestMapping(REST.INDEX)
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = {REST.INDEX, REST.GET_BOOKS}, method = {RequestMethod.GET, RequestMethod.POST})
    public String books(ModelMap modelMap) {
        modelMap.addAttribute("title", "香蕉鱼书店 BANANAFISH BOOKS");
        List<Book> books = indexService.getAllBooks();
        modelMap.addAttribute("books", books);
        return "front/index";
    }

    @RequestMapping(value = REST.GET_BOOKS_DETAIL, method = RequestMethod.GET)
    public String detail(@PathVariable("bookId") int id, ModelMap modelMap) {
        modelMap.addAttribute("title", "香蕉鱼书店");
        Book book = indexService.getBookById(id);
        modelMap.addAttribute("title", book.getTitle());
        modelMap.addAttribute("book", book);
        return "front/detail";
    }
}

