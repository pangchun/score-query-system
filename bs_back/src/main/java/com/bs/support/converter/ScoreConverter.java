package com.bs.support.converter;

import com.bs.model.Application;
import com.bs.model.Score;
import com.bs.model.User;
import com.bs.model.vo.ApplicationVO;
import com.bs.model.vo.ScoreVO;
import com.bs.model.vo.TeacherVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 成绩转换器
 *
 * @author : zhangqianchun
 * @date : 2021-1-19 22:05
 * @description : 用于复制实体到VO中
 * @version : v1.0
 */

@Mapper
public interface ScoreConverter {

    ScoreConverter SCORE_CONVERTER = Mappers.getMapper(ScoreConverter.class);

    /**
     *ScoreVO转换
     *
     * @param score
     * @return
     */
    @Mappings({
            @Mapping(target = "stuUserName", source = "student.username"),
            @Mapping(target = "stuName", source = "student.realName"),
            @Mapping(target = "subId", source = "subject.id"),
            @Mapping(target = "subName", source = "subject.subName"),
            @Mapping(target = "subGrade", source = "subject.subGrade"),
            @Mapping(target = "subPass", source = "subject.subPass"),
            @Mapping(target = "teaId", source = "subject.teacher.id"),
            @Mapping(target = "teaName", source = "subject.teacher.realName")
    })
    ScoreVO toScoreVO(Score score);

    @Mappings({
            @Mapping(target = "teaId", source = "id"),
            @Mapping(target = "teaName", source = "realName"),
            @Mapping(target = "teaTel", source = "phone"),
            @Mapping(target = "email", source = "email")
    })
    TeacherVO toTeaVO(User user);

    ApplicationVO toAppVO(Application application);



}
