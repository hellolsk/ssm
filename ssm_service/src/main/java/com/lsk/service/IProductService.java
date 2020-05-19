package com.lsk.service;

import com.lsk.domain.Product;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/2/5 15:47
 * @Description:ssm com.lsk.service
 */
public interface IProductService {
    public List<Product> findAll();

    void save(Product product);
}
