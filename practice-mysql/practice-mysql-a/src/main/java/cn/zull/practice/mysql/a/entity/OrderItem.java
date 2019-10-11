package cn.zull.practice.mysql.a.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author zurun
 * @date 2019/10/11 11:45:48
 */
@Data
@Accessors(chain = true)
@TableName("t_order_item")
public class OrderItem {
    private Integer itemId;
    private Integer orderId;

    private String orderNumber;
    private Integer goodsId;

    private BigDecimal goodsNumber;
    private BigDecimal goodsPrice;
    private BigDecimal goodsAmount;

}
