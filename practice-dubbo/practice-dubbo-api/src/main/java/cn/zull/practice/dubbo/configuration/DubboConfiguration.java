package cn.zull.practice.dubbo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zurun
 * @date 2018/11/6 14:50:23
 */
@Configuration
@PropertySource(value = "classpath:/dubbo.properties")
public class DubboConfiguration {

}
