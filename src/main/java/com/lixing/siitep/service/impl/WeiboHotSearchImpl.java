package com.lixing.siitep.service.impl;

import com.lixing.siitep.entity.TbWeibohotsearch;
import com.lixing.siitep.mapper.TbWeibohotsearchMapper;
import com.lixing.siitep.service.WeiboHotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeiboHotSearchImpl implements WeiboHotSearchService {
    @Autowired
    TbWeibohotsearchMapper tbWeibohotsearchMapper;

    @Override
    public List<TbWeibohotsearch> selectTitle() {
        return tbWeibohotsearchMapper.selectTitle();
    }
}
