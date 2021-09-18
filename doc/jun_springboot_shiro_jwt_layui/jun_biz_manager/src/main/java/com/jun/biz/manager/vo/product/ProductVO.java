package com.jun.biz.manager.vo.product;


import lombok.Data;

import java.util.Date;


/**
 *
 */

@Data
public class ProductVO {


    /**
     * id       db_column: id
     */
    private Long id;
    /**
     * 商品名称       db_column: name
     */
    private String name;
    /**
     * 轮播图       db_column: pic_filenames
     */

    private String picFilenames;
    private String mainPic;
    /**
     * 销售价       db_column: price
     */
    private Double price;
    /**
     * 原价       db_column: original_price
     */
    private Double originalPrice;
    /**
     * 成本价       db_column: cost_price
     */
    private Double costPrice;
    /**
     * 页面描述       db_column: detail
     */
    private String detail;
    /**
     * 创建时间       db_column: create_time
     */
    private Date createTime;
    /**
     * createAdmin       db_column: create_admin
     */
    private String createAdmin;
    /**
     * 状态       db_column: status
     */
    private Integer status;
    /**
     * 分类       db_column: categoryid
     */
    private Long categoryId;
    private String allCategoryIds;
    /**
     * 库存       db_column: stock
     */
    private Integer stock;
    /**
     * 销量       db_column: sales_num
     */
    private Integer salesNum;
    /**
     * 虚拟销量       db_column: virtual_sales_num
     */
    private Integer virtualSalesNum;
    /**
     * 评价数量       db_column: evaluation_num
     */
    private Integer evaluationNum;
    /**
     * 商品评价的总平均分       db_column: evaluation_score
     */
    private Double evaluationScore;
    /**
     * properties       db_column: properties
     */
    private String properties;
    /**
     * 备用字段       db_column: note
     */
    private String note;


}






