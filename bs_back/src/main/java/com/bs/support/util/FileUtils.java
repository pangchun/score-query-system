package com.bs.support.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author : zhangqianchun
 * @date : 2021-1-27 14:54
 * @description : 文件上传工具类
 * @version : v1.0
 */
public class FileUtils {

    /**
     * 文件上传路径
     */
    public static final String PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\fileUpload\\";

    /**
     * 上传文件
     *
     * @param file MultipartFile文件
     * @return File文件
     */
    public static File upUtil(MultipartFile file) {

        //获取文件名
        String filename = file.getOriginalFilename();

        //获取文件后缀
        assert filename != null;
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);

        //重命名文件
        String nickName = System.currentTimeMillis() + "." + suffix;

        //生成文件
        File dest = new File(PATH, nickName);

        //检查目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        //将收到的文件传输到给定的目标文件
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dest;
    }
}
