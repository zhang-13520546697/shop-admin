import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.fh.shop.util.DateAUtil;
import com.fh.shop.util.OSSUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * <pre>包名称：com.fh.shop
 * 类名称：TestOss
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/1417:10
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/spring-commons.xml")
public class TestOss extends AbstractJUnit4SpringContextTests {

    @Test
    public void test2() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAIP3EnhWcbjRD5";
        String accessKeySecret = "xyjKQF4TWiYyPbXiMHrx4uFbHRiNgC";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        String dateStr = DateAUtil.datastr(new Date(), DateAUtil.YMD).replace("-","");
        String uuid = UUID.randomUUID().toString().replace("-","");

        InputStream   inputStream = new FileInputStream("E:\\FeiH\\images\\003.jpg");
        String filepath = dateStr + "/" + uuid + ".jpg";
        PutObjectResult zhangyux = ossClient.putObject("zhangyux", filepath, inputStream);
        // 关闭OSSClient。
        String path ="https://zhangyux.oss-cn-beijing.aliyuncs.com/20190714/428fc56d703649f5bdb548994d839041.jpg"+filepath;
        System.out.println(path);
        ossClient.shutdown();
    }
    @Test
    public void test3(){
        String path = OSSUtil.uploadFile("E:\\FeiH\\images\\2.jpg");
        System.out.println(path);
    }
    @Test
    public void test4(){
// Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIP3EnhWcbjRD5";
        String accessKeySecret ="xyjKQF4TWiYyPbXiMHrx4uFbHRiNgC";
        String bucketName = "zhangyux";
        String objectName = "https://zhangyux.oss-cn-beijing.aliyuncs.com/20190715/5ad355eb5d59453abbdf0c800e604c5b.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。
        ossClient.deleteObject(bucketName, objectName);

// 关闭OSSClient。
        ossClient.shutdown();
    }


}
