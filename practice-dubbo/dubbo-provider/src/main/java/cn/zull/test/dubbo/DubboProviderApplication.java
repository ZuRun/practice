package cn.zull.test.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zurun
 * @date 2018/9/16 23:22:47
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "cn.zull.test")
public class DubboProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(DubboProviderApplication.class, args);
    }
}
