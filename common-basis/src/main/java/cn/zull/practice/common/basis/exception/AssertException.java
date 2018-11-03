package cn.zull.practice.common.basis.exception;


import cn.zull.practice.common.basis.constants.ErrorCode;
import cn.zull.practice.common.basis.constants.IMessage;

/**
 * @author zurun
 * @date 2018/9/17 21:41:11
 */
public class AssertException extends ProjectRuntimeException {
    public AssertException(String errMsg) {
        super(ErrorCode.ASSERT.ILLEGAL_ARGUMENT, errMsg);
    }

    public AssertException(IMessage errCode) {
        super(errCode);
    }

    public AssertException(IMessage errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
