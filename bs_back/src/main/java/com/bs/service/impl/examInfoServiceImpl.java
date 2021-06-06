package com.bs.service.impl;

import com.bs.dao.ExamInfoDao;
import com.bs.model.ExamInfo;
import com.bs.model.Subject;
import com.bs.model.User;
import com.bs.model.vo.ExamInfoVO;
import com.bs.model.vo.SubjectIdNameVO;
import com.bs.service.ExamService;
import com.bs.support.common.CommonResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class examInfoServiceImpl implements ExamService {

    @Resource
    ExamInfoDao examInfoDao;
    //查询所有下拉窗口下的课程
    public CommonResult<List<SubjectIdNameVO>> selectDownBox(String username) {
        //获取数据
        List<SubjectIdNameVO> idNameVOList = examInfoDao.selectDownBox(username);
        //封装返回值
        CommonResult<List<SubjectIdNameVO>> result;

        if (!idNameVOList.isEmpty()) {
            result = CommonResult.success(idNameVOList, "成功");
        }
        else {
            result = CommonResult.failed("失败");
        }
        return result;
    }
    //查询所有考试信息
    @Override
    public Page<ExamInfoVO> selectAllExamInfo(ExamInfoVO examInfoVO, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        Page<ExamInfoVO> examInfoVOs =examInfoDao.selectAllExamInfo(examInfoVO);
        return examInfoVOs;
    }
    @Override
    public List<Subject> findTeacherCourse(User user) {
        return examInfoDao.findTeacherCourse(user);
    }
    //新增考试信息
    @Override
    public int addexamInfo(ExamInfo examInfo) {
        return examInfoDao.addexamInfo(examInfo);
    }

    @Override
    public int updateExamInfo(ExamInfo examInfo) {
        return examInfoDao.updateExamInfo(examInfo);
    }

    @Override
    public int delExamInfo(ExamInfo examInfo) {
        return examInfoDao.delExamInfo(examInfo);
    }
}
