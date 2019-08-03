import com.fh.shop.util.CheckSumBuilder;
import com.fh.shop.util.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.IOException;
import java.util.*;
import java.util.Map;

/**
 * <pre>包名称：PACKAGE_NAME
 * 类名称：TestSMS
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/120:38
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-commons.xml")
public class TestSMS {

    @Value("${api.httpclient.sms}")
    private String url;

    @Value("${sms.url}")
    private String smsurl;
    @Value("${sms.templateid}")
    private String templateid;
    @Value("${sms.AppKey}")
    private String AppKey;
    @Value("${sms.AppSecret}")
    private String AppSecret;

    @Test
    public void testHttpClient()  {
        Map<String,String> params = new HashMap<>();
        params.put("mobile","13295405567");
        params.put("templateid",templateid);
        Map<String, String> headers = new HashMap<>();
        headers.put("AppKey",AppKey);
        String uuidInfo = UUID.randomUUID().toString();
        headers.put("Nonce", uuidInfo);
        String time = new Date().getTime() + "";
        headers.put("CurTime", time);
        String checkSum = CheckSumBuilder.getCheckSum(AppSecret, uuidInfo, time);
        headers.put("CheckSum", checkSum);
        String result = HttpClientUtil.sendPost(smsurl, params, headers);
        System.out.println(result);
    }

}


