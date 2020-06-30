package com.imooc.service.impl;

import com.imooc.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import com.imooc.dataobject.OrderDTO;
import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j

public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "110110";
    private final String ORDER_OPENID = "123123";

    @Test
    public void caeate() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("David");
        orderDTO.setBuyerAddress("南山智园");
        orderDTO.setBuyerPhone("123123123123");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.caeate(orderDTO);

        log.info("【创建订单】result={}",result);
    }

    @Test
    public void findOne() throws Exception {
       OrderDTO result = orderService.findOne(ORDER_OPENID);
       log.info("查询单个订单：result={}",result);
        Assert.assertEquals(ORDER_OPENID,result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }
}