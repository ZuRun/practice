package cn.zull.test.dubbo.provider.service.impl;

import cn.zull.test.dubbo.api.DemoService;
import cn.zull.test.dubbo.constants.DubboVersion;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author zurun
 * @date 2018/10/1 21:54:29
 */
@Service(version = DubboVersion.V1)
public class DemoServiceImpl implements DemoService {

    @Override
    public String helloWorld(String name) {
        return "hello " + name;
    }
}
