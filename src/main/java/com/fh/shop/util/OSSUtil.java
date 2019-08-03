package com.fh.shop.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * <pre>包名称：com.fh.shop.util
 * 类名称：OSSUtil
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/1421:21
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Component
public class OSSUtil {

    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;
    private static String ossUrl;

    @Value("${oss.endpoint}")
    public void setEndpoint(String endpoint) {
        OSSUtil.endpoint = endpoint;
    }
    @Value("${oss.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        OSSUtil.accessKeyId = accessKeyId;
    }
    @Value("${oss.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        OSSUtil.accessKeySecret = accessKeySecret;
    }
    @Value("${oss.bucketName}")
    public void setBucketName(String bucketName) {
        OSSUtil.bucketName = bucketName;
    }
    @Value("${oss.ossUrl}")
    public void setOssUrl(String ossUrl) {
        OSSUtil.ossUrl = ossUrl;
    }

    //使用流上传文件
    public static String uploadFile(InputStream is, String fileName){
        OSS ossClient = null;
        String path ="";
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            String dateStr = DateAUtil.datastr(new Date(), DateAUtil.YMD).replace("-","");
            String uuid = UUID.randomUUID().toString().replace("-","");
            String filepath = dateStr + "/" + uuid + getSuffix(fileName);
            ossClient.putObject(bucketName, filepath, is);
            path =ossUrl+"/"+filepath;
        } finally {
            if(null != is){
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != ossClient){
                ossClient.shutdown();
            }
        }
        return path;
    }
    //上传文件
    public static String uploadFile(String fileName){
        InputStream inputStream = null;
        OSS ossClient = null;
        String filepath = "";
        String path ="";
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            String dateStr = DateAUtil.datastr(new Date(), DateAUtil.YMD).replace("-","");
            String uuid = UUID.randomUUID().toString().replace("-","");
            inputStream = new FileInputStream(fileName);
            filepath = dateStr + "/" + uuid + getSuffix(fileName);
            ossClient.putObject(bucketName, filepath, inputStream);
            path =ossUrl+filepath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != ossClient){
                ossClient.shutdown();
            }
        }
        return path;
    }
    //获取文件后缀名
    public static String getSuffix(String fileName){
        int i = fileName.lastIndexOf(".");
        String substring = fileName.substring(i);
            return substring;
        }
        //删除单张图片
        public static void deleteFile(String fileName){
            OSS ossClient = null;
            try {
                // 20190715/5653d1ed3e76418a9f3a0f7b3f96b56d.jpg
                String objectName=fileName.replace(ossUrl+"/","");
                // 创建OSSClient实例。
                ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                // 删除文件。
                ossClient.deleteObject(bucketName, objectName);
            } catch (OSSException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        } finally {
            if(null != ossClient){
            // 关闭OSSClient。
            ossClient.shutdown();
            }
        }

    }

}
