package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    // 查询所有在架商品
    List<ProductInfo> findUpAll();

    List<ProductInfo> findAll();

    // 查询所有
    Page<ProductInfo> findAll(Pageable pageable);

    // 保存
    ProductInfo save(ProductInfo productInfo);

    // 加库存

    // 减库存
}