package com.lixing.siitep.service.impl;

import com.lixing.siitep.entity.TbPictures;
import com.lixing.siitep.entity.TbPicturesExample;
import com.lixing.siitep.mapper.TbPicturesMapper;
import com.lixing.siitep.service.PicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author: zxx
 * @Date: 2020/2/24
 */
@Service
public class PicturesServiceImpl implements PicturesService {
    @Autowired
    private TbPicturesMapper tbPicturesMapper;

    @Override
    public List<TbPictures> findByExample(TbPictures pictures) {
        TbPicturesExample example = new TbPicturesExample();
        TbPicturesExample.Criteria criteria = example.createCriteria();
       if (pictures.getType() == 1)
           criteria.andImgEqualTo(pictures.getImg());

       return tbPicturesMapper.selectByExample(example);

    }
}
