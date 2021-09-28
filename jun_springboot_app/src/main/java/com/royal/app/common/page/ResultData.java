package com.royal.app.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 */
public class ResultData implements Serializable
{
    //
    private static final long serialVersionUID = -2652773809295318093L;

    /** 消息状态码 */
    private int               code;

    private String            msg;

    /** 总记录数 */
    private Long              total;

    /** 列表数据 */
    private List<?>           rows;

    private Object            data;

    /**
     * 
     * @author zmr
     */
    public ResultData()
    {
        super();
    }

    /**
     * @param code
     * @param msg
     * @author zmr
     */
    public ResultData(int code, String msg)
    {
        super();
        this.code = code;
        this.msg = msg;
    }

    /**
     * @param code
     * @param msg
     * @param data
     * @author zmr
     */
    public ResultData(int code, String msg, Object data)
    {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @param code
     * @param msg
     * @param total
     * @param rows
     * @author zmr
     */
    public ResultData(int code, String msg, Long total, List<?> rows)
    {
        super();
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.rows = rows;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Long getTotal()
    {
        return total;
    }

    public void setTotal(Long total)
    {
        this.total = total;
    }

    public List<?> getRows()
    {
        return rows;
    }

    public void setRows(List<?> rows)
    {
        this.rows = rows;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public static ResultData success()
    {
        return new ResultData(0, "success");
    }

    public static ResultData successMsg(String msg)
    {
        return new ResultData(0, msg);
    }

    public static ResultData success(Object data)
    {
        return new ResultData(0, "success", data);
    }

    public static ResultData eoror(int code, String msg)
    {
        return new ResultData(code, msg);
    }
}
