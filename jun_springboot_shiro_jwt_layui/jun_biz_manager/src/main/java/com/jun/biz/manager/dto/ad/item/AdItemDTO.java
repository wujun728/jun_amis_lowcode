package com.jun.biz.manager.dto.ad.item;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AdItemDTO {


    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;
    @NotNull(message = "广告位id不能为空", groups = Add.class)
    private Long adSpaceId;
    @NotBlank
    private String name;
    @NotNull
    private Double weight;

    private Long objectId;

    private String text;
    private String link;
    private String picPath;

    public interface Add {
    }

    public interface Update {
    }

}
