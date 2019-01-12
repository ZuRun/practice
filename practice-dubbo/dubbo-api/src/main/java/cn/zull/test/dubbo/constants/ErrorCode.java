package cn.zull.test.dubbo.constants;

import cn.zull.practice.common.basis.constants.IMessage;

/**
 * @author zurun
 * @date 2018/11/3 00:25:11
 */
public interface ErrorCode extends cn.zull.practice.common.basis.constants.ErrorCode {
    enum dubbo implements IMessage {
        /**
         *
         */
        DUBBO_DEFAULT_ERROR(1101, "DUBBO默认错误"),

        ;

        private Integer errCode;
        private String errMsg;


        dubbo(Integer errCode, String errMsg) {
            this.errCode = errCode;
            this.errMsg = errMsg;
        }

        @Override
        public Integer getErrCode() {
            return this.errCode;
        }

        @Override
        public String getErrMsg() {
            return this.errMsg;
        }
    }
}
