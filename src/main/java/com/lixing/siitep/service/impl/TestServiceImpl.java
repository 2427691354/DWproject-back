package com.lixing.siitep.service.impl;

import com.lixing.siitep.entity.TbDayrpt;
import com.lixing.siitep.entity.TbDayrptExample;
import com.lixing.siitep.entity.TblRecord;
import com.lixing.siitep.mapper.TbDayrptMapper;
import com.lixing.siitep.mapper.TblRecordMapper;
import com.lixing.siitep.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cc
 * @Date: 2020/2/23
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TblRecordMapper tblRecordMapper;

    @Autowired
    private TbDayrptMapper tbDayrptMapper;

    @Override
    public List<TbDayrpt> selectByExample(TbDayrpt tbDayrpt) {
        TbDayrptExample example = new TbDayrptExample();
        TbDayrptExample.Criteria criteria = example.createCriteria();
        example.setTableName(tblRecordMapper.selectTableName());
        return tbDayrptMapper.selectByExample(example);
    }
}
