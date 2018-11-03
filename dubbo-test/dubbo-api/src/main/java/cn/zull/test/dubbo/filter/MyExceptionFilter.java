package cn.zull.test.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zurun
 * @date 2018/11/3 00:11:28
 */
@Activate(group = Constants.PROVIDER)
public class MyExceptionFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            Result result = invoker.invoke(invocation);
            if (result.hasException() && GenericService.class != invoker.getInterface()) {
                logger.info("hasException");
                return result;
            }
            return result;
        } catch (RuntimeException e) {
            logger.error("error e:" + e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }
}
