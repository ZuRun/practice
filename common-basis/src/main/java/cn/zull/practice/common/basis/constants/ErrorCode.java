package cn.zull.practice.common.basis.constants;

/**
 * 错误码枚举类,不同模块中各自建立此类型接口并继承此接口
 * 此接口为通用错误码
 * <p>
 * 业务的错误码 约定为 4位
 * 第1.2位 为模块
 * 第3.4位 错误码排序
 * <p>
 *
 * 非业务错误码:
 * 800-810 : ASSERT
 * 820-830 : DistributedLock
 * 840-850: rocketmq
 * @author zurun
 * @date 2018/3/20 23:34:24
 */
public interface ErrorCode {

    enum common implements IMessage {
        /**
         *
         */
        REQUEST_NOT_FOUND(404, "未找到请求路径!"),
        DEFAULT_ERROR_CODE(980, "请求失败!"),
        DEFAULT_BUSINESS_EXCEPTION_CODE(990, "业务异常"),
        DEFAULT_EXCEPTION_CODE(991, "业务异常"),
        DEFAULT_FAIL_CODE(999, "默认异常"),


        ;

        private Integer errCode;
        private String errMsg;


        common(Integer errCode, String errMsg) {
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

    enum distributedLock implements IMessage {
        /**
         *
         */
        DEFAULT_LOCK_FAIL(820, "分布式锁异常"),
        DISTRIBUTED_LOCK_INTERRUPTED_EXCEPTION(821, "分布式锁中断异常"),
        GET_LOCK_TIMEOUT(822, "获取锁超时失败"),

        ;

        private Integer errCode;
        private String errMsg;


        distributedLock(Integer errCode, String errMsg) {
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

    enum ASSERT implements IMessage {
        /**
         *
         */
        ILLEGAL_ARGUMENT(800, "非法参数错误"),
        NOTNULL(801, "[Assertion failed] - the object argument must be null"),
        NOTEMPTY(802, "[Assertion failed] - this object must not be empty"),
        NOTTRUE(803, "this state invariant must be true"),
        NOTEQUALS(804, "this state invariant must be true"),
        ;

        private Integer errCode;
        private String errMsg;


        ASSERT(Integer errCode, String errMsg) {
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
