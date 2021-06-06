package com.bs.model.vo;

import com.bs.model.ExamInfo;
import com.bs.model.Subject;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamInfoVO extends ExamInfo {
    private String subName;
    private String realName;
    private String teacherName;
    @ApiModelProperty("考试编号")
    private Integer subId;
    @ApiModelProperty("考试编号")
    private Integer id;
    @ApiModelProperty("考试名称")
    private String name;
    @ApiModelProperty("考试课程")
    private int courseId;
    @ApiModelProperty("考试类型")
    private String type;
    @ApiModelProperty("考试时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;

}
