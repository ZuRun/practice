package cn.zull.practice.mysql.a;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zurun
 * @date 2019/10/10 15:52:08
 */
@SpringBootApplication
@MapperScan("cn.zull.practice.mysql.a.mapper.*")
public class MysqlTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlTestApplication.class, args);
    }
}
