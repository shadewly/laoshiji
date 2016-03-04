package com.bz.service.impl;

import com.bz.model.Master;
import com.bz.service.IMasterService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ThinkPad on 2016-01-21.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-core.xml")
public class MasterServiceTest {
    @Autowired
    IMasterService masterService;

    @org.junit.Test
    public void testSaveMaster() throws Exception {
        Master master = new Master("zhangsan", 30, "女");
        master.setId(1231231l);
        masterService.saveMaster(master);
    }
}