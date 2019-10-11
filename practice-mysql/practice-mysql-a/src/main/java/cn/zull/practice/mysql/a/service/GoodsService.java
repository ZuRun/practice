package cn.zull.practice.mysql.a.service;

import cn.zull.practice.common.basis.utils.RandomUtils;
import cn.zull.practice.mysql.a.config.BizExecutor;
import cn.zull.practice.mysql.a.config.BizThreadType;
import cn.zull.practice.mysql.a.entity.Goods;
import cn.zull.practice.mysql.a.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zurun
 * @date 2019/10/10 16:35:47
 */
@Slf4j
@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    BizExecutor executor;

    public void test() {
        Goods goods = new Goods();
        goods.setName("A1");
        goods.setSpec("0.01g/ml");
        Integer count = goodsMapper.insert(goods);
        log.info("[插入商品] count:{}", count);
    }

    /**
     * 初始化数据1000条商品
     */
    public void initializeData() {
        final int size = 1000;
        for (int i = 1; i <= size; i++) {
            final int index = i;
            executor.execute(() -> {
                Goods goods = new Goods();
                goods.setId(index);
                goods.setName("A" + index);
                goods.setSpec(RandomUtils.randomNumber(1000) + "g");
                goodsMapper.insert(goods);
            }, BizThreadType.GOODS_INITIALIZE);

        }
    }
}
