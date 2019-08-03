package com.fh.shop.demo;


import com.fh.shop.po.product.ProductInfo;
import com.fh.shop.util.DateAUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * <pre>包名称：com.fh.shop.demo
 * 类名称：Main1
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/2523:10
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class Main1 {

    public static void main(String[] args) throws IOException, TemplateException {
        //创建配置文件
        Configuration configuration = new Configuration();
        //指定配置文件的位置
        configuration.setClassForTemplateLoading(Main1.class,"/template");
        //获取模板文件
        Template template =  configuration.getTemplate("info.html");

        //构造函数
        Map map = new HashMap();
        map.put("companyName","郑州飞狐教育");
        List list = new ArrayList();
        ProductInfo p1 = new ProductInfo();
        p1.setProductName("123");
        p1.getBrand().setBrandName("小马");
        p1.setProductPrice(202f);

        ProductInfo p2 = new ProductInfo();
        p2.setProductName("123");
        p2.getBrand().setBrandName("小马");
        p2.setProductPrice(202f);

        list.add(p1);
        list.add(p2);

        map.put("products",list);
        map.put("createDate",DateAUtil.datastr(new Date(),DateAUtil.YMD));

        //将模板和数据结合
        //StringWriter stringWriter = new FileWriter();
        FileWriter fileWriter = new FileWriter("d:/a.html");
        template.process(map,fileWriter);
        //获取结果
        //String a = stringWriter.toString();
        // System.out.println(a);


    }
}
