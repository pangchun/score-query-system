package com.bs.service;


import com.bs.model.ExamInfo;
import com.bs.model.Subject;
import com.bs.model.User;
import com.bs.model.vo.ExamInfoVO;
import com.bs.model.vo.SubjectIdNameVO;
import com.bs.support.common.CommonResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExamService {

    CommonResult<List<SubjectIdNameVO>> selectDownBox(String username);

    Page<ExamInfoVO> selectAllExamInfo(ExamInfoVO examInfoVO, Integer pageNum, Integer pageSize);
    
    //添加考试信息

    List<Subject> findTeacherCourse(User user);

    int addexamInfo(ExamInfo examInfo);

    int updateExamInfo(ExamInfo examInfo);

    int delExamInfo(ExamInfo examInfo);
}
