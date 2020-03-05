package com.lixing.siitep.mapper;

import com.lixing.siitep.entity.TbWeibohotsearch;
import com.lixing.siitep.entity.TbWeibohotsearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbWeibohotsearchMapper {
    long countByExample(TbWeibohotsearchExample example);

    int deleteByExample(TbWeibohotsearchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbWeibohotsearch record);

    int insertSelective(TbWeibohotsearch record);

    List<TbWeibohotsearch> selectByExample(TbWeibohotsearchExample example);

    TbWeibohotsearch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbWeibohotsearch record, @Param("example") TbWeibohotsearchExample example);

    int updateByExample(@Param("record") TbWeibohotsearch record, @Param("example") TbWeibohotsearchExample example);

    int updateByPrimaryKeySelective(TbWeibohotsearch record);

    int updateByPrimaryKey(TbWeibohotsearch record);
    //微博热搜标题
    List<TbWeibohotsearch> selectTitle();
}