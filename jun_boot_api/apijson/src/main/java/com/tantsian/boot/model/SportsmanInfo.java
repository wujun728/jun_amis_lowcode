package com.tantsian.boot.model;

import apijson.MethodAccess;
import apijson.framework.BaseModel;
import lombok.Data;

import java.util.Date;

import static apijson.RequestRole.*;

/**
 * @author LY
 * @description: TODO
 * @title: Stone
 * @projectName apijson-boot
 * @date 2020/11/30
 */
@MethodAccess(
        POST = {UNKNOWN, ADMIN},
        GET = {UNKNOWN, LOGIN, ADMIN},
        DELETE = {ADMIN}
)
@Data
public class SportsmanInfo {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String name;
    private Byte sex;
    private Byte age;
    private Double height;
    private Double weight;
    private Long sportItemId;
    private Long disabilityLevelId;
    private Long createUser;
    private Long modifiUser;
    private Date gmtCreate;
    private Date gmtModified;
    private Byte isDeleted;
    private Byte isActivated;
    private Long userType = 1L;


}
