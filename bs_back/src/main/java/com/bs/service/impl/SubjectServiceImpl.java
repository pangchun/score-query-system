package com.bs.service.impl;

import com.bs.dao.SubjectDao;
import com.bs.model.Score;
import com.bs.model.Subject;
import com.bs.model.User;
import com.bs.model.vo.ScoreVO;
import com.bs.model.vo.SubjectVo;
import com.bs.service.SubjectService;
import com.bs.service.UserService;
import com.bs.support.util.GetUserNameByToken;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectDao subjectDao;
    @Resource
    HttpServletRequest request;
    @Autowired
    GetUserNameByToken getUserNameByToken;
    @Autowired
    private UserService userService;

    @Override
    public Page<SubjectVo> selectAllSubject(SubjectVo subject, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        Page<SubjectVo> subjects = subjectDao.selectAllSubject(subject);
        return subjects;
    }

    @Override
    public int updateSubject(SubjectVo subject) {
        //查询科目详情：用于判断增加选课数量是否合理
        SubjectVo subject1=subjectDao.selectObjectDetail(subject);
        //计算重新设定的选课人数与原来设定的选课人数的差值
        int value=subject.getSubTotalCount()-subject1.getSubTotalCount();
        //原来的选课人数要大于修改后的总选课人数
        if(subject1.getSubTotalCount()-subject1.getSubNowCount()<=subject.getSubTotalCount()){
            return subjectDao.updateSubject(subject,value);
        }else {
            return 0;
        }
    }

    @Override
    public int addSubject(SubjectVo subject) {
        //增加科目之前需要判断科目编号是否重复
        //根据科目的编码进行判断
        SubjectVo subjectVo=subjectDao.selectSubject(subject);
        if (subjectVo==null){
            return subjectDao.addSubject(subject);
        }
        return 0;
    }

    @Override
    public boolean selectAllSubCode(SubjectVo subjectVo) {
        List<SubjectVo> list=subjectDao.selectAllSubCode(subjectVo);
        if(list==null||list.size()==0){
           return true;
        }
        return false;
    }

    @Override
    public int delSubject(SubjectVo subject) {
        return subjectDao.delSubject(subject);
    }

    @Override
    public Score isSelectedSubject(ScoreVO score) {
        return subjectDao.isSelectedSubject(score);
    }

    @Override
    public int studentSelectSubject(ScoreVO score) {
        return subjectDao.studentSelectSubject(score);
    }

    @Override
    public Subject selcetSubjectSum(SubjectVo subject) {
        return subjectDao.selcetSubjectSum(subject);
    }

    @Override
    public void subjectSumSub(SubjectVo subject) {
        subjectDao.subjectSumSub(subject);
    }

    @Override
    public Page<SubjectVo> studentIsSelectSubject(SubjectVo subject, Integer pageNum, Integer pageSize) {
        User user=userService.selectUserDetail(getUserNameByToken.getUserName(request));
        PageHelper.startPage(pageNum,pageSize,true);
        Page<SubjectVo> subjects = subjectDao.studentIsSelectSubject(subject,user.getUsername());
        return subjects;
    }

    @Override
    public int delIsSelectedSubject(ScoreVO score) {
        User user=userService.selectUserDetail(getUserNameByToken.getUserName(request));
        score.setStuUserName(user.getUsername());
        int result=subjectDao.delIsSelectedSubject(score);
        if(result>0){
            SubjectVo subjectVo=new SubjectVo();
            subjectVo.setNum(-1);
            subjectVo.setId(score.getSubId());
            subjectDao.subjectSumSub(subjectVo);
        }
        return result;
    }
}
