package com.bs.support.util.excelUtils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.bs.model.dto.BatchEditDTO;
import com.bs.service.ScoreService;

/**
 * @author : zhangqianchun
 * @date : 2021-1-27 16:54
 * @description : 读Excel
 * @version : v1.0
 */
public class ReadExcel {

    public static void simpleRead(String fileName, ScoreService scoreService) {

        /*
           由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器;
          有个很重要的点 ExcelListener 不能被spring管理，要每次读取excel都要new,
          然后里面用到spring可以构造方法传进去;
        */
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, BatchEditDTO.class, new ExcelReadListener(scoreService)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        }
        finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }
}
