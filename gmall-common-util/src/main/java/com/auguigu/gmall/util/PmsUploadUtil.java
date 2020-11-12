package com.auguigu.gmall.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public class PmsUploadUtil {
    public static String uploadImage(MultipartFile multipartFile) throws IOException {
        ConstantUtil constantUtil = new ConstantUtil();
        String ImageUrl = constantUtil.getImageUrl();
        //获取tracker的配置文件路径
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();
        //读取配置文件
        try {
            ClientGlobal.init(tracker);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("配置实例化失败");
        }
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        //创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //获取文件的byte数组
        byte[] bytes = multipartFile.getBytes();
        //获取文件的后缀名
        String multipartFileName = multipartFile.getOriginalFilename();
        int index = multipartFileName.lastIndexOf(".");
        String extNamne = multipartFileName.substring(index + 1);
        //使用StorageClient对象上传图片；扩展名不带“.”
        try {
            String[] strings = storageClient.upload_file(bytes, extNamne, null);
            //返回数组。包含组名和图片的路径。重组成URL链接
            for (String string : strings) {
                ImageUrl += "/" + string;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ImageUrl;
    }
}
