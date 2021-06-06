package com.bs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 申请实体
 *
 * @author : zhangqianchun
 * @date : 2021-1-30 14:34
 * @description : 因为联表查询比较麻烦，所以我将这些属性全部放到一张申请表中，只需要查一张表即可；
 * @version : v1.0
 */
@ApiModel("申请实体")
@Data
public class Application {

    @ApiModelProperty("申请编号")
    private String id;

    @ApiModelProperty("成绩编号")
    private String scoId;

    @ApiModelProperty("学生账户")
    private String stuUserName;

    @ApiModelProperty("学生姓名")
    private String stuName;

    @ApiModelProperty("科目id")
    private String subId;

    @ApiModelProperty("科目名")
    private String subName;

    @ApiModelProperty("教师id")
    private String teaId;

    @ApiModelProperty("教师姓名")
    private String teaName;

    @ApiModelProperty("科目总分")
    private Double subGrade;

    @ApiModelProperty("学生得分")
    private Double grade;

    @ApiModelProperty("修改分数")
    private Double applyGrade;

    @ApiModelProperty("申请描述")
    private String applyDescription;

    @ApiModelProperty("图片地址")
    private String imgUri;

    @ApiModelProperty("审批状态")
    private Integer status;

}
