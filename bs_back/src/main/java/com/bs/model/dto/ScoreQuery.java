package com.bs.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 条件查询query
 *
 * @author : zhangqianchun
 * @date : 2020-1-23 13:58
 * @description : 成绩管理条件查询query
 * @version : v1.0
 */
@ApiModel("条件查询query")
@Data
public class ScoreQuery {

    @ApiModelProperty("成绩编号 - 精确搜索")
    private String scoId;

    @ApiModelProperty("科目编号 - 精确搜索")
    private String subId;

    /**
     * 科目名搜索如果做成下拉框，当科目非常多的时候体验不好；
     */
    @ApiModelProperty("科目名 - 模糊搜索")
    private String subName;

    @ApiModelProperty("是否及格、未录入、全部 - 精确搜索")
    private String isPassed;

    @ApiModelProperty("教师编号 - 精确搜索")
    private String teaId;

    @ApiModelProperty("学生账户 - 模糊搜索")
    private String stuUserName;

    @ApiModelProperty("学生名 - 模糊搜索")
    private String stuName;

    /* 分页信息 */

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页条数")
    private Integer limit;

}
