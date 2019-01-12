package cn.zull.test.dubbo.provider.exception;

/**
 * @author zurun
 * @date 2018/11/3 23:06:29
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
