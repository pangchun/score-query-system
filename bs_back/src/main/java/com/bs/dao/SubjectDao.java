package com.bs.dao;

import com.bs.model.Score;
import com.bs.model.Subject;
import com.bs.model.vo.ScoreVO;
import com.bs.model.vo.SubjectVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SubjectDao {
    Page<SubjectVo> selectAllSubject(SubjectVo subject);

    int updateSubject(@Param("subject") SubjectVo subject, @Param("value") int value);

    SubjectVo selectObjectDetail(Subject subject);

    SubjectVo selectSubject(SubjectVo subject);

    int addSubject(SubjectVo subject);

    List<SubjectVo> selectAllSubCode(SubjectVo subjectVo);

    int delSubject(SubjectVo subject);

    Score isSelectedSubject(ScoreVO score);

    int studentSelectSubject(ScoreVO score);

    Subject selcetSubjectSum(SubjectVo subject);

    void subjectSumSub(SubjectVo subject);

    Page<SubjectVo> studentIsSelectSubject(@Param("subject") SubjectVo subject,@Param("username") String username);

    int delIsSelectedSubject(ScoreVO score);
}
