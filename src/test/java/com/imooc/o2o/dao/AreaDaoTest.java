package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:/spring/spring-service.xml"})
public class AreaDaoTest {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private AreaService areaService;

    @Test
    public void queryArea() {
        final List<Area> list = areaService.getAreaList();
        System.out.println(list.size());
    }

    @Test
    public void insertArea() {
    }

    @Test
    public void updateArea() {
    }

    @Test
    public void deleteArea() {
    }

    @Test
    public void batchDeleteArea() {
    }
}