package com.bs.dao;

import com.bs.model.Application;
import com.bs.model.Score;
import com.bs.model.User;
import com.bs.model.dto.ApplicationDTO;
import com.bs.model.dto.BatchEditDTO;
import com.bs.model.dto.ScoreQuery;
import com.bs.model.dto.UpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 成绩Dao
 *
 * @author : zhangqianchun
 * @date : 2020-1-18 13:16
 * @description : 成绩Dao
 * @version : v1.0
 */
@Mapper
@Repository
public interface ScoreDao {

    /**
     * 成绩查询 - 学生查询成绩
     *
     * @param username 用户账号
     * @return 学生成绩
     */
    List<Score> findAllScoreByUserName(String username);

    /**
     * 成绩管理 - 动态条件查询
     *
     * @param scoreQuery 查询条件
     * @return 满足条件的成绩
     */
    List<Score> findByDynamic(ScoreQuery scoreQuery);

    /**
     * 按username查询Id
     *
     * @param username 账户
     * @return 用户Id
     */
    String findIdByUsername(String username);

    /**
     * 修改学生成绩
     *
     * @param dto 修改参数dto
     * @return 更新的记录条数
     */
    int updateScore(UpdateDTO dto);

    /**
     * 查询科目总分
     *
     * @param idSet 科目id集合
     * @return 科目id与总分的map集合
     */
    List<HashMap<String,Object>> findBySubIdSet(Set<Integer> idSet);

    /**
     * 批量修改成绩
     *
     * @param list 导入的excel数据
     * @return 修改条数
     */
    int updateBatch(List<BatchEditDTO> list);

    /**
     * 批量重置成绩
     *
     * @param list idList
     * @return 重置条数
     */
    int resetBatch(List<Long> list);

    /**
     * 按id查询用户
     *
     * @param id 用户id
     * @return 用户
     */
    User findUserById(Long id);

    /**
     * 新增申请
     *
     * @param dto 申请dto
     * @return 新增条数
     */
    int addApplication(ApplicationDTO dto);

    /**
     * 按成绩id查询申请
     *
     * @param scoId 成绩id
     * @return 一条申请
     */
    Application findApplicationByScoId(Long scoId);

    /**
     * 按教师id查询所有申请
     *
     * @param teaId 教师id
     * @return 申请列表
     */
    List<Application> findAllApplicationsByTeaId(Long teaId);

    /**
     * 按成绩id修改学生得分
     *
     * @param scoId 成绩Id
     * @param grade 变更成绩
     * @return 变更条数
     */
    int updateScoreByScoId(Long scoId, Double grade);

    /**
     * 按申请id修改申请状态
     *
     * @param appId 申请Id
     * @param status 变更状态
     * @return 变更条数
     */
    int updateStatusByAppId(Long appId, Integer status);

    /**
     * 按学生账户名查询所有的申请
     *
     * @param stuUserName 学生账户
     * @return 申请列表
     */
    List<Application> findAllApplicationsStuUserName(String stuUserName);

    /**
     * 按id删除申请
     *
     * @param appId 申请id
     * @return 变动条数
     */
    int deleteApplyById(Long appId);
}
