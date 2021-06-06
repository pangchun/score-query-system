package com.bs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分数修改dto
 *
 * @author : zhangqianchun
 * @date : 2020-1-26 17:15
 * @description : 分数修改dto
 * @version : v1.0
 */
@ApiModel("分数修改dto")
@Data
public class UpdateDTO {

    @ApiModelProperty("成绩编号")
    private String scoId;

    @ApiModelProperty("科目编号")
    private String subId;

    @ApiModelProperty("学生账户")
    private String stuUserName;

    @ApiModelProperty("分数")
    private Double grade;

    @ApiModelProperty("分数")
    private Double subGrade;

    @ApiModelProperty("备注")
    private String remark;
}
