package cn.zull.practice.mysql.a.service;

import cn.zull.practice.common.basis.utils.RandomUtils;
import cn.zull.practice.mysql.a.config.BizExecutor;
import cn.zull.practice.mysql.a.config.BizThreadType;
import cn.zull.practice.mysql.a.entity.OrderInfo;
import cn.zull.practice.mysql.a.entity.OrderItem;
import cn.zull.practice.mysql.a.mapper.OrderInfoMapper;
import cn.zull.practice.mysql.a.mapper.OrderItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zurun
 * @date 2019/10/11 11:48:50
 */
@Slf4j
@Service
public class OrderService {
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    BizExecutor bizExecutor;


    public void initializeData() {

    }

    @Transactional(rollbackFor = Exception.class)
    public void insert() {
        String orderNumber = createOrderNumber();
        int buyerId = RandomUtils.getRangeNum(1, 500_0000);

        List<OrderItem> orderItemList = new ArrayList<>(8);
        int itemSize = RandomUtils.getRangeNum(1, 5);
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < itemSize; i++) {
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setOrderNumber(orderNumber)
                    .setGoodsId(RandomUtils.getRangeNum(1, 1000))
                    .setGoodsNumber(new BigDecimal(RandomUtils.randomNumber(10)))
                    .setGoodsPrice(new BigDecimal(RandomUtils.getRangeNum(15, 131)))
                    .setGoodsAmount(orderItem1.getGoodsPrice().multiply(orderItem1.getGoodsNumber()));
            sum = sum.add(orderItem1.getGoodsAmount());
            orderItemList.add(orderItem1);
        }

        OrderInfo orderInfo = new OrderInfo()
                .setCreateTime(new Date())
                .setOrderNumber(orderNumber)
                .setBuyerId(buyerId)
                .setOrderStatus(RandomUtils.randomNumber(10) == 1 ? 1 : 0)
                .setOrderAmount(sum);
        orderInfoMapper.insert(orderInfo);
        orderItemList.forEach(orderItem -> {
            orderItem.setOrderId(orderInfo.getOrderId());
            orderItemMapper.insert(orderItem);

        });
    }

    public void batchInsert() {
        for (int i = 0; i < 50_0000; i++) {
            bizExecutor.execute(()->{
                insert();
            }, BizThreadType.ORDER_BATCH_INSERT);
        }
    }

    private static final AtomicInteger NUM = new AtomicInteger(0);

    /**
     * 创建订单编号
     *
     * @return
     */
    private static String createOrderNumber() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String strDate = dtf.format(localDate);

        return "D" + strDate + n();
    }

    /**
     * 返回编码,规则00000-99999字符串
     *
     * @return
     */
    private static String n() {
        while (true) {
            int i = NUM.get();
            int newNum = i >= 99999 ? 1 : i + 1;
            boolean bl = NUM.compareAndSet(i, newNum);
            if (bl) {
                return String.format("%05d", newNum);
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            String s = createOrderNumber();
            System.out.println(s);
        }

    }
}
