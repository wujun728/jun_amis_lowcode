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
public class TExamPaper {

    private Integer id;

    private String name;

    private Integer subjectId;

    private Integer paperType;

    private Integer gradeLevel;

    private Integer score;

    private Integer questionCount;

    private Integer suggestTime;

    private Date limitStartTime;

    private Date limitEndTime;

    private Integer frameTextContentId;

    private Integer createUser;

    private Date createTime;

    private Boolean deleted;

    private Integer taskExamId;

    private static final long serialVersionUID = 1L;
}
