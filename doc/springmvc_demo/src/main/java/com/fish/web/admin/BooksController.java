package com.fish.web.admin;

import com.fish.cons.REST;
import com.fish.domain.Book;
import com.fish.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/23
 * Time: 15:09
 */
@Controller
@RequestMapping(REST.ADMIN_BOOKS)
public class BooksController {
    @Autowired
    private BooksService booksService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("title", "图书管理");
        modelMap.addAttribute("books", booksService.getAll());
        return "back/books";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@RequestParam("cover") CommonsMultipartFile file,
                          HttpServletRequest request, ModelMap modelMap) {
        Book book = new Book();
        book.setAddTime(new Date());
        book.setAuthor(request.getParameter("author"));
        book.setBookbinding(request.getParameter("bookbinding"));
        book.setCount(Integer.valueOf(request.getParameter("count")));
        book.setDesc(request.getParameter("desc"));
        try {
            String pages = request.getParameter("pages");
            book.setPages(Integer.valueOf(pages));
        } catch (Exception e) {}
        book.setTitle(request.getParameter("title"));
        book.setPressDesc(request.getParameter("pressDesc"));
        book.setSize(request.getParameter("size"));
        book.setPrint(request.getParameter("print"));
        try {
            book.setPrice(Double.valueOf(request.getParameter("price")));
        } catch (Exception e) {}
        try {
            String time = request.getParameter("publishTime");
            System.out.println(time);
            book.setPublishTime(new Date());
        } catch (Exception e) {}
//        booksService.addBook(book);

        if (booksService.addBook(book)) {
            modelMap.addAttribute("bookError", "添加成功");
        } else {
            modelMap.addAttribute("bookError", "添加失败");
        }
        if (!file.isEmpty()) {
            File localFile = new File(request.getRealPath("/public/images/book/cover") +
                    "\\" + booksService.maxId() + ".jpg");
            try {
                file.transferTo(localFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return "back/books";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") int id, ModelMap modelMap) {
        if (booksService.deleteBook(id)) {
            modelMap.addAttribute("bookError", "删除成功");
        } else {
            modelMap.addAttribute("bookError", "删除失败");
        }
        return "back/books";
    }

    @RequestMapping(value = "/online/{id}", method = RequestMethod.GET)
    public String online(@PathVariable("id") int id, ModelMap modelMap) {
        if (booksService.online(id)) {
            modelMap.addAttribute("bookError", "操作成功");
        } else {
            modelMap.addAttribute("bookError", "操作失败");
        }
        modelMap.addAttribute("books", booksService.getAll());
        return "back/books";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("cover") CommonsMultipartFile file,
                         HttpServletRequest request, ModelMap modelMap) {
        Book book = new Book();
        book.setAddTime(new Date());
        book.setAuthor(request.getParameter("author"));
        book.setBookbinding(request.getParameter("bookbinding"));
        book.setCount(Integer.valueOf(request.getParameter("count")));
        book.setDesc(request.getParameter("desc"));
        try {
            String pages = request.getParameter("pages");
            book.setPages(Integer.valueOf(pages));
        } catch (Exception e) {}
        book.setTitle(request.getParameter("title"));
        book.setPressDesc(request.getParameter("pressDesc"));
        book.setSize(request.getParameter("size"));
        book.setPrint(request.getParameter("print"));
        try {
            book.setPrice(Double.valueOf(request.getParameter("price")));
        } catch (Exception e) {}
        try {
            String time = request.getParameter("publishTime");
            System.out.println(time);
            book.setPublishTime(new Date());
        } catch (Exception e) {}
//        booksService.addBook(book);

        int id = -1;
        try {
            id = Integer.valueOf(request.getParameter("id"));
            if (booksService.updateBook(book, id)) {
                modelMap.addAttribute("bookError", "修改成功");
            } else {
                modelMap.addAttribute("bookError", "修改失败");
            }
        } catch (Exception e) {
            modelMap.addAttribute("bookError", "修改失败");
        }

        if (!file.isEmpty()) {
            System.out.println(request.getRealPath("/public/images/book/cover"));
            File localFile = new File(request.getRealPath("/public/images/book/cover") +
                    "\\" + booksService.maxId() + ".jpg");
            try {
                file.transferTo(localFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return "back/books";
    }
}
