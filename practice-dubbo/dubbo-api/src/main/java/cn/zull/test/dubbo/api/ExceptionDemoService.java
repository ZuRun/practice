package cn.zull.test.dubbo.api;

/**
 * @author zurun
 * @date 2018/11/3 00:28:23
 */
public interface ExceptionDemoService {
    String projectRuntimeException();

    String dubboProviderException();

    String exception() throws Exception;

    String customException();

    String runtimeException();
}
