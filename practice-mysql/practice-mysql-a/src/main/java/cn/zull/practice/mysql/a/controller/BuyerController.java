package cn.zull.practice.mysql.a.controller;

import cn.zull.practice.common.basis.constants.Result;
import cn.zull.practice.mysql.a.service.BuyerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zurun
 * @date 2019/10/11 10:34:37
 */
@RestController
@RequestMapping("buyer")
public class BuyerController {
    @Autowired
    BuyerInfoService buyerInfoService;


    @RequestMapping("initialize")
    public Result initialize() {
        buyerInfoService.initializeData();
        return Result.ok();
    }

}
