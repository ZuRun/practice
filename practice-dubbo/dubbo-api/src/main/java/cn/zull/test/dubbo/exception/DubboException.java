package cn.zull.test.dubbo.exception;

import cn.zull.practice.common.basis.constants.IMessage;
import cn.zull.practice.common.basis.exception.BusinessException;
import cn.zull.practice.common.basis.exception.ProjectRuntimeException;

/**
 * @author zurun
 * @date 2018/11/3 17:38:54
 */
public class DubboException extends BusinessException {
    public DubboException(ProjectRuntimeException cause) {
        super(cause);
    }

    public DubboException(IMessage errCode) {
        super(errCode);
    }

    public DubboException(IMessage errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
