package cn.zull.practice.performance.dubbo.service.impl;

import cn.zull.practice.performance.dubbo.api.service.SimpleService;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author zurun
 * @date 2019/1/16 00:45:29
 */
@Service(version = "1.0")
public class SimpleServiceImpl implements SimpleService {

    @Override
    public int plus(int num1, int num2) {
        return num1 + num2;
    }
}
