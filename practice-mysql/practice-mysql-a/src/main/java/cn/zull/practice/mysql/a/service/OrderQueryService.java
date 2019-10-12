package cn.zull.practice.mysql.a.service;

import cn.zull.practice.mysql.a.bo.OrderDetailBO;
import cn.zull.practice.mysql.a.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zurun
 * @date 2019/10/12 15:06:38
 */
@Slf4j
@Service
public class OrderQueryService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    public List<OrderDetailBO> getOrderDetailsByUserId(int buyerId) {
        List<OrderDetailBO> orderDetails = orderInfoMapper.getOrderDetailsByUserId(buyerId);
        Set<Integer> orderIds = new HashSet<>();
        orderDetails.forEach(orderDetailBO -> {
            orderIds.add(orderDetailBO.getOrderId());
        });
        orderIds.forEach(orderId -> {
            orderInfoMapper.updateStatus2Confirm(orderId);
        });
        return orderDetails;
    }
}
