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

import java.util.ArrayList;
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
    public Map<String, Object> sum(String day) {
        if(isExist("tb_dayrpt_" + day) == 0){
            return tbDayrptMapper.sum("tb_dayrpt_" + day);
        }
        else{
            return tbDayrptMapper.sum(tblRecordMapper.selectTableName());
        }
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
        if(isExist("tb_dayrpt_" + day) == 0){
            return tbDayrptMapper.StuInProvince("tb_dayrpt_" + day);
        }
        else{
            return tbDayrptMapper.StuInProvince(tblRecordMapper.selectTableName());
        }

    }

    @Override
    public List<TbDayrpt> StuHotInProvince(String day) {
        if(isExist("tb_dayrpt_" + day) == 0){
            return tbDayrptMapper.StuHotInProvince("tb_dayrpt_" + day);
        }
        else{
            return tbDayrptMapper.StuHotInProvince(tblRecordMapper.selectTableName());
        }
    }

    @Override
    public List<TbDayrpt> StuIsolatedInProvince(String day) {
        if(isExist("tb_dayrpt_" + day) == 0){
            return tbDayrptMapper.StuIsolatedInProvince("tb_dayrpt_" + day);
        }
        else{
            return tbDayrptMapper.StuIsolatedInProvince(tblRecordMapper.selectTableName());
        }
    }
    @Override
    public List<TbDayrpt> FocusStu(String day) {
        if(isExist("tb_dayrpt_" + day) == 0){
            return tbDayrptMapper.FocusStu("tb_dayrpt_" + day);
        }
        else{
            return tbDayrptMapper.FocusStu(tblRecordMapper.selectTableName());
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

    @Override
    public List<TbDayrpt> StuInSuZhou(String city,String province) {
        List<String> list = tblRecordMapper.OneWeekTable();
        List<TbDayrpt> tbDayrpts = null;
        if(city!=null){
            tbDayrpts = tbDayrptMapper.StuInSuZhou(list,city,null);
        }
        if(province!=null){
            tbDayrpts =  tbDayrptMapper.StuInSuZhou(list,null,province);
        }
        return tbDayrpts;
    }

    @Override
    public List<TbDayrpt> StuFeverTrend() {
        List<String> list = tblRecordMapper.OneWeekTable();
        List<TbDayrpt> tbDayrpts = tbDayrptMapper.StuFeverTrend(list);
        return tbDayrpts;
    }

}
