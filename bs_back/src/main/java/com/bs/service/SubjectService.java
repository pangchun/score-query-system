package com.bs.service;

import com.bs.model.Score;
import com.bs.model.Subject;
import com.bs.model.vo.ScoreVO;
import com.bs.model.vo.SubjectVo;
import com.github.pagehelper.Page;


public interface SubjectService {
    Page<SubjectVo> selectAllSubject(SubjectVo subject, Integer pageNum, Integer pageSize);

    int updateSubject(SubjectVo subject);

    int addSubject(SubjectVo subject);

    boolean selectAllSubCode(SubjectVo subjectVo);

    int delSubject(SubjectVo subject);

    Score isSelectedSubject(ScoreVO score);

    int studentSelectSubject(ScoreVO score);

    Subject selcetSubjectSum(SubjectVo subject);

    void subjectSumSub(SubjectVo subject);

    Page<SubjectVo> studentIsSelectSubject(SubjectVo subject, Integer pageNum, Integer pageSize);

    int delIsSelectedSubject(ScoreVO score);
}
