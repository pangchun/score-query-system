package com.bs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 科目实体类
 *
 * @author : zhangqianchun
 * @date : 2020-1-18 11:59
 * @description : 科目实体类
 * @version : v1.0
 */
@ApiModel("科目实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {

    @ApiModelProperty("科目id")
    private Integer id;

    @ApiModelProperty("科目编码")
    private String subCode;

    @ApiModelProperty("科目名")
    private String subName;

    @ApiModelProperty("及格分数")
    private Double subPass;

    @ApiModelProperty("科目总分")
    private Double subGrade;

    @ApiModelProperty("科目剩余数量")
    private Integer subNowCount;

    @ApiModelProperty("科目总数")
    private Integer subTotalCount;

    @ApiModelProperty("更新时间")
    private String updateTime;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("授课教师")
    private User teacher;
}
