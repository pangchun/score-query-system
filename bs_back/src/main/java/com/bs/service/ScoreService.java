package com.bs.service;

import cn.hutool.core.collection.CollectionUtil;
import com.bs.dao.ScoreDao;
import com.bs.model.Application;
import com.bs.model.Score;
import com.bs.model.User;
import com.bs.model.dto.*;
import com.bs.model.vo.ApplicationVO;
import com.bs.model.vo.ScoreVO;
import com.bs.model.vo.TeacherVO;
import com.bs.support.common.CommonResult;
import com.bs.support.converter.ScoreConverter;
import com.bs.support.exception.ServiceException;
import com.bs.support.exception.ServiceExceptionCode;
import com.bs.support.util.AssertUtils;
import com.bs.support.util.BasePathUtils;
import com.bs.support.util.FileUtils;
import com.bs.support.util.excelUtils.ReadExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 成绩Service
 *
 * @author : zhangqianchun
 * @date : 2020-1-20 9:10
 * @description : 成绩Service
 * @version : v1.0
 */
@Service
@Slf4j
public class ScoreService {

    @Resource
    ScoreDao scoreDao;

/* ##################################################################################################################################################################################### */

    /**
     *学生成绩查询
     *
     * @param username 账户名
     * @return 学生成绩
     */
    public CommonResult<List<ScoreVO>> findAllScoreByUserName(String username) {

        //参数校验
        AssertUtils.notNull(username, ServiceExceptionCode.PARAM_ERROR, "请输入账户名");

        //获取数据
        List<Score> scoreList = scoreDao.findAllScoreByUserName(username);

        //构造ScoreVO
        List<ScoreVO> voList = Helper.toScoreVOList(scoreList);

        //封装返回值
        CommonResult<List<ScoreVO>> result;

        if (!voList.isEmpty()) {
            result = CommonResult.success(voList, "成功查询学生成绩");
        }
        else {
            result = CommonResult.failed("相关成绩暂未公布，请于发布通知后查询");
        }

        return result;

    }

/* ##################################################################################################################################################################################### */

    /**
     * 动态条件查询 - 管理员
     *
     * @param query 查询条件
     * @return 学生成绩
     */
    public CommonResult<Page<ScoreVO>> findByDynamic(ScoreQuery query) {

        //参数校验
        Helper.checkPageParam(query.getPage(), query.getLimit());

        //去除空格
        Helper.trimQueryParam(query);

        //分页 - 注意：分页必须在执行sql语句之前设置，否则不会生效
        Page<ScoreVO> voPage = PageMethod.startPage(query.getPage(), query.getLimit());

        //获取数据
        List<ScoreVO> voList = Helper.toScoreVOList(scoreDao.findByDynamic(query));

        //要先清除page中装的Score，然后再将ScoreVO装进去
        voPage.clear();
        voPage.addAll(voList);

        log.info("打印分页信息： " + voPage.toString());

        //封装返回值
        CommonResult<Page<ScoreVO>> result;
        result = CommonResult.success(voPage, voPage.getTotal());

        return result;
    }

/* ##################################################################################################################################################################################### */

    /**
     * 动态条件查询 - 教师
     *
     * @param query 查询条件
     * @param username 教师账户
     * @return 学生成绩
     */
    public CommonResult<Page<ScoreVO>> findByDynamic(ScoreQuery query, String username) {

        //参数校验
        Helper.checkPageParam(query.getPage(), query.getLimit());
        AssertUtils.notNull(username, ServiceExceptionCode.PARAM_ERROR, "请传入用户名");

        //获取教师Id
        String teaId = scoreDao.findIdByUsername(username);

        if (StringUtil.isNotEmpty(teaId)){
            query.setTeaId(teaId);
        }
        else {
            return CommonResult.failed("未查询到该教师");
        }

        //去除空格
        Helper.trimQueryParam(query);

        //分页 - 注意：分页必须在执行sql语句之前设置，否则不会生效
        Page<ScoreVO> voPage = PageMethod.startPage(query.getPage(), query.getLimit());

        //获取数据
        List<ScoreVO> voList = Helper.toScoreVOList(scoreDao.findByDynamic(query));

        //要先清除page中装的Score，然后再将ScoreVO装进去
        voPage.clear();
        voPage.addAll(voList);

        log.info("打印分页信息： " + voPage.toString());

        //封装返回值
        CommonResult<Page<ScoreVO>> result;
        result = CommonResult.success(voPage, voPage.getTotal());

        return result;

    }

/* ##################################################################################################################################################################################### */

    /**
     * 修改成绩
     *
     * @param dto 修改dto
     * @return 修改结果
     */
    public CommonResult<Integer> update(UpdateDTO dto) {

        //参数校验
        Helper.checkUpdateParam(dto);

        int i = scoreDao.updateScore(dto);

        if (i>0) {
            return CommonResult.success(i, "修改成功");
        }
        return CommonResult.failed("修改失败, 请联系管理员");
    }

/* ##################################################################################################################################################################################### */

    Boolean gradeIsIllegal = false;
    Boolean idIsNull = false;

    /**
     * 批量新增成绩
     *
     * @param file 学生成绩excel
     */
    public CommonResult<Object> patchEdit(MultipartFile file) {

        //上传文件
        File dest = FileUtils.upUtil(file);

        //解析excel
        try {
            ReadExcel.simpleRead(dest.getAbsolutePath(), this);
        }
        catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("数据解析错误，请检查您上传的excel格式或内容是否有误");
        }

        //excel数据校验
        if (!CollectionUtils.isEmpty(excelData)) {

            //科目set
            Set<Integer> subIds = excelData.stream().filter(e -> e.getSubId() != null).map(BatchEditDTO::getSubId).collect(Collectors.toSet());

            //科目总分map
            List<HashMap<String, Object>> list = scoreDao.findBySubIdSet(subIds);
            Map<Object, Object> map = Helper.getSubIdMap(list);

            log.info("各个科目总分：" + map.toString());

            excelData.forEach(e -> {
                Double grade = e.getGrade();
                Double subGrade = (Double) map.get(e.getSubId());
                if (grade < 0 || grade > subGrade) {
                    gradeIsIllegal = true;
                }

                if (       e.getId() == null
                        || e.getGrade() == null
                        || e.getSubId() == null
                        || e.getStuName() == null
                        || e.getStuUserName() == null
                        || e.getSubGrade() == null
                        || e.getSubName() == null
                        || e.getSubPass() == null
                        || e.getTeaId() == null
                        || e.getTeaName() == null
                ) {
                    idIsNull = true;
                }
            });

            //检验成绩编号是否录入
            if (idIsNull) {
                idIsNull = false;
                return CommonResult.failed("请检查表格是否有未填项，否则无法解析成绩");
            }

            //检验成绩是否存在小于0或者大于满分
            if (gradeIsIllegal) {
                gradeIsIllegal = false;
                return CommonResult.failed("数据解析错误，请检查文件中的学生成绩是否存在小于零或大于满分");
            }
        }

        //批量修改
        int i = scoreDao.updateBatch(excelData);

        return CommonResult.success(this.excelData, "成功导入" + i + "条数据");

    }

/* ##################################################################################################################################################################################### */

    List<BatchEditDTO> excelData = new ArrayList<>();

    /**
     * 保存解析的excel数据
     *
     * @param list 从ExcelReadListener解析得到的数据集合
     */
    public void saveExcelData(List<BatchEditDTO> list) {
        excelData = list;
    }

/* ##################################################################################################################################################################################### */

    /**
     * 批量重置
     *
     * @param data id数组
     * @return 通用返回结果
     */
    public CommonResult<Integer> resetBatch(@RequestBody String[] data) {

        //参数校验
        AssertUtils.notNull(data, ServiceExceptionCode.PARAM_ERROR, "请传入要修改的成绩id集合");

        //转为List<Long>
        ArrayList<Long> list = new ArrayList<>();
        for (String s : data) {
            list.add(Long.valueOf(s));
        }

        //返回重置条数
        int resetNum = scoreDao.resetBatch(list);

        return CommonResult.success(resetNum, "成功重置" + resetNum + "条记录");
    }

/* ##################################################################################################################################################################################### */

    /**
     *
     * @param id
     * @return
     */
    public CommonResult<TeacherVO> detail(Long id) {

        //参数校验
        AssertUtils.notNull(id, ServiceExceptionCode.PARAM_ERROR, "请传入教师Id");

        //获取User
        User user = scoreDao.findUserById(id);

        //转为UserVO
        TeacherVO teacherVO = ScoreConverter.SCORE_CONVERTER.toTeaVO(user);

        if (user != null) {
            return CommonResult.success(teacherVO, "成功获取教师信息");
        }
        else {
            return CommonResult.failed("获取教师信息失败");
        }

    }

/* ##################################################################################################################################################################################### */

    /**
     * 获取上传图片的访问路径
     *
     * @param file 图片文件
     * @param req 请求
     * @return 可访问路径
     */
    public CommonResult<String> imgUp(MultipartFile file, HttpServletRequest req) {

        File img = FileUtils.upUtil(file);

        String basePath = BasePathUtils.getBasePath(req);

        String path =basePath + "fileupload/" + img.getName();

        return CommonResult.success(path,"图片上传成功");

    }

/* ##################################################################################################################################################################################### */

    /**
     * 新增申请
     *
     * @param dto 申请dto
     * @return 通用返回结果
     */
    public CommonResult<Integer> apply(ApplicationDTO dto) {

        //参数校验
        Helper.checkApplyParam(dto);

        //新增申请
        int num = scoreDao.addApplication(dto);

        return CommonResult.success(num, "成功添加" + num + "条申请");
    }

/* ##################################################################################################################################################################################### */

    /**
     * 判断状态为待处理的记录是否存在
     *
     * @param scoId 成绩id
     * @return 通用响应结果
     */
    public CommonResult<Object> applyExist(Long scoId) {

        //参数校验
        AssertUtils.notNull(scoId, ServiceExceptionCode.PARAM_ERROR, "请输入成绩id");

        Application application = scoreDao.findApplicationByScoId(scoId);

        if (application == null) {
            return CommonResult.success(null,"该条记录未发起过申请");
        }

        return CommonResult.success(application, "该条记录已经发起过申请");
    }

/* ##################################################################################################################################################################################### */

    /**
     * 获取申请列表
     *
     * @param username 教师账户
     * @param page 页码
     * @param limit 每页条数
     * @return 申请列表
     */
    public CommonResult<Page<ApplicationVO>> applicationsForTea(String username, Integer page, Integer limit) {

        //参数校验
        AssertUtils.notNull(username, ServiceExceptionCode.PARAM_ERROR, "请输入用户账号");
        Helper.checkPageParam(page, limit);

        //分页
        Page<ApplicationVO> applicationPage = PageMethod.startPage(page, limit);

        //获取数据
        Long teaId = Long.valueOf(scoreDao.findIdByUsername(username));
        List<Application> list = scoreDao.findAllApplicationsByTeaId(teaId);

        if (CollectionUtils.isEmpty(list)) {
            return CommonResult.failed("暂无申请数据");
        }

        //转换VO
        List<ApplicationVO> voList = new ArrayList<>();
        list.forEach(e -> {
            ApplicationVO vo = ScoreConverter.SCORE_CONVERTER.toAppVO(e);
            vo.setStatus(e.getStatus() == 0? "待处理" : (e.getStatus() == 1? "已通过" : "已驳回"));
            voList.add(vo);
        });

        //设置page的值
        applicationPage.clear();
        applicationPage.addAll(voList);

        //封装返回值
        CommonResult<Page<ApplicationVO>> result;
        result = CommonResult.success(applicationPage, applicationPage.getTotal());

        return result;
    }

/* ##################################################################################################################################################################################### */

    /**
     * 处理审批
     *
     * @param dto 审批dto
     * @return 通用返回结果
     */
    @Transactional(rollbackFor = ServiceException.class)
    public CommonResult<String> deal(ApplyDealDTO dto) {

        //参数校验
        boolean dtoBoolean = Helper.checkApplyDealParam(dto);

        if (dtoBoolean) {
            //为true时为同意审批

            int changeNum = scoreDao.updateStatusByAppId(dto.getAppId(), dto.getStatus());
            if (changeNum > 0) {
                int num = scoreDao.updateScoreByScoId(dto.getScoId(), dto.getApplyGrade());
                if (num > 0) {
                    return CommonResult.success(null,"您已同意该条申请");
                }
                else {
                    throw new ServiceException(ServiceExceptionCode.SYSTEM_ERROR, "系统异常,请联系开发人员");
                }
            }
            else {
                return CommonResult.failed("审批失败，请联系管理员");
            }
        }
        else {
            //为false时为驳回审批

            int changeNum = scoreDao.updateStatusByAppId(dto.getAppId(), dto.getStatus());
            if (changeNum > 0) {
                return CommonResult.success(null,"您已驳回该条申请");
            }
            else {
                return CommonResult.failed("审批失败，请联系管理员");
            }
        }
    }

/* ##################################################################################################################################################################################### */

    /**
     * 获取学生申请列表
     *
     * @param stuUserName 学生账户名
     * @return 申请列表
     */
    public CommonResult<List<ApplicationVO>> applicationsForStu(String stuUserName) {

        //参数校验
        AssertUtils.notNull(stuUserName, ServiceExceptionCode.PARAM_ERROR, "请输入用户账号");

        //获取数据
        List<Application> list = scoreDao.findAllApplicationsStuUserName(stuUserName);

        if (CollectionUtils.isEmpty(list)) {
            return CommonResult.failed("暂无申请数据");
        }

        //转换VO
        List<ApplicationVO> voList = new ArrayList<>();
        list.forEach(e -> {
            ApplicationVO vo = ScoreConverter.SCORE_CONVERTER.toAppVO(e);
            vo.setStatus(e.getStatus() == 0? "待处理" : (e.getStatus() == 1? "已通过" : "已驳回"));
            voList.add(vo);
        });

        //封装返回值
        CommonResult<List<ApplicationVO>> result;
        result = CommonResult.success(voList, "获取申请列表成功");

        return result;
    }

/* ##################################################################################################################################################################################### */

    public CommonResult<Integer> fallBack(Long appId) {

        //参数校验
        AssertUtils.notNull(appId, ServiceExceptionCode.PARAM_ERROR, "请输入申请编号");

        //撤销申请
        int num = scoreDao.deleteApplyById(appId);

        if (num > 0) {
            return CommonResult.success(num, "成功撤销申请");
        }
        else {
            return CommonResult.failed("撤销申请失败，请联系您的任课教师查看审批情况");
        }
    }

    /* ##################################################################################################################################################################################### */

    /**
     * 辅助类
     */
    private static class Helper {
        public static List<ScoreVO> toScoreVOList(List<Score> data) {

            ArrayList<ScoreVO> listVO = new ArrayList();

            data.forEach(item -> {

                ScoreVO vo = ScoreConverter.SCORE_CONVERTER.toScoreVO(item);

                if (item.getGrade() == null) {
                    vo.setGrade("未录入");
             }

                listVO.add(vo);
            });

            return listVO;
        }

        public static void trimQueryParam(ScoreQuery query) {

            query.setScoId(StringUtils.trimAllWhitespace(query.getScoId()));
            query.setSubId(StringUtils.trimAllWhitespace(query.getSubId()));
            query.setSubName(StringUtils.trimAllWhitespace(query.getSubName()));
            query.setTeaId(StringUtils.trimAllWhitespace(query.getTeaId()));
            query.setStuUserName(StringUtils.trimAllWhitespace(query.getStuUserName()));
            query.setStuName(StringUtils.trimAllWhitespace(query.getStuName()));
        }

        public static void checkPageParam(Integer page, Integer limit) {

            AssertUtils.notNull(page, ServiceExceptionCode.PARAM_ERROR, "请输入页码");
            AssertUtils.notNull(limit, ServiceExceptionCode.PARAM_ERROR, "请输入每页最大记录条数");
        }

        public static void checkUpdateParam(UpdateDTO dto) {

            AssertUtils.notNull(dto.getScoId(), ServiceExceptionCode.PARAM_ERROR, "请输入成绩Id");
            AssertUtils.notNull(dto.getGrade(), ServiceExceptionCode.PARAM_ERROR, "请输入学生成绩");
            AssertUtils.notNull(dto.getStuUserName(), ServiceExceptionCode.PARAM_ERROR, "请输入学生账户");
            AssertUtils.notNull(dto.getSubId(), ServiceExceptionCode.PARAM_ERROR, "请输入科目Id");
            AssertUtils.notNull(dto.getSubGrade(), ServiceExceptionCode.PARAM_ERROR, "请输入科目总分");
        }

        /**
         * list转map工具
         *
         * @param list list
         * @return map
         */
        public static Map<Object, Object> getSubIdMap(List<HashMap<String,Object>> list) {

            HashMap<Object, Object> map = new HashMap<>(1000);

            if (list != null && !CollectionUtils.isEmpty(list)) {
                for (HashMap<String, Object> item : list) {
                    Object key = null;
                    Object value = null;
                    for (Map.Entry<String, Object> entry : item.entrySet()) {
                        if (entry.getKey().equals("id")) {
                            key = entry.getValue();
                        }
                        else if (entry.getKey().equals("sub_grade")) {
                            value = entry.getValue();
                        }
                    }
                    map.put(key, value);
                }
            }

            return map;
        }

        public static void checkApplyParam(ApplicationDTO dto) {

            AssertUtils.notNull(dto.getGrade(), ServiceExceptionCode.PARAM_ERROR, "请输入学生得分");
            AssertUtils.notNull(dto.getScoId(), ServiceExceptionCode.PARAM_ERROR, "请输入成绩id");
            AssertUtils.notNull(dto.getStuName(), ServiceExceptionCode.PARAM_ERROR, "请输入学生姓名");
            AssertUtils.notNull(dto.getStuUserName(), ServiceExceptionCode.PARAM_ERROR, "请输入学生账户");
            AssertUtils.notNull(dto.getSubGrade(), ServiceExceptionCode.PARAM_ERROR, "请输入科目总分");
            AssertUtils.notNull(dto.getSubId(), ServiceExceptionCode.PARAM_ERROR, "请输入科目id");
            AssertUtils.notNull(dto.getTeaId(), ServiceExceptionCode.PARAM_ERROR, "请输入教师id");
            AssertUtils.notNull(dto.getTeaName(), ServiceExceptionCode.PARAM_ERROR, "请输入教师姓名");
            AssertUtils.notNull(dto.getApplyGrade(), ServiceExceptionCode.PARAM_ERROR, "请输入修改的成绩");
            AssertUtils.notNull(dto.getApplyDescription(), ServiceExceptionCode.PARAM_ERROR, "请输入申请描述");
            AssertUtils.notNull(dto.getImgUri(), ServiceExceptionCode.PARAM_ERROR, "请输入图片地址");


        }

        public static boolean checkApplyDealParam(ApplyDealDTO dto) {

            AssertUtils.notNull(dto.getAppId(), ServiceExceptionCode.PARAM_ERROR, "请输入申请编号");
            AssertUtils.notNull(dto.getStatus(), ServiceExceptionCode.PARAM_ERROR, "请输入审批状态");
            if (dto.getScoId() == null && dto.getApplyGrade() == null) {
                 return false;
            }
            else {
                AssertUtils.notNull(dto.getScoId(), ServiceExceptionCode.PARAM_ERROR, "请输入申请编号");
                AssertUtils.notNull(dto.getApplyGrade(), ServiceExceptionCode.PARAM_ERROR, "请输入审批状态");
                return true;
            }
        }

    }
}
