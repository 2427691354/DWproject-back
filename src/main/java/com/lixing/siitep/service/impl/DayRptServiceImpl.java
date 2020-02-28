package com.lixing.siitep.service.impl;

import com.lixing.siitep.entity.TbDayrpt;
import com.lixing.siitep.entity.TbDayrptExample;
import com.lixing.siitep.entity.TblRecord;
import com.lixing.siitep.entity.TblRecordExample;
import com.lixing.siitep.mapper.TbDayrptMapper;
import com.lixing.siitep.mapper.TblRecordMapper;
import com.lixing.siitep.service.DayRptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: cc
 * @Date: 2020/2/23
 */
@Service
public class DayRptServiceImpl implements DayRptService
{
    @Autowired
    private TblRecordMapper tblRecordMapper;

    @Autowired
    private TbDayrptMapper tbDayrptMapper;

    @Override
    public Map<String, Object> sum() {
        TbDayrptExample example = new TbDayrptExample();
        TbDayrptExample.Criteria criteria = example.createCriteria();
        example.setTableName(tblRecordMapper.selectTableName());
        return tbDayrptMapper.sum(example);
    }

    @Override
    public List<TbDayrpt> selectByExample(TbDayrpt tbDayrpt) {
        TbDayrptExample example = new TbDayrptExample();
        TbDayrptExample.Criteria criteria = example.createCriteria();
        example.setTableName(tblRecordMapper.selectTableName());
        return tbDayrptMapper.selectByExample(example);
    }

    @Override
    public List<TbDayrpt> StuInProvince(String day) {
        if(day=="") {
            System.out.println(tblRecordMapper.selectTableName());

            return tbDayrptMapper.StuInProvince(tblRecordMapper.selectTableName());
        }else {
            String dayrpt = "tb_dayrpt_" + day;
            return tbDayrptMapper.StuInProvince(dayrpt);
        }

    }

    @Override
    public List<TbDayrpt> StuHotInProvince(String day) {
        if(day==""){
            System.out.println(tblRecordMapper.selectTableName());
            return tbDayrptMapper.StuHotInProvince(tblRecordMapper.selectTableName());

        }else{

            String dayrpt = "tb_dayrpt_" + day;
            return tbDayrptMapper.StuHotInProvince(dayrpt);
        }
    }

    @Override
    public List<TbDayrpt> StuIsolatedInProvince(String day) {
        if(day==""){
            System.out.println(tblRecordMapper.selectTableName());
            return tbDayrptMapper.StuIsolatedInProvince(tblRecordMapper.selectTableName());

        }else{
            String dayrpt = "tb_dayrpt_" + day;
            return tbDayrptMapper.StuIsolatedInProvince(dayrpt);
        }
    }

    @Override

    public List<TbDayrpt> FocusStu(String day) {
        if(day==""){
            System.out.println(tblRecordMapper.selectTableName());
            return tbDayrptMapper.FocusStu(tblRecordMapper.selectTableName());

        }else{
            String dayrpt = "tb_dayrpt_" + day;
            return tbDayrptMapper.FocusStu(dayrpt);
        }
    }

    @Override
    public List<TbDayrpt> getTemperatureGradeRatio(String day) {
        if(isExist("tb_dayrpt_" + day) == 0){
            return tbDayrptMapper.getTemperatureGradeRatio("tb_dayrpt_" + day);
        }
        else{
            return tbDayrptMapper.getTemperatureGradeRatio(tblRecordMapper.selectTableName());
        }
    }

    @Override
    public String NewTime() {
        TbDayrptExample example = new TbDayrptExample();
        TbDayrptExample.Criteria criteria = example.createCriteria();
        example.setTableName(tblRecordMapper.selectTableName());
        return tbDayrptMapper.NewTime(example);
    }

    @Override
    public int isExist(String tableName) {
        TblRecordExample example = new TblRecordExample();
        TblRecordExample.Criteria criteria = example.createCriteria();
        criteria.andTableNameEqualTo(tableName);
        List<TblRecord> tblRecords = tblRecordMapper.selectByExample(example);
        return tblRecords.size()>0 ? 0:-1;
    }


}
