package com.bs.support.util.excelUtils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.bs.model.dto.BatchEditDTO;
import com.bs.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhangqianchun
 * @date : 2021-1-27 16:49
 * @description : 监听器
 * @version : v1.0
 */
public class ExcelReadListener extends AnalysisEventListener<BatchEditDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReadListener.class);

    /**
     * 每隔100条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 用一个list来装解析得到的数据
     */
    List<BatchEditDTO> list = new ArrayList<>();

    /**
     * 注入Service
     */
    private ScoreService scoreService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param scoreService 业务层
     */
    public ExcelReadListener(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    /**
     *  这个每一条数据解析都会来调用
     *
     * @param data 要解析成的java对象类
     * @param analysisContext 解析上下文
     */
    @Override
    public void invoke(BatchEditDTO data, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param analysisContext 解析上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析及存储完成！");
    }

    /**
     * 保存数据
     */
    public void saveData() {
        LOGGER.info("{}条数据，开始保存数据！", list.size());

        //这里可以调用service的方法处理数据
        scoreService.saveExcelData(list);

        LOGGER.info("存储数据成功！");

    }
}
