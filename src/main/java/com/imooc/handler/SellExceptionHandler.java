package com.imooc.handler;

import com.imooc.excetion.SellerAuthorzeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class SellExceptionHandler {

    // 拦截登陆异常
    @ExceptionHandler(value = SellerAuthorzeException.class)
    public ModelAndView handlerAuthorzeException(){
        return new ModelAndView("redirect:/");
    }
}
