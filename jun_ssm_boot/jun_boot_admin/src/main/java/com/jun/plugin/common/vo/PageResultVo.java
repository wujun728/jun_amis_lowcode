package com.jun.plugin.common.vo;

import java.util.List;

public class PageResultVo {
	
    private List<?> rows;
    
    private Long total;

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public PageResultVo(List<?> rows ,Long total) {
        this.total = total;
        this.rows = rows;
    }
}
