package com.fh.shop.biz.brand;

import com.fh.shop.po.brand.BrandInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <pre>包名称：com.fh.shop.biz.brand
 * 类名称：TestBrandServicesimpl
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/918:37
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-commons.xml")
public class TestBrandServicesimpl extends AbstractJUnit4SpringContextTests {

    @Resource(name="brandService")
    public IBrandService brandService;

    @Test
    public void testBrandService() throws ParseException {

        BrandInfo brandInfo = new BrandInfo();
        brandInfo.setBrandName("振华666");
        brandInfo.setId(24);
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        brandInfo.setCreateDate(sim.parse("2001-01-01"));
        brandService.updateBrand(brandInfo);
    }

}
