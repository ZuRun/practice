package cn.zull.test.dubbo.provider.service.impl;

import cn.zull.practice.common.basis.exception.AssertException;
import cn.zull.test.dubbo.api.ExceptionDemoService;
import cn.zull.test.dubbo.constants.DubboVersion;
import cn.zull.test.dubbo.constants.ErrorCode;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zurun
 * @date 2018/11/3 00:18:04
 */
@Service(version = DubboVersion.V1)
public class ExceptionDemoServiceImpl implements ExceptionDemoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String projectRuntimeException() {
        logger.info("projectRuntimeException");
        throw new AssertException(ErrorCode.ASSERT.ILLEGAL_ARGUMENT);
    }

    @Override
    public String exception() throws Exception {
        logger.info("exception");
        throw new Exception("exception");
    }

    @Override
    public String runtimeException() {
        logger.info("RuntimeException");
        throw new RuntimeException("RuntimeException");

    }


}
