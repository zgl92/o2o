package com.imooc.o2o.service;


import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public interface ShopService {

    /**
     * 添加店铺
     * @param shop 店铺信息
     * @param shopImg 店铺图片
     * @return
     */
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);
}
