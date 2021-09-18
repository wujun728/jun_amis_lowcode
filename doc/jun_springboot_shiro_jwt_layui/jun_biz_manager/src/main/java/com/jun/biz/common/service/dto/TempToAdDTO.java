package com.jun.biz.common.service.dto;

import lombok.Data;

/**
 * Created on 2020/10/26 18:56
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class TempToAdDTO {
    private Long adSpaceId;
    private String tempFilePath;
    private Integer width;
    private Integer height;
}
