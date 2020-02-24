package com.lixing.siitep.service;

import com.lixing.siitep.entity.TbPictures;
import com.lixing.siitep.entity.TbPicturesExample;

import java.util.List;
/**
 * @Author: zxx
 * @Date: 2020/2/24
 */
public interface PicturesService {
    //查询图片轮播
    List<TbPictures> findByExample(TbPictures pictures);
}
