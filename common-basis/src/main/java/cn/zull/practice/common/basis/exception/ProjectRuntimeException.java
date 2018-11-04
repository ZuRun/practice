package cn.zull.practice.common.basis.exception;


import cn.zull.practice.common.basis.constants.ErrorCode;
import cn.zull.practice.common.basis.constants.IMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 项目中所有异常均继承此异常,不要自定义检查异常
 *
 * @author zurun
 * @date 2018/6/15 16:17:05
 */
public class ProjectRuntimeException extends RuntimeException {
    /**
     * 异常码/错误码
     */
    protected IMessage errCode;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public ProjectRuntimeException(ProjectRuntimeException cause) {
        super(cause);
        this.errCode = cause.getErrorCode();
    }

    public ProjectRuntimeException(String errMsg) {
        super(errMsg);
        errCode = ErrorCode.common.DEFAULT_EXCEPTION_CODE;
    }

    public ProjectRuntimeException(IMessage errCode) {
        super(errCode.getErrMsg());
        this.errCode = errCode;
    }

    protected ProjectRuntimeException(IMessage errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }


    public Integer getErrCode() {
        return errCode.getErrCode();
    }

    /**
     * 慎用，因为实际错误信息不一定对应此枚举的错误信息
     *
     * @return
     */
    @Deprecated
    public IMessage getErrorCode() {
        return errCode;
    }


    @Override
    public String getMessage() {
        return StringUtils.isEmpty(super.getMessage()) ? errCode.getErrMsg() : super.getMessage();
    }
}
