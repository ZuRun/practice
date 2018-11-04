package cn.zull.test.dubbo.consumer.service;

import cn.zull.test.dubbo.api.ExceptionDemoService;
import cn.zull.test.dubbo.constants.DubboVersion;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author zurun
 * @date 2018/11/3 00:29:12
 */
@Service
public class ConsumerExceptionDemoService {
    @Reference(version = DubboVersion.V1)
    ExceptionDemoService exceptionDemoService;

    public String projectRuntimeException() {
        return exceptionDemoService.projectRuntimeException();
    }

    public String dubboProviderException() {
        return exceptionDemoService.dubboProviderException();
    }

    public String exception() throws Exception {
        return exceptionDemoService.exception();
    }

    public String runtimeException() {
        return exceptionDemoService.runtimeException();
    }

    public String customException() {
        return exceptionDemoService.customException();
    }
}
