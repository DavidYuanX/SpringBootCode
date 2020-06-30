package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    private final String OPENID = "110110";

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123123");
        orderMaster.setBuyerName("David");
        orderMaster.setBuyerPhone("123123123123");
        orderMaster.setBuyerAddress("Mu渴望");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0,1);

        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID,request);
        Assert.assertNotEquals(0,result.getTotalElements());
    }
}