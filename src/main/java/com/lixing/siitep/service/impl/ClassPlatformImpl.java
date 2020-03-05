package com.lixing.siitep.service.impl;

import com.lixing.siitep.mapper.TbClassPlatformMapper;
import com.lixing.siitep.service.ClassPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClassPlatformImpl implements ClassPlatformService {
    @Autowired
    TbClassPlatformMapper tbClassPlatformMapper;

    @Override
    public Map<String, Object> platformCount() {
        return tbClassPlatformMapper.platformCount();
    }
}
