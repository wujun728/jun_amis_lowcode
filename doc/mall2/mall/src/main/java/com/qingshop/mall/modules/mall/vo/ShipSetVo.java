package com.qingshop.mall.modules.mall.vo;

import java.math.BigDecimal;
import java.util.List;

import com.qingshop.mall.modules.mall.entity.MallSaleArea;
import com.qingshop.mall.modules.mall.entity.MallShipFree;
import com.qingshop.mall.modules.mall.entity.MallShipRule;


public class ShipSetVo {
	
	private Long setId;
	
    /**
     * 销售区域（1-部分区域，0-全国）
     */
    private Integer saleAreaType;

    /**
     * 是否自提
     */
    private Integer isSelfMention;

    /**
     * 运费方式（1-自定义邮费，0-卖家包邮）
     */
    private Integer freightType;

    /**
     * 首重
     */
    private BigDecimal defaultWeight;

    /**
     * 首重费
     */
    private BigDecimal defaultExpense;

    /**
     * 续重
     */
    private BigDecimal continueWeight;

    /**
     * 续重费
     */
    private BigDecimal continueExpense;
    
    /**
     * 销售区域
     */
    private List<MallSaleArea> saleArea;
    
    /**
     * 运费规则
     */
    private List<MallShipRule> shipRule;
    
    /**
     * 包邮规则
     */
    private List<MallShipFree> freeShip;
    
	public Long getSetId() {
		return setId;
	}

	public void setSetId(Long setId) {
		this.setId = setId;
	}

	public Integer getSaleAreaType() {
		return saleAreaType;
	}

	public void setSaleAreaType(Integer saleAreaType) {
		this.saleAreaType = saleAreaType;
	}

	public Integer getIsSelfMention() {
		return isSelfMention;
	}

	public void setIsSelfMention(Integer isSelfMention) {
		this.isSelfMention = isSelfMention;
	}

	public Integer getFreightType() {
		return freightType;
	}

	public void setFreightType(Integer freightType) {
		this.freightType = freightType;
	}

	public BigDecimal getDefaultWeight() {
		return defaultWeight;
	}

	public void setDefaultWeight(BigDecimal defaultWeight) {
		this.defaultWeight = defaultWeight;
	}

	public BigDecimal getDefaultExpense() {
		return defaultExpense;
	}

	public void setDefaultExpense(BigDecimal defaultExpense) {
		this.defaultExpense = defaultExpense;
	}

	public BigDecimal getContinueWeight() {
		return continueWeight;
	}

	public void setContinueWeight(BigDecimal continueWeight) {
		this.continueWeight = continueWeight;
	}

	public BigDecimal getContinueExpense() {
		return continueExpense;
	}

	public void setContinueExpense(BigDecimal continueExpense) {
		this.continueExpense = continueExpense;
	}

	public List<MallSaleArea> getSaleArea() {
		return saleArea;
	}

	public void setSaleArea(List<MallSaleArea> saleArea) {
		this.saleArea = saleArea;
	}

	public List<MallShipRule> getShipRule() {
		return shipRule;
	}

	public void setShipRule(List<MallShipRule> shipRule) {
		this.shipRule = shipRule;
	}

	public List<MallShipFree> getFreeShip() {
		return freeShip;
	}

	public void setFreeShip(List<MallShipFree> freeShip) {
		this.freeShip = freeShip;
	}
    
}
