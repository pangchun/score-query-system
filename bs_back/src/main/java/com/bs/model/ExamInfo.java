package com.bs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data//set/get方法
@AllArgsConstructor//全部有参构造
@NoArgsConstructor//无参构造
@Builder//链式编程
public class ExamInfo {
    @ApiModelProperty("考试编号")
    private Integer id;
    @ApiModelProperty("考试名称")
    private String name;
    @ApiModelProperty("考试课程")
    private int courseId;
    @ApiModelProperty("考试类型")
    private String type;
    @ApiModelProperty("考试时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;

}
