package com.jun.biz.manager.dto.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
public class AdminDTO {

    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;

    @NotBlank(message = "用户名不能为空", groups = Add.class)
    private String username;

    @NotBlank(message = "密码不能为空", groups = Add.class)
    private String password;
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "\\d{11}", message = "手机号格式错误")
    private String phone;
    @NotBlank(message = "请选择角色", groups = {Update.class, Add.class})
    private String roleIds;

    public interface Add {
    }

    public interface Update {
    }

    public interface UpdateMine {
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roleIds='" + roleIds + '\'' +
                '}';
    }
}
