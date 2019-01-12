package cn.zull.practice.dubbo.consumer.controller;

import cn.zull.practice.dubbo.consumer.service.ConsumerExceptionDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zurun
 * @date 2018/11/3 00:30:26
 */
@RestController
@RequestMapping("exception")
public class ExceptionController {
    @Autowired
    ConsumerExceptionDemoService exceptionDemoService;

    @RequestMapping("project")
    public String projectRuntimeException() {
        return exceptionDemoService.projectRuntimeException();
    }

    @RequestMapping("dubboProviderException")
    public String dubboProviderException() {
        return exceptionDemoService.dubboProviderException();
    }

    @RequestMapping("exception")
    public String exception() throws Exception {
        return exceptionDemoService.exception();
    }

    @RequestMapping("runtimeException")
    public String runtimeException() {
        return exceptionDemoService.runtimeException();
    }

    @RequestMapping("customException")
    public String customException() {
        return exceptionDemoService.customException();
    }
}
