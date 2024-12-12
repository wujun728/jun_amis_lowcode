package com.jqp.admin.page.service.impl;

import com.jqp.admin.db.service.JdbcService;
import com.jqp.admin.page.data.Form;
import com.jqp.admin.page.data.Page;
import com.jqp.admin.page.data.PageButton;
import com.jqp.admin.page.service.PageButtonDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.jqp.admin.page.service.impl
 *
 * @author Leo Liu
 * @created 2022/4/8 2:38 PM
 */
@Service
public class PageButtonDaoImpl implements PageButtonDao {
    @Resource
    JdbcService jdbcService;

    @Override
    public void save(PageButton pageButton){
        jdbcService.saveOrUpdate(pageButton);
    }

    @Override
    public List<PageButton> byPageId(Long id) {
        return jdbcService.find(PageButton.class, "pageId", id);
    }

    @Override
    public List<PageButton> byPage(Page page) {
        return jdbcService.find(PageButton.class, "pageId", page.getId());
    }

    @Override
    public List<PageButton> getByForm(Form form) {
        return jdbcService.find(PageButton.class, new String[]{"optionValue", "optionType"}, new Object[]{form.getCode(), "form"});
    }

    @Override
    public void del(PageButton pageButton) {
        jdbcService.delete(pageButton);
    }
}
