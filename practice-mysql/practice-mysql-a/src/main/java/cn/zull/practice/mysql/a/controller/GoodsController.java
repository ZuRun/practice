package cn.zull.practice.mysql.a.controller;

import cn.zull.practice.common.basis.constants.Result;
import cn.zull.practice.mysql.a.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zurun
 * @date 2019/10/10 16:35:24
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("test")
    public Result test() {
        goodsService.test();
        return Result.ok();
    }
    @RequestMapping("initialize")
    public Result initializeData() {
        goodsService.initializeData();
        return Result.ok();
    }


}
