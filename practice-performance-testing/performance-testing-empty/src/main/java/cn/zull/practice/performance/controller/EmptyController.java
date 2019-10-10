package cn.zull.practice.performance.controller;

import cn.zull.practice.common.basis.constants.AuthType;
import cn.zull.practice.common.basis.constants.IflytekAuth;
import cn.zull.practice.common.basis.constants.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("sleep/{time}")
    public Result sleep(@PathVariable Integer time) throws InterruptedException {
        Thread.sleep(time);
        return Result.ok();
    }

    @GetMapping("post")
    public Result post(@RequestBody String body) {
        return Result.ok();
    }

    @GetMapping("withParam")
    public Result emptyWithParam(@RequestParam String param) {
        return Result.ok().addResult("hello " + param);
    }

}
