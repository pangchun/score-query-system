package com.bs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhangqianchun
 * @date : 2021-1-27 16:42
 * @description : 批量修改dto
 * @version : v1.0
 */
@ApiModel("批量修改dto")
@Data
public class BatchEditDTO {

    @ApiModelProperty("成绩id")
    private Long id;

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
    private Long teaId;

    @ApiModelProperty("教师姓名")
    private String teaName;

    @ApiModelProperty("学生成绩")
    private Double grade;
}
