package cn.zull.practice.mysql.a.controller;

import cn.zull.practice.common.basis.constants.Result;
import cn.zull.practice.mysql.a.service.OrderQueryService;
import cn.zull.practice.mysql.a.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zurun
 * @date 2019/10/11 15:50:48
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderQueryService orderQueryService;

    @RequestMapping("insert")
    public Result insert() {
        orderService.insert();
        return Result.ok();
    }

    @RequestMapping("insert/batch/{number}")
    public Result batchInsert(@PathVariable Integer number) {
        orderService.batchInsert(number);
        return Result.ok();
    }

    @GetMapping("orderDetails")
    public Result select(@RequestParam Integer userId) {
        return Result.ok().addResult(orderQueryService.getOrderDetailsByUserId(userId));
    }
}
