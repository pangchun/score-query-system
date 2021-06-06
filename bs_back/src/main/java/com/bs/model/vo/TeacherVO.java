package com.bs.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhangqianchun
 * @date : 2020-1-29 16:12
 * @description : 用户VO
 * @version : v1.0
 */
@ApiModel("教师VO")
@Data
public class TeacherVO {

    @ApiModelProperty("教师编号")
    private String teaId;

    @ApiModelProperty("教师姓名")
    private String teaName;

    @ApiModelProperty("联系电话")
    private String teaTel;

    @ApiModelProperty("邮箱地址")
    private String email;
}
