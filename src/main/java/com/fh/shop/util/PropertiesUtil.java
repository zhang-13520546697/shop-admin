package com.fh.shop.util;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * <pre>包名称：com.fh.shop.util
 * 类名称：PropertiesUtil
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/201:34
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class PropertiesUtil {

    public static String getValue(String key){
        //写java代码读取属性文件获取value

        try {
            CompositeConfiguration config = new CompositeConfiguration();
            String path = PropertiesUtil.class.getClassLoader().getResource("config.properties").getPath();
            PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(path);
            config.addConfiguration(propertiesConfiguration);
            return config.getString(key);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return "";
    }

}
