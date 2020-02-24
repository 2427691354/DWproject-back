package com.lixing.siitep.service;

import com.lixing.siitep.entity.TbDayrpt;

import java.util.List;

/**
 * @Author: cc
 * @Date: 2020/2/23
 */
public interface TestService {

    List<TbDayrpt> selectByExample(TbDayrpt tbDayrpt);

}
