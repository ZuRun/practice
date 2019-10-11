package cn.zull.practice.mysql.a.mapper;

import cn.zull.practice.mysql.a.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zurun
 * @date 2019/10/11 11:47:43
 */
@Repository
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    @Override
    int insert(OrderInfo orderInfo);
}
