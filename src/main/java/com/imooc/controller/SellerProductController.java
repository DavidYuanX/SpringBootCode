package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.excetion.SellException;
import com.imooc.form.ProductForm;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){

        PageRequest request = PageRequest.of(page-1,size);

        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                             Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(form,productInfo);

        try{
            if(StringUtils.isEmpty(productInfo.getProductId())){
                productInfo.setProductId(KeyUtil.genUniqueKey());
            }
            if(productInfo.getProductStatus() == null){
                productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            }
            productService.save(productInfo);
        } catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/off_sale")
    public ModelAndView off_sale(@RequestParam("productId") String productId,
                              Map<String,Object> map){
        map.put("url","/sell/seller/product/list");
        try {
            productService.OffSale(productId);
        }catch (SellException e){
            log.error(e.getMessage());
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/on_sale")
    public ModelAndView on_sale(@RequestParam("productId") String productId,
                                 Map<String,Object> map){
        map.put("url","/sell/seller/product/list");
        try {
            productService.OnSale(productId);
        }catch (SellException e){
            log.error(e.getMessage());
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMessage());

        return new ModelAndView("common/success", map);
    }
}
