package com.jun.biz.manager.model.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuhui
 * @version 1.0
 * @date 2013-5-6 下午10:22:44
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PicTextAdItem {
    private String link;
    private String picPath;
    private String text;
}
