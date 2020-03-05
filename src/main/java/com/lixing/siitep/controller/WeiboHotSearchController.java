package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbWeibohotsearch;
import com.lixing.siitep.service.WeiboHotSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "词云接口 微博热搜（网课出现率） / 教师反馈 ")
@RequestMapping("/weibohotsearch")
public class WeiboHotSearchController {
    @Autowired
    private WeiboHotSearchService weiboHotSearchService;
    @GetMapping("/selectweiboTitle")
    @ApiOperation("微博热搜")
    private List<TbWeibohotsearch> selectweiboTitle(){
        return weiboHotSearchService.selectTitle();
    }
}
