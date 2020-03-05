package com.lixing.siitep.mapper;

import com.lixing.siitep.entity.TbClassPlatform;
import com.lixing.siitep.entity.TbClassPlatformExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbClassPlatformMapper {
    long countByExample(TbClassPlatformExample example);

    int deleteByExample(TbClassPlatformExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbClassPlatform record);

    int insertSelective(TbClassPlatform record);

    List<TbClassPlatform> selectByExample(TbClassPlatformExample example);

    TbClassPlatform selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbClassPlatform record, @Param("example") TbClassPlatformExample example);

    int updateByExample(@Param("record") TbClassPlatform record, @Param("example") TbClassPlatformExample example);

    int updateByPrimaryKeySelective(TbClassPlatform record);

    int updateByPrimaryKey(TbClassPlatform record);

    Map<String,Object> platformCount();

}