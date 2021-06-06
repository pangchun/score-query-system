package com.bs;

import com.bs.dao.ScoreDao;
import com.bs.model.Application;
import com.bs.model.Score;
import com.bs.model.dto.ScoreQuery;
import com.bs.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 单元测试类
 *
 * @author : zhangqianchun
 * @date : 2020-1-19 15:23
 * @description : 测试类
 * @version : v1.0
 */
@SpringBootTest
public class ZhangQianChunTests {

    @Resource
    ScoreDao scoreDao;

    @Resource
    ScoreService scoreService;

    /**
     * 测试学生成绩查询Dao - findAllScoreByUserName(String username);
     */
    @Test
    void contextTest1() {
        List<Score> scoreList = scoreDao.findAllScoreByUserName("1740611318");
        for (Score score : scoreList) {
            System.out.println(score.toString());
        }
    }

    @Test
    void contextTest2() {
        ScoreQuery query = new ScoreQuery();
        //query.setScoId("1");
        //query.setSubId("3");
        query.setSubName("积分");
        //query.setTeaId("3");
        //query.setTeaName("张");
        //query.setStuUserName("1740611318");
        //query.setStuName("张");
        List<Score> scoreList = scoreDao.findByDynamic(query);
        for (Score score : scoreList) {
            System.out.println(score.toString());
        }
    }

    @Test
    void contextTest3() {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        List<HashMap<String, Object>> maps = scoreDao.findBySubIdSet(set);
    }

    @Test
    void contextTest4() {
        Application application = scoreDao.findApplicationByScoId((long) 1);
        System.out.println(application.toString());
    }

    @Test
    void contextTest5() {
        List<Application> applications = scoreDao.findAllApplicationsByTeaId((long) 3);
        System.out.println(applications.toString());
        System.out.println("o");
    }

    @Test
    void contextTest6() {
        System.out.println(System.getProperty("user.home"));
    }


}
