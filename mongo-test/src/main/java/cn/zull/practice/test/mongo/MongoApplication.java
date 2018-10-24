package cn.zull.practice.test.mongo;

import cn.zull.practice.test.mongo.pojo.BaseDeviceCmd;
import cn.zull.practice.test.mongo.pojo.Customer;
import cn.zull.practice.test.mongo.pojo.DeviceCmdStringType;
import cn.zull.practice.test.mongo.pojo.DeviceCmdStringTypeValue;
import cn.zull.practice.test.mongo.repository.CustomerRepository;
import cn.zull.practice.test.mongo.repository.DeviceCmdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zurun
 * @date 2018/10/24 14:34:50
 */
@SpringBootApplication
public class MongoApplication implements CommandLineRunner {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private DeviceCmdRepository deviceCmdRepository;


    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customer();
        deviceCmd();
    }

    void deviceCmd() {
        List<DeviceCmdStringTypeValue> list = new ArrayList<>();
        list.add(new DeviceCmdStringTypeValue("开", "OPEN"));
        list.add(new DeviceCmdStringTypeValue("关", "CLOSE"));

        DeviceCmdStringType deviceCmd = new DeviceCmdStringType();
        deviceCmd.setValues(list);
        deviceCmd.setAiuiKey("开关");
        deviceCmd.setCmdKey("CMD-SWITCH");
        deviceCmd.setCmdType("STRING");
        deviceCmd.setDeviceType("light");

        deviceCmdRepository.save(deviceCmd);

        List<BaseDeviceCmd> result = deviceCmdRepository.list("light");
        System.out.println(result);
    }

    void customer() {
        repository.deleteAll();

        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        System.out.println("Customers found with findAll");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }

        System.out.println();
        System.out.println("---------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("--------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}
