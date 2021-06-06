package com.bs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 成绩实体类
 *
 * @author : zhangqianchun
 * @version : v1.0
 * @date : 2020-1-18 11:59
 * @description : 成绩实体类
 */
@ApiModel("成绩实体")
@Data
public class Score {

    @ApiModelProperty("成绩id")
    private Long id;

    @ApiModelProperty("学生用户")
    private User student;

    @ApiModelProperty("科目")
    private Subject subject;

    @ApiModelProperty("学生成绩")
    private Double grade;

    @Override
    public String toString() {
        return "Score{" +
                "成绩编号: " + id +
                ", 学生账号: " + student.getUsername() +
                ", 学生姓名: " + student.getRealName() +
                ", 科目: " + subject.getSubName() +
                ", 科目总分: " + subject.getSubGrade() +
                ", 学生成绩: " + grade +
                '}';
    }
}
