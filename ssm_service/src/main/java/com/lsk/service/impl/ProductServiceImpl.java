package com.lsk.service.impl;

import com.lsk.dao.IProductDao;
import com.lsk.domain.Product;
import com.lsk.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:${六月的雨}
 * @Date:2020/2/5 15:48
 * @Description:ssm com.lsk.service.impl
 */

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Secured("ROLE_ADMIN")//有admin角色的用户才能访问
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
