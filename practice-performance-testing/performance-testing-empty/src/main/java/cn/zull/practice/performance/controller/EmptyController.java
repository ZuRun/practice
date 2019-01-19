package cn.zull.practice.performance.controller;

import cn.zull.practice.common.basis.constants.AuthType;
import cn.zull.practice.common.basis.constants.IflytekAuth;
import cn.zull.practice.common.basis.constants.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zurun
 * @date 2019/1/14 23:06:43
 */
@Slf4j
@RestController
@RequestMapping("empty")
public class EmptyController {
    @GetMapping("eeempty")
    public Result empty() {
        return Result.ok();
    }

    @IflytekAuth(authType = AuthType.PERFORMANCE_TEST)
    @GetMapping("auth")
    public Result auth() {
        return Result.ok();
    }

    @GetMapping("withLog")
    public Result emptyWithLog() {
        log.info("[接受到请求]");
        return Result.ok();
    }

    @GetMapping("sleep")
    public Result sleep() throws InterruptedException {
        Thread.sleep(20L);
        return Result.ok();
    }

    @GetMapping("withParam")
    public Result emptyWithParam(@RequestParam String param) {
        return Result.ok().addResult("hello " + param);
    }

}
