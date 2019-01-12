package cn.zull.practice.dubbo.provider.exception;

import cn.zull.practice.common.basis.constants.IMessage;
import cn.zull.practice.common.basis.exception.BusinessException;

/**
 * @author zurun
 * @date 2018/11/3 17:48:32
 */
public class DubboProviderException extends BusinessException {
    public DubboProviderException(IMessage errCode) {
        super(errCode);
    }

    public DubboProviderException(IMessage errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
