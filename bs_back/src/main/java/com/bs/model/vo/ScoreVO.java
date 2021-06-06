package com.bs.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 成绩VO
 *
 * @author : zhangqianchun
 * @date : 2020-1-18 13:34
 * @description : 用于在业务层封装返回结果
 * @version : v1.0
 */
@ApiModel("成绩VO")
@Data
public class ScoreVO {

    /**
     * 不用Long返回是为了防止精度丢失
     */
    @ApiModelProperty("成绩id")
    private String id;

    @ApiModelProperty("学生账号")
    private String stuUserName;

    @ApiModelProperty("学生姓名")
    private String stuName;

    @ApiModelProperty("科目编号")
    private Integer subId;

    @ApiModelProperty("科目名字")
    private String subName;

    @ApiModelProperty("科目总分")
    private Double subGrade;

    @ApiModelProperty("及格分数")
    private Double subPass;

    @ApiModelProperty("教师编号")
    private String teaId;

    @ApiModelProperty("教师姓名")
    private String teaName;

    @ApiModelProperty("学生成绩")
    private String grade;
}
