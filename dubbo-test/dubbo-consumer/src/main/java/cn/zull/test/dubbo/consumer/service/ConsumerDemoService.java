package cn.zull.test.dubbo.consumer.service;

import cn.zull.test.dubbo.api.DemoService;
import cn.zull.tracing.dubbo.RpcTraceContext;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zurun
 * @date 2018/10/1 22:00:38
 */
@Service
public class ConsumerDemoService {

//    @Reference(version = "1.0.0",url="dubbo://192.168.0.101:12345")
    @Reference(version = "1.0.0")
    private DemoService demoService;

    @Autowired
    RpcTraceContext traceContext;

    public String demo(String name){
        return demoService.helloWorld(name);
    }

}
