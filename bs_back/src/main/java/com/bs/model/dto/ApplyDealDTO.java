package com.bs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhangqianchun
 * @date : 2021-1-31 21:27
 * @description : 处理申请dto
 * @version : v1.0
 */
@ApiModel("处理申请dto")
@Data
public class ApplyDealDTO {

    @ApiModelProperty("申请编号")
    private Long appId;

    @ApiModelProperty("成绩编号")
    private Long scoId;

    @ApiModelProperty("审批状态")
    private Integer status;

    @ApiModelProperty("修改分数")
    private Double applyGrade;
}
