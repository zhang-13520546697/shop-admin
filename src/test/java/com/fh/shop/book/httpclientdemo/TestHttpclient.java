package com.fh.shop.book.httpclientdemo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * <pre>包名称：com.fh.shop.httpclientdemo
 * 类名称：TestHttpclient
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/1113:58
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class TestHttpclient {

    @Test
    public  void test1(){
        CloseableHttpClient client = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        try {
            //打开浏览器
            client = HttpClientBuilder.create().build();
            //输入url地址
            httpGet = new HttpGet("https://www.baidu.com/");
            //敲回车[发送请求，并且响应数据]
           response = client.execute(httpGet);
            //获取响应内容
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpGet != null ){
                try {
                    httpGet.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            if(client != null ){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
