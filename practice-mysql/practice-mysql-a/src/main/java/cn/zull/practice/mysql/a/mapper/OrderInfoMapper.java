package cn.zull.practice.mysql.a.mapper;

import cn.zull.practice.mysql.a.bo.OrderDetailBO;
import cn.zull.practice.mysql.a.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zurun
 * @date 2019/10/11 11:47:43
 */
@Repository
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    @Override
    int insert(OrderInfo orderInfo);

    List<OrderDetailBO> getOrderDetailsByUserId(@Param("buyerId") Integer buyerId);

    int updateStatus2Confirm(@Param("order_id") Integer orderId);
}
