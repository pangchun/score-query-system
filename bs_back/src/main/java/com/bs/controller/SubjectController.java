package com.bs.controller;

import com.bs.model.Score;
import com.bs.model.Subject;
import com.bs.model.User;
import com.bs.model.vo.ScoreVO;
import com.bs.model.vo.SubjectVo;
import com.bs.service.SubjectService;
import com.bs.service.UserService;
import com.bs.support.common.CommonResult;
import com.bs.support.util.GetUserNameByToken;
import com.bs.support.util.JsonUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 科目管理
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    GetUserNameByToken getUserNameByToken;
    @Autowired
    private UserService userService;

    /**
     * 分页/按条件查询全部科目
     */
    @PreAuthorize("hasAnyAuthority('wx:subject:selectAllSubject')")
    @GetMapping("/selectAllSubject")
    public CommonResult selectAllSubject(@RequestParam(required = false) String data, @RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize){
        SubjectVo subject=null;
        if (data!=null){
            subject= JsonUtil.toObject(data,SubjectVo.class,String.class);
        }
        Page<SubjectVo> subjects= subjectService.selectAllSubject(subject,pageNum,pageSize);
        return CommonResult.success(subjects,(long)subjects.getTotal());
    }

    /**
     * 修改学科内容
     */
    @PreAuthorize("hasAnyAuthority('wx:subject:updateSubject')")
    @RequestMapping("/updateSubject")
    public CommonResult updateSubject(@RequestBody SubjectVo subject){
        Map<String,Object> map=new HashMap<String, Object>();
        //首先需要判断登录的人是谁，对应老师只能修改自己的科目
        //根据登录信息查询用户姓名
        User user=userService.selectUserDetail(getUserNameByToken.getUserName(request));
        if(user.getRealName().equals(subject.getTeacherName())){
            //修改的时候科目编码不能和已有编码重复
            if(subjectService.selectAllSubCode(subject)){
                if(subjectService.updateSubject(subject)>0){
                    map.put("success",true);
                    map.put("message","修改成功");
                }else {
                    map.put("success",false);
                    map.put("message","修改失败");
                }
            }else {
                map.put("success",false);
                map.put("message","科目编码重复");
            }
        }else {
            map.put("success",false);
            map.put("message","只能修改自己发布的科目");
        }
        return CommonResult.success(map);
    }

    /**
     * 新增学科
     */
    @PreAuthorize("hasAnyAuthority('wx:subject:addSubject')")
    @PostMapping("/addSubject")
    public CommonResult addSubject(@RequestBody SubjectVo subject){
        User user=userService.selectUserDetail(getUserNameByToken.getUserName(request));
        subject.setTeacherId(user.getId());
        Map<String,Object> map=new HashMap<String, Object>();
        if(user.getRealName().equals(subject.getTeacherName())){
            if(subjectService.addSubject(subject)>0){
                map.put("success",true);
                map.put("message","添加成功");
            }else {
                map.put("success",false);
                map.put("message","该科目编码已经存在");
            }
        }else {
            map.put("success",false);
            map.put("message","老师名称错误");
        }
        return CommonResult.success(map);
    }

    /**
     * 删除科目
     * @param subject
     * @return
     */
    @PreAuthorize("hasAnyAuthority('wx:subject:delSubject')")
    @PostMapping("/delSubject")
    public CommonResult delSubject(@RequestBody SubjectVo subject){
        User user=userService.selectUserDetail(getUserNameByToken.getUserName(request));
        subject.setTeacherId(user.getId());
        Map<String,Object> map=new HashMap<String, Object>();
        //不能删除别人发布的科目
        if(user.getRealName().equals(subject.getTeacherName())){
            int result=subjectService.delSubject(subject);
            if(result>0){
                map.put("success",true);
                map.put("message","删除成功");
            }else {
                map.put("success",false);
                map.put("message","删除失败");
            }
        }else {
            map.put("success",false);
            map.put("message","不能删除其他老师的科目");
        }
        return CommonResult.success(map);
    }

    //学生选课
    @PostMapping("/studentSelectSubject")
    public CommonResult studentSelectSubject(@RequestBody SubjectVo subject){
        //用户信息
        User user=userService.selectUserDetail(getUserNameByToken.getUserName(request));
        //去查询成绩表中是否有这一条数据——>看是否选课
        ScoreVO score=new ScoreVO();
        score.setStuUserName(user.getUsername());
        score.setSubId(subject.getId());
        Score score1=subjectService.isSelectedSubject(score);
        Map<String,Object> map=new HashMap<String, Object>();
        //已经选择了该课程
        if(score1!=null){
            map.put("success",false);
            map.put("message","已经选择了该课程");
        }else {
            Subject subject1=subjectService.selcetSubjectSum(subject);
            if(subject1.getSubNowCount()>0){
                int result=subjectService.studentSelectSubject(score);
                subject.setNum(1);
                subjectService.subjectSumSub(subject);
                if(result>0){
                    map.put("success",true);
                    map.put("message","选课成功");
                }
            }else {
                map.put("success",false);
                map.put("message","选课失败");
            }
        }
        return CommonResult.success(map);
    }

    //查询学生已选择的课程
    @GetMapping("/studentIsSelectSubject")
    public CommonResult studentIsSelectSubject(@RequestParam(required = false) String data, @RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize){
        SubjectVo subject=null;
        if (data!=null){
            subject= JsonUtil.toObject(data,SubjectVo.class,String.class);
        }
        Page<SubjectVo> subjects= subjectService.studentIsSelectSubject(subject,pageNum,pageSize);
        return CommonResult.success(subjects,(long)subjects.getTotal());
    }

//    删除已选的课程
    @PostMapping("delIsSelectedSubject")
    public CommonResult delIsSelectedSubject(@RequestBody SubjectVo subjectVo){
        ScoreVO score=new ScoreVO();
        score.setSubId(subjectVo.getId());
        score.setSubName(subjectVo.getSubName());
        int result=subjectService.delIsSelectedSubject(score);
        if(result>0){
            return CommonResult.success("删除成功");
        }
        return CommonResult.success("删除失败");
    }
}
