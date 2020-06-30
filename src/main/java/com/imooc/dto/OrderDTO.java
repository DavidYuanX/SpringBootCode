package com.imooc.dataobject;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderDTO {

    // 订单Id
    private String orderId;

    // 买家名字
    private String buyerName;

    // 买家手机号
    private String buyerPhone;

    // 买家地址
    private String buyerAddress;

    // 买家微信OpenId
    private String buyerOpenid;

    // 订单金额
    private BigDecimal orderAmount;

    // 订单状态
    private Integer orderStatus;

    // 支付状态
    private Integer payStatus;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
