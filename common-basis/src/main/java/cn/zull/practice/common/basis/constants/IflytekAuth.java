package cn.zull.practice.common.basis.constants;

import java.lang.annotation.*;

/**
 * @author zurun
 * @date 2018/12/29 17:03:12
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IflytekAuth {

    AuthType authType()  ;
}
