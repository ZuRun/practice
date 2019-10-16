package cn.zull.practice.performance.redis.controller;

import cn.zull.practice.common.basis.constants.Result;
import cn.zull.practice.performance.redis.service.RedisTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zurun
 * @date 2019/10/16 11:49:38
 */
@Slf4j
@RestController
//@RequestMapping("redis")
public class RedisTestController {
    @Autowired
    RedisTestService redisTestService;

    @RequestMapping("write/batch")
    public Result batchWrite(@RequestParam int threadSize, @RequestParam int cycleNum) {
        if (threadSize > 100) {
            log.warn("[线程数过大] threadSize:{} cycleNum:{}", threadSize, cycleNum);
            return Result.fail(1, "线程数过大");
        }
        return Result.ok().addResult(redisTestService.batchWrite(threadSize, cycleNum));
    }

    @RequestMapping("read/batch")
    public Result batchRead(@RequestParam int threadSize, @RequestParam int cycleNum) {
        if (threadSize > 100) {
            log.warn("[线程数过大] threadSize:{} cycleNum:{}", threadSize, cycleNum);
            return Result.fail(1, "线程数过大");
        }
        return Result.ok().addResult(redisTestService.batchRead(threadSize, cycleNum));
    }
}
