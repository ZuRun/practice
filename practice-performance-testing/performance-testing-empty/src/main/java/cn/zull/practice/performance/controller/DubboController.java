package cn.zull.practice.performance.controller;

import cn.zull.practice.common.basis.constants.Result;
import cn.zull.practice.performance.dubbo.api.service.SimpleService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zurun
 * @date 2019/1/16 00:43:16
 */
@RestController
@RequestMapping("dubbo")
public class DubboController {

    @Reference(version = "1.0")
    SimpleService simpleService;

    @GetMapping("plus")
    public Result plus() {
        int result = simpleService.plus(1, 2);
        return Result.ok().addResult(result);
    }
}
