//package cn.zull.practice.performance.controller;
//
//import cn.zull.practice.common.basis.constants.Result;
//import cn.zull.practice.performance.dubbo.api.service.SimpleService;
//import com.alibaba.dubbo.config.annotation.Reference;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author zurun
// * @date 2019/1/16 00:43:16
// */
//@RestController
//@RequestMapping("dubbo/calc")
//public class DubboController {
//
////    @Reference(version = "1.0")
//    SimpleService simpleService;
//
//    @GetMapping()
//    public Result plus() {
//        int result = simpleService.plus(1, 2);
//        return Result.ok().addResult(result);
//    }
//
//    @GetMapping("withSleep20Plus20")
//    public Result withSleep20Plus20() throws InterruptedException {
//        Thread.sleep(20);
//        int result = simpleService.plusWithSleep20(1, 2);
//        return Result.ok().addResult(result);
//    }
//
//    @GetMapping("withSleep20")
//    public Result plusWithSleep20() {
//        int result = simpleService.plusWithSleep20(1, 2);
//        return Result.ok().addResult(result);
//    }
//
//    @GetMapping("withSleep50")
//    public Result plusWithSleep50() {
//        int result = simpleService.plusWithSleep50(1, 2);
//        return Result.ok().addResult(result);
//    }
//
//    @GetMapping("withSleep50Plus20")
//    public Result withSleep50Plus20() throws InterruptedException {
//        Thread.sleep(20);
//        int result = simpleService.plusWithSleep50(1, 2);
//        return Result.ok().addResult(result);
//    }
//
//    @GetMapping("withSleep100")
//    public Result plusWithSleep100() {
//        int result = simpleService.plusWithSleep100(1, 2);
//        return Result.ok().addResult(result);
//    }
//}
