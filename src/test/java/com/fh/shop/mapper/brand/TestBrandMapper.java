package com.fh.shop.mapper.brand;

import com.fh.shop.po.brand.BrandInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <pre>包名称：com.fh.shop.mapper.brand
 * 类名称：TestBrandMapper
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/919:09
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-commons.xml")
public class TestBrandMapper extends AbstractJUnit4SpringContextTests {

    @Autowired
    private IBrandMapper brandMapper;

    @Test
    public void TestBrandMapper() throws ParseException {
        BrandInfo brand = new BrandInfo();
        brand.setBrandName("精英");
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        brand.setCreateDate(sim.parse("1999-01-01"));
        brandMapper.addBrand(brand);
    }

}
