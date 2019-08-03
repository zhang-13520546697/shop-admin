package com.fh.shop.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.util
 * 类名称：HttpClientUtil
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/1121:20
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class HttpClientUtil {

    //httpclient 发送post请求
    public static String sendPost(String url, Map<String,String> params,Map<String,String>headers) {
        CloseableHttpClient client = null;
        HttpPost httpPost = null;
        CloseableHttpResponse execute =null;
        String result="";
        try {
            client = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            if(null != params && params.size()>0){
                Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
                List<NameValuePair> info = new ArrayList<>();
                while (iterator.hasNext()){
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    info.add(new BasicNameValuePair(key,value));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(info,"utf-8");
                //将数据放到body体中
                httpPost.setEntity(urlEncodedFormEntity);
            }
            if(null != headers && headers.size()>0){
                Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    //将数据放到body体中
                    httpPost.addHeader(key,value);
                }
            }
            execute = client.execute(httpPost);
            HttpEntity entity = execute.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            return  result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (execute!=null){
                try {
                    execute.close();
                    execute=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (httpPost!=null){
                httpPost.releaseConnection();
            }
            if(client!=null){
                try {
                    client.close();
                    client=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    //httpclient 发送get请求
    public static  String sendGet(String url){
        CloseableHttpClient client = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        String result = "";
        //调用远程接口
        try {
            client = HttpClientBuilder.create().build();
            httpGet = new HttpGet(url);
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != response){
                try {
                    response.close();
                    response=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != httpGet){
                //释放连接
                httpGet.releaseConnection();
            }
            if(client !=null){
                try {
                    client.close();
                    client=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
