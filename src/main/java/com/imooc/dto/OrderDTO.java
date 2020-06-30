package com.imooc.dto;

import com.imooc.dataobject.OrderDetail;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
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
