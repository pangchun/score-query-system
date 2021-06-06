package com.bs.dao;

import com.bs.model.ExamInfo;
import com.bs.model.Subject;
import com.bs.model.User;
import com.bs.model.vo.ExamInfoVO;
import com.bs.model.vo.SubjectIdNameVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamInfoDao {

    List<SubjectIdNameVO> selectDownBox(String username);

    Page<ExamInfoVO> selectAllExamInfo(ExamInfoVO examInfoVO);
    ExamInfoVO selectExamInfoDetail(ExamInfoVO examInfoVO);

    ExamInfoVO selectExamInfo(ExamInfoVO examInfoVO);
    List<Subject> findTeacherCourse(User user);

    int addexamInfo(ExamInfo examInfo);

    int updateExamInfo(ExamInfo examInfo);

    int delExamInfo(ExamInfo examInfo);
}
