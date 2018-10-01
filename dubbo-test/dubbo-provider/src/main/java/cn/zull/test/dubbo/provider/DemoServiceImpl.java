package cn.zull.test.dubbo.provider;

import cn.zull.test.dubbo.api.DemoService;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author zurun
 * @date 2018/10/1 21:54:29
 */
@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {

    @Override
    public String helloWorld(String name) {
        return "hello "+name;
    }
}
