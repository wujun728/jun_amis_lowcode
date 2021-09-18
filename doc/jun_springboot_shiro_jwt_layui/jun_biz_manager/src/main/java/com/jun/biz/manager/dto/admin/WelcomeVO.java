package com.jun.biz.manager.dto.admin;

import lombok.Data;

import java.util.List;

import com.jun.biz.manager.model.dao.DateCount;

/**
 * Created on 2020/11/11 14:37
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class WelcomeVO {
    private Long userNum;
    private Long productNum;
    private Long orderNum;
    private Double orderAmount;
    private List<DateCount> loginCount;
    private List<DateCount> orderCount;
}
