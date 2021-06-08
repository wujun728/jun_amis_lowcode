package com.fish.web.home;

import com.fish.cons.CONST;
import com.fish.cons.REST;
import com.fish.domain.EBookInfo;
import com.fish.domain.EBookRes;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/18
 * Time: 21:44
 */
@Controller
@RequestMapping(REST.SEARCH)
public class SearchController {

    @RequestMapping(method = RequestMethod.GET)
    public String disSearch(HttpServletRequest req, ModelMap modelMap) {
        return search(1, req, modelMap);
    }

    @RequestMapping(value = REST.SEARCH_BOOK_DETAIL, method = RequestMethod.GET)
    public String detail(@PathVariable("bookId") long id, ModelMap modelMap) {
        modelMap.addAttribute("title", "Search");
        RestTemplate restTemplate = new RestTemplate();
        String res =
                restTemplate.getForObject(CONST.API + "/book/" + id, String.class);
        try {
            JSONObject jsonObject = new JSONObject(res);
            EBookInfo eBookInfo = new EBookInfo(jsonObject);
            modelMap.addAttribute("book", eBookInfo);
        } catch (JSONException e) {
//            e.printStackTrace();
        }
        return "front/search_book_detail";
    }

    @RequestMapping(value = REST.PAGE, method = RequestMethod.GET)
    public String search(@PathVariable("number") int page, HttpServletRequest req, ModelMap modelMap) {
        modelMap.addAttribute("title", "Search");
        try {
            String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");
            RestTemplate restTemplate = new RestTemplate();
            String query = CONST.API + "search/" + keyword + "/page/";
            String res =
                    restTemplate.getForObject(query + page, String.class);
//            System.out.println(res);
            JSONObject jsonObject = new JSONObject(res);
            EBookRes eBookRes = new EBookRes(jsonObject);
            modelMap.addAttribute("bookList", eBookRes);
//            modelMap.addAttribute("queryString", query);
            modelMap.addAttribute("keyword", keyword);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return "front/search";
    }
}
