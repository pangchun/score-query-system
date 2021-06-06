package com.bs.support.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA
 * User: Shelter
 * Date: 2020/7/2
 * Time: 17:09
 * Description: Json方法封装类
 * Json转Object
 * Object转Json
 * Version: V1.0
 */
public class JsonUtil {
    /** jackson 实例对象 */
    private static ObjectMapper objectMapper = new ObjectMapper();
    /** log4j 实例对象 */
    private static Logger log;

    /**
     * 将json转换成对象
     * @param value json数据
     * @param valueType 转换成对象的class对象
     * @param currentClass 调用该方法的class对象
     * @param <T> 泛型，根据valueType确定
     * @return
     */
    public static <T>T toObject(String value, Class<T> valueType, Class currentClass){
        T object = null;
        log = Logger.getLogger(currentClass);
        try {
            object = objectMapper.readValue(value, valueType);
        } catch (JsonProcessingException e) {
            log.error("json转object处理错误，异常信息为 {"+e.getClass().getName()+": "+e.getMessage()+"}, 发生于："+currentClass);
        }catch (IOException e){
            log.error("json转object处理错误，异常信息为 {"+e.getClass().getName()+": "+e.getMessage()+"}, 发生于："+currentClass);
        }
        return object;
    }

    /**
     * 对象转换成json数据
     * @param value 对象
     * @param currentClass 调用该方法的class对象
     * @return
     */
    public static String toJson(Object value, Class currentClass){
        log = Logger.getLogger(currentClass);
        // objectMapper传入date默认为时间戳显示，这里将时间戳设置为false，停用
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        // 创建一个日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 给objectMapper配置一个日期类型的格式
        objectMapper.setDateFormat(simpleDateFormat);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("object转json处理错误，异常信息为 {"+e.getClass().getName()+": "+e.getMessage()+"}");
        }
        return json;
    }
}
