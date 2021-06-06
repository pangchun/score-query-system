package com.bs.model.vo;

import com.bs.model.Subject;
import com.bs.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectVo extends Subject {
    private Integer teacherId;
    private String teacherName;

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

    int num;

}
