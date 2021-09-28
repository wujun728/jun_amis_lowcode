package com.jun.plugin.ow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.jun.plugin.common.base.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 客户表 biz_customer
 * 
 * @author admin
 * @date 2021-09-25
 */
@Table(name = "biz_customer")
public class Customer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	@Id
	private Integer id;
	/** 客户名称 */
	private String cusname;
	/** 客户描述 */
	private String cusdesc;
	/** 客户全称 */
	private String fullname;
	/** 客户性质 */
	private String cussex;
	/** 注册时间 */
	private Date registerDate;
	/** 客户类型 */
	private String custype;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCusname(String cusname) 
	{
		this.cusname = cusname;
	}

	public String getCusname() 
	{
		return cusname;
	}
	public void setCusdesc(String cusdesc) 
	{
		this.cusdesc = cusdesc;
	}

	public String getCusdesc() 
	{
		return cusdesc;
	}
	public void setFullname(String fullname) 
	{
		this.fullname = fullname;
	}

	public String getFullname() 
	{
		return fullname;
	}
	public void setCussex(String cussex) 
	{
		this.cussex = cussex;
	}

	public String getCussex() 
	{
		return cussex;
	}
	public void setRegisterDate(Date registerDate) 
	{
		this.registerDate = registerDate;
	}

	public Date getRegisterDate() 
	{
		return registerDate;
	}
	public void setCustype(String custype) 
	{
		this.custype = custype;
	}

	public String getCustype() 
	{
		return custype;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cusname", getCusname())
            .append("cusdesc", getCusdesc())
            .append("fullname", getFullname())
            .append("cussex", getCussex())
            .append("registerDate", getRegisterDate())
            .append("custype", getCustype())
            .toString();
    }
}
