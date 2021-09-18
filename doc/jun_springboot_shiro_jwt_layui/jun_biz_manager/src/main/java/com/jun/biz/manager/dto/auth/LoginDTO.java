package com.jun.biz.manager.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created on 2020/10/14 19:30
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class LoginDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                '}';
    }
}
