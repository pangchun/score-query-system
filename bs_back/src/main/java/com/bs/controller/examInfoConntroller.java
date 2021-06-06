package com.bs.controller;

import com.bs.model.ExamInfo;
import com.bs.model.Subject;
import com.bs.model.User;
import com.bs.model.vo.ExamInfoVO;
import com.bs.model.vo.SubjectIdNameVO;
import com.bs.model.vo.SubjectVo;
import com.bs.service.ExamService;
import com.bs.support.common.CommonResult;
import com.bs.support.util.JsonUtil;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/examInfo")
public class examInfoConntroller {

    @Resource
    ExamService examService;

    @GetMapping("/findSelectDown")
    public CommonResult<List<SubjectIdNameVO>> findselectDown(String username) {
        return examService.selectDownBox(username);
    }

    @GetMapping("/selectAllExamInfo")//data前端传给后端的json数据、@RequestParam(required = false)非必要、@RequestParam("page") 重命名，前端不是这个page名字，pageNum表示第几页，pageSize一页显示多少条
    public CommonResult selectAllExamInfo(@RequestParam(required = false) String data, @RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize){
        ExamInfoVO examInfoVO=null;
        if (data!=null){
            examInfoVO= JsonUtil.toObject(data,ExamInfoVO.class,String.class);
        }
        Page<ExamInfoVO> examInfo= examService.selectAllExamInfo(examInfoVO,pageNum,pageSize);
        return CommonResult.success(examInfo,(long)examInfo.getTotal());
    }
    @GetMapping("/findTeacherCourse")
    public CommonResult findTeacherCourse(String username) {
        User user = new User();
        user.setUsername(username);
        List list = examService.findTeacherCourse(user);
        return  CommonResult.success(list);
    }

    //新增考试信息
        @PostMapping("/addexamInfo")
    public CommonResult addexamInfo(@RequestBody ExamInfo examInfo) {
        int result=examService.addexamInfo(examInfo);

        if(result>0) {
            return CommonResult.success("新增成功！");
        }
        return CommonResult.failed("新增失败！");
    }
    //修改考试信息
    @PostMapping("/updateExamInfo")
    public CommonResult updateExamInfo(@RequestBody ExamInfo examInfo) {
        int result=examService.updateExamInfo(examInfo);

        if(result>0) {
            return CommonResult.success("修改成功！");
        }
        return CommonResult.failed("修改失败！");
    }

    //删除考试信息
    @PostMapping("/delExamInfo")
    public CommonResult delExamInfo(@RequestBody ExamInfo examInfo) {
        int result=examService.delExamInfo(examInfo);

        if(result>0) {
            return CommonResult.success("修改成功！");
        }
        return CommonResult.failed("修改失败！");
    }



}
