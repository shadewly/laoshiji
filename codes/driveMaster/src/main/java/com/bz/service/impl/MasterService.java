package com.bz.service.impl;

import com.bz.dao.MasterDao;
import com.bz.model.Master;
import com.bz.service.IMasterService;
import com.common.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ThinkPad on 2016-01-21.
 */
@Service
public class MasterService extends BasicService implements IMasterService {
    @Autowired
    private MasterDao masterDao;

    public void saveMaster(Master master) {
        this.masterDao.save(master);
    }
}
