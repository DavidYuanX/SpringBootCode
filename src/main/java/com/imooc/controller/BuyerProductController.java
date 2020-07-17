package com.imooc.controller;

import com.imooc.VO.PrductVo;
import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.imooc.utils.ResultVOUtil;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        // 1、查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2、查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
        // 传统方法
//        for (ProductInfo productInfo : productInfoList) {
//            categoryTypeList.add(productInfo.getCatrgoryType());
//        }
        // 精简方法（Java8, lambda)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 3、 数据拼装
        List<PrductVo> prductVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            PrductVo prductVo = new PrductVo();

            prductVo.setCategoryType(productCategory.getCategoryType());
            prductVo.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            prductVo.setProductInfoVOList(productInfoVOList);
            prductVoList.add(prductVo);
        }

        return ResultVOUtil.success(prductVoList);

//        ResultVO resultVO = new ResultVO();

//        PrductVo prductVo = new PrductVo();
//
//        ProductInfoVO productInfoVO = new ProductInfoVO();
//
//        prductVo.setProductInfoVOList(Arrays.asList(productInfoVO));

//        resultVO.setData(prductVoList);
//
//        resultVO.setCode(0);
//        resultVO.setMsg("成功");
//        return resultVO;
    }
}
