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
public class TExamPaperAnswer {

    private Integer id;

    private Integer examPaperId;

    private String paperName;

    private Integer paperType;

    private Integer subjectId;

    private Integer systemScore;

    private Integer userScore;

    private Integer paperScore;

    private Integer questionCorrect;

    private Integer questionCount;

    private Integer doTime;

    private Integer status;

    private Integer createUser;

    private Date createTime;

    private Integer taskExamId;

    private String remark;

    private static final long serialVersionUID = 1L;
}
