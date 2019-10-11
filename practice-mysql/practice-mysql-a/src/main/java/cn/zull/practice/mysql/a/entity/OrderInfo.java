package cn.zull.practice.mysql.a.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zurun
 * @date 2019/10/10 17:06:31
 */
@Data
@Accessors(chain = true)
@TableName("t_order_info")
public class OrderInfo {
    @TableId(value = "`order_id`", type = IdType.AUTO)
    private Integer orderId;

    private String orderNumber;

    private Integer buyerId;
    /**
     * 状态 0 进行中 1 完成 2 取消 3 已结算
     */
    private Integer orderStatus;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
    private Date createTime;
}