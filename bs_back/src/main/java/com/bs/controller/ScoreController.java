package com.bs.controller;

import com.bs.model.dto.ApplicationDTO;
import com.bs.model.dto.ApplyDealDTO;
import com.bs.model.dto.ScoreQuery;
import com.bs.model.dto.UpdateDTO;
import com.bs.model.vo.ApplicationVO;
import com.bs.model.vo.ScoreVO;
import com.bs.model.vo.TeacherVO;
import com.bs.service.ScoreService;
import com.bs.support.common.CommonResult;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 成绩API
 *
 * @author : zhangqianchun
 * @date : 2020-1-19 13:34
 * @description : 记录：使用了@ApiModel注解的实体如果未被used的时候，在Swagger界面是看不到该实体的；
 * @version : v1.0
 */
@Api(tags = "成绩API")
@RestController
@RequestMapping("/score")
@Slf4j
public class ScoreController {

    @Resource
    ScoreService scoreService;

    @ApiOperation("成绩-管理-学生")
    @PreAuthorize("hasAnyAuthority('wx:socre:query')")
    @GetMapping("/query")
    public CommonResult<List<ScoreVO>> query(String username) {

        log.info("学生账户名：" + username);

        return scoreService.findAllScoreByUserName(username);
    }

    @ApiOperation("成绩-管理-管理员")
    @PreAuthorize("hasAnyAuthority('wx:socre:dynamic')")
    @GetMapping("/dynamic")
    public CommonResult<Page<ScoreVO>> dynamic(ScoreQuery query) {

        log.info("查询条件：" + query.toString());

        return scoreService.findByDynamic(query);
    }

    @ApiOperation("成绩-管理-教师")
    @PreAuthorize("hasAnyAuthority('wx:socre:dynamic-tea')")
    @GetMapping("/dynamic-tea")
    public CommonResult<Page<ScoreVO>> dynamic(ScoreQuery query, String teaUserName) {

        log.info("查询条件：" + query.toString() + "/n");
        log.info("教师账户：" + teaUserName);

        return scoreService.findByDynamic(query, teaUserName);
    }

    @ApiOperation("成绩-修改")
    @PreAuthorize("hasAnyAuthority('wx:socre:update')")
    @PutMapping("/update")
    public CommonResult<Integer> update(@RequestBody UpdateDTO updateDTO) {

        log.info("修改成绩dto：" + updateDTO.toString());

        return scoreService.update(updateDTO);

    }

    @ApiOperation("成绩-一键导入")
    @PreAuthorize("hasAnyAuthority('wx:socre:editBatch')")
    @PostMapping("/editBatch")
    public CommonResult<Object> update(MultipartFile file) {

        log.info("上传文件名：" + file.getOriginalFilename());

        return scoreService.patchEdit(file);

    }

    @ApiOperation("成绩-重置")
    @PreAuthorize("hasAnyAuthority('wx:socre:resetBatch')")
    @PutMapping("/resetBatch")
    public CommonResult<Integer> resetBatch(@RequestBody String[] data) {

        log.info("所有id参数：" + Arrays.toString(data));

        return scoreService.resetBatch(data);

    }

    @ApiOperation("成绩-教师详情")
    @PreAuthorize("hasAnyAuthority('wx:socre:tea-detail')")
    @GetMapping("/tea-detail")
    public CommonResult<TeacherVO> detail(Long id) {

        log.info("教师Id：" + id);

        return scoreService.detail(id);
    }

    @ApiOperation("成绩-截图上传")
    @PreAuthorize("hasAnyAuthority('wx:socre:imgUp')")
    @PostMapping("/imgUp")
    public CommonResult<String> imgUp(MultipartFile file, HttpServletRequest req) {

        log.info("截图名：" + file.getOriginalFilename());

        return scoreService.imgUp(file, req);
    }

    @ApiOperation("申请-发起")
    @PreAuthorize("hasAnyAuthority('wx:socre:apply')")
    @PostMapping("/apply")
    public CommonResult<Integer> apply(@RequestBody ApplicationDTO applicationDTO) {

        log.info("申请dto：" + applicationDTO.toString());

        return scoreService.apply(applicationDTO);

    }

    @ApiOperation("申请-存在判断")
    @PreAuthorize("hasAnyAuthority('wx:socre:applyExist')")
    @GetMapping("/applyExist")
    public CommonResult<Object> applyExist(Long scoId) {

        log.info("成绩id：" + scoId);

        return scoreService.applyExist(scoId);

    }

    @ApiOperation("申请-列表-教师")
    @PreAuthorize("hasAnyAuthority('wx:socre:applications-tea')")
    @GetMapping("/applications-tea")
    public CommonResult<Page<ApplicationVO>> applications(String teaUserName, Integer page, Integer limit) {

        log.info("教师账户：" + teaUserName);

        return scoreService.applicationsForTea(teaUserName, page, limit);
    }

    @ApiOperation("申请-列表-学生")
    @PreAuthorize("hasAnyAuthority('wx:socre:applications-stu')")
    @GetMapping("/applications-stu")
    public CommonResult<List<ApplicationVO>> applications(String stuUserName) {

        log.info("学生账户：" + stuUserName);

        return scoreService.applicationsForStu(stuUserName);
    }

    @ApiOperation("申请-处理")
    @PreAuthorize("hasAnyAuthority('wx:socre:apply-deal')")
    @PutMapping("/apply-deal")
    public CommonResult<String> deal(@RequestBody ApplyDealDTO dealDTO) {

        log.info("处理dto：" + dealDTO.toString());

        return scoreService.deal(dealDTO);

    }

    @ApiOperation("申请-撤销")
    @PreAuthorize("hasAnyAuthority('wx:socre:fallBackApply')")
    @DeleteMapping("/fallBackApply/{appId}")
    public CommonResult<Integer> fallBack(@PathVariable("appId") Long appId) {

        log.info("appId：" + appId);

        return scoreService.fallBack(appId);

    }
}
