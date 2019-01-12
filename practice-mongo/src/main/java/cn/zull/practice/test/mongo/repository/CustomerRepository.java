package cn.zull.practice.test.mongo.repository;

import cn.zull.practice.test.mongo.pojo.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zurun
 * @date 2018/10/24 14:49:23
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
