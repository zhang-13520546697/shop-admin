import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;

/**
 * <pre>包名称：com.fh.shop
 * 类名称：TestRead
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/201:07
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class TestRead {

    @Test
    public void testRead(){
        //写java代码读取属性文件获取value
        try {
            CompositeConfiguration config = new CompositeConfiguration();
            String path = this.getClass().getClassLoader().getResource("config.properties").getPath();
            System.out.println(path);
            PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(path);
            config.addConfiguration(propertiesConfiguration);
            String host = config.getString("redis.host");
            short port = config.getShort("redis.port");
            System.out.println(host);
            System.out.println(port);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }


}
