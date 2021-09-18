package com.jun.biz.manager.dto.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created on 2020/10/23 14:49
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class ModifyMyPasswordDTO {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
}
