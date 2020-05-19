package com.lsk.controller;

import com.lsk.domain.Product;
import com.lsk.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

/**
 * @Author:${六月的雨}
 * @Date:2020/2/5 16:47
 * @Description:ssm com.lsk.controller
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 添加商品
     * @param product
     * @return
     */
    @RequestMapping("/save")
    public String save(Product product){
        UUID uuid = UUID.randomUUID();
        product.setId(uuid.toString());
        productService.save(product);

        return "redirect:findAll";
    }
    /**
     * 查询所有产品
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.findAll();
        //测试输出
        for(Product products : productList){
            System.out.println(products);
        }
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
}
