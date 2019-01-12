package cn.zull.test.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zurun
 * @date 2018/10/1 22:04:23
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "cn.zull.test")
@ComponentScan("cn.zull")
public class ConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(ConsumerApplication.class, args);

    }

}
