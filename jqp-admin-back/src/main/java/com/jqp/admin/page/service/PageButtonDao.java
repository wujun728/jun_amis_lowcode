package com.jqp.admin.page.service;

import com.jqp.admin.page.data.Form;
import com.jqp.admin.page.data.Page;
import com.jqp.admin.page.data.PageButton;

import java.util.List;

/**
 * com.jqp.admin.page.service.impl
 *
 * @author Leo Liu
 * @created 2022/4/8 2:36 PM
 */
public interface PageButtonDao {

    void save(PageButton pageButton);

    List<PageButton> byPageId(Long id);

    List<PageButton> byPage(Page page);

    List<PageButton> getByForm(Form form);

    void del(PageButton pageButton);
}
