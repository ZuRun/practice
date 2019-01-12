package cn.zull.test.dubbo.consumer.controller;

import cn.zull.test.dubbo.consumer.service.ConsumerDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zurun
 * @date 2018/10/1 21:59:52
 */
@RestController
public class DemoController {

    @Autowired
    ConsumerDemoService demoService;

    @RequestMapping("demo/{name}")
    public String demo( @PathVariable String name){
//        return null;
        return demoService.demo(name);
    }
}
