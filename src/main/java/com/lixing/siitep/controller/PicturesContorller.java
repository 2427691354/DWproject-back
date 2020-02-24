package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbPictures;
import com.lixing.siitep.service.PicturesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zxx
 * @Date: 2020/2/24
 */
@RestController
@Api(tags = "轮播图图片相关接口")
@RequestMapping("/pictures")
public class PicturesContorller {
    @Autowired
    private PicturesService picturesService;

    @GetMapping("/selectByType")
    @ApiOperation("查找轮播图片")
    private List<TbPictures> getImg(@RequestParam(value = "type",required = false) int type){
        List<TbPictures> imgs = picturesService.selectByType(type);
        return imgs;
    }

}
