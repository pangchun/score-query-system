package com.bs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 申请dto
 *
 * @author : zhangqianchun
 * @date : 2021-1-30 14:34
 * @description : 申请dto
 * @version : v1.0
 */
@ApiModel("申请dto")
@Data
public class ApplicationDTO {

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

    /**
     * 不用理会这个属性，这是layui一起传过来的；
     *
     */
    @ApiModelProperty("图片文件")
    private String file;


}
