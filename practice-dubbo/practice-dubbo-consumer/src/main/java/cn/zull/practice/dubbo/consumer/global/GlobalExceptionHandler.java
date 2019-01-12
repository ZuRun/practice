package cn.zull.practice.dubbo.consumer.global;


import cn.zull.practice.common.basis.constants.ErrorCode;
import cn.zull.practice.common.basis.constants.Result;
import cn.zull.practice.common.basis.exception.BusinessException;
import cn.zull.practice.common.basis.exception.ProjectRuntimeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;

/**
 * 全局异常处理
 * 此项目相对较简单,不存在多个微服务之间的通信问题,所有异常处理方式都一样
 * 因为是内部使用,所以所有类型的异常,返回的错误信息,都可以直接返回给调用方
 * <p>
 * 不同类型的异常具体返回的http状态码,根据业务来调整,此项目可以都返回200,调用方根据errCode来判断是否成功
 *
 * @author zurun
 * @date 2018/6/20 15:32:27
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 自定义业务异常
     *
     * @param e
     * @return 用于springBoot框架返回response的body
     * @ResponseStatus 返回的 HTTP 状态码为 500
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result> handlerException(BusinessException e) {
        e.printStackTrace();
        HttpHeaders headers = createHeaders();

        return new ResponseEntity<>(Result.fail(e.getErrCode(), e.getMessage()), headers, HttpStatus.OK);

    }

    /**
     * 自定义非业务运行异常
     *
     * @param e
     * @return 用于springBoot框架返回response的body
     * @ResponseStatus 返回的 HTTP 状态码为 500
     */
    @ExceptionHandler(ProjectRuntimeException.class)
    public ResponseEntity<Result> handlerException(ProjectRuntimeException e) {
        e.printStackTrace();
        HttpHeaders headers = createHeaders();
        return new ResponseEntity<>(Result.fail(e.getErrCode(), e.getMessage()), headers, HttpStatus.OK);

    }

    /**
     * 异常,直接返回错误信息
     *
     * @param e
     * @return 用于springBoot框架返回response的body
     * @ResponseStatus 返回的 HTTP 状态码为 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handlerException(Exception e) {
        e.printStackTrace();
        HttpHeaders headers = createHeaders();
        return new ResponseEntity<>(Result.fail(ErrorCode.common.DEFAULT_EXCEPTION_CODE.getErrCode(), e.getMessage()), headers, HttpStatus.OK);
    }

    /**
     * 404页面
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Result> handlerException(NoHandlerFoundException e) {
        HttpHeaders headers = createHeaders();
        return new ResponseEntity<>(Result.fail(404, "未找到请求路径！"), headers, HttpStatus.NOT_FOUND);
    }

    /**
     * 请求method错误(post、get)
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Result> handlerException(HttpRequestMethodNotSupportedException e) {
        HttpHeaders headers = createHeaders();
        return new ResponseEntity<>(Result.fail(404, "未找到请求路径！").addResult(e.getMessage()), headers, HttpStatus.NOT_FOUND);
    }

    /**
     * 请求错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServletException.class)
    public ResponseEntity<Result> handlerException(ServletException e) {
        HttpHeaders headers = createHeaders();
        return new ResponseEntity<>(Result.fail(400, "请求错误！").addResult(e.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }


    /**
     * 生成header
     *
     * @return
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }
}
