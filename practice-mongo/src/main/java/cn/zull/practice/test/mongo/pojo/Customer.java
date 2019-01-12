package cn.zull.practice.test.mongo.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author zurun
 * @date 2018/10/24 14:47:48
 */
@Data
public class Customer {
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
