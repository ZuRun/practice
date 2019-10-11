package cn.zull.practice.mysql.a.service;

import cn.zull.practice.common.basis.utils.RandomUtils;
import cn.zull.practice.mysql.a.config.BizExecutor;
import cn.zull.practice.mysql.a.config.BizThreadType;
import cn.zull.practice.mysql.a.entity.BuyerInfo;
import cn.zull.practice.mysql.a.mapper.BuyerInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zurun
 * @date 2019/10/11 10:26:22
 */
@Slf4j
@Service
public class BuyerInfoService {

    @Autowired
    BuyerInfoMapper buyerInfoMapper;
    @Autowired
    BizExecutor executor;

    /**
     * 初始化数据 500_0000 条用户数据
     */
    public void initializeData() {
        final int size = 500_0000;
        for (int i = 1; i <= size; i++) {
            final int index = i;
            executor.execute(() -> {
                BuyerInfo buyerInfo = new BuyerInfo();
                buyerInfo.setBuyerId(index);
                buyerInfo.setName("张" + index);
                buyerInfo.setSex(RandomUtils.randomNumber(2));
                buyerInfo.setRegisterTime(new Date());
                buyerInfoMapper.insert(buyerInfo);
            }, BizThreadType.BUYER_INITIALIZE);
            if (RandomUtils.randomNumber(1000) == 1) {
                System.out.println("初始用户数:" + i);
            }
        }
    }
}
