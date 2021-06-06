package com.bs.controller;

import com.alibaba.fastjson.JSON;
import com.bs.support.util.UUIDUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @ResponseBody
    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile file){
        //创建Map集合保存返回的JSON数据
        Map<String,Object> map = new HashMap<String,Object>();
        //判断是否有选中文件
        if(!file.isEmpty()){
            //前后端分离项目最好是将路径设置未后端web目录下
            //这样前端在访问时只需要加上后端访问域名http://localhost:8080/就可以访问到
            String path="D:\\bishe\\bsweb\\static\\img\\";
            //获取源文件的名称
            String oldFileName = file.getOriginalFilename();
            //获取文件的后缀名
            String extension = FilenameUtils.getExtension(oldFileName);
            //重命名旧文件
            String newFileName = UUIDUtils.randomUUID()+"."+extension;
            //为了解决同一个文件夹下文件过多的问题，使用日期作为文件夹管理
            String datePath = new SimpleDateFormat("yyyyMMdd").format(new Date());
            //组装最终的文件名
            String finalName = datePath + "/" + newFileName;
            //创建文件对象
            //参数1：文件上传的地址  参数2：文件名称
            File dest = new File(path,finalName);
            //判断该文件夹是否存在，不存在则创建文件夹
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();//创建文件夹
            }

            try {
                //进行文件上传
                file.transferTo(dest);
                map.put("code",0);//状态码
                map.put("msg","上传成功");//执行消息
                Map<String,Object> dataMap = new HashMap<String,Object>();
                /*
                /student/show/
                1.配置tomcat
                2.点击Deployment，点击+
                3.点击扩展资源external source
                4.选中上面D:/workspace/img/中的img目录
                5.将application context设置为/student/show/
                 */
                dataMap.put("src","/img/"+finalName);
                map.put("data",dataMap);//文件数据
                //保存到数据库需要的图片路径格式：前端的隐藏标签可以读到这个值
                map.put("imagePath",finalName);//隐藏域的值，只保留日期文件夹+重命名后的文件名

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return JSON.toJSONString(map);
    }
}
