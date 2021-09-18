package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class Evaluation  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * userId       db_column: user_id
     */

private Integer userId;

    /**
     * 昵称       db_column: nickname
     */

private String nickname;

    /**
     * type       db_column: type
     */

private Integer type;

    /**
     * description       db_column: description
     */

private String description;

    /**
     * 认为此评价有用人数       db_column: agree
     */

private Integer agree;

    /**
     * productId       db_column: product_id
     */

private Integer productId;

    /**
     * time       db_column: time
     */

private Date time;

    /**
     * 服务态度得分       db_column: attitude_score
     */

private Double attitudeScore;

    /**
     * 描述相符得分       db_column: describe_score
     */

private Double describeScore;

    /**
     * 发货速度得分       db_column: speed_score
     */

private Double speedScore;







}






