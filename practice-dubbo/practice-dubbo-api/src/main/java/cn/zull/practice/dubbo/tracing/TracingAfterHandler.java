package cn.zull.practice.dubbo.tracing;

import cn.zull.tracing.core.after.TracingLogHandler;
import cn.zull.tracing.core.model.TraceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zurun
 * @date 2018/11/2 15:25:01
 */
@Component
public class TracingAfterHandler implements TracingLogHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handler(TraceLog traceLog) {
        logger.warn("【链路】" + traceLog.toString());
    }
}
