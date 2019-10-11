package cn.zull.practice.mysql.a.controller;

import cn.zull.practice.common.basis.constants.Result;
import cn.zull.practice.mysql.a.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zurun
 * @date 2019/10/11 15:50:48
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("insert")
    public Result insert() {
        orderService.insert();
        return Result.ok();
    }

    @RequestMapping("insert/batch")
    public Result batchInsert() {
        orderService.batchInsert();
        return Result.ok();
    }
}
