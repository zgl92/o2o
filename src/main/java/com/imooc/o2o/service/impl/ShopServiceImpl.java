package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.uitl.FileUtil;
import com.imooc.o2o.uitl.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;


@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        //1.控值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //2.添加店铺初始信息
            shop.setEnableStatus(0); //默认状态
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            //3.添加店铺信息
            int effectedNum = shopDao.insertShop(shop);

            //4.判断操作状态
            if (effectedNum <= 0) {
                throw new RuntimeException("店铺创建失败");
            } else {

                //5.获取shopId,进行下一步操作
                if (shopImg != null) {
                    //6存储图片

                    try {
                        addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        throw new RuntimeException("addShopImg error创建失败" + e.getMessage());
                    }

                    //7更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new RuntimeException("更新图片地址失败");
                    }
                }
            }


        } catch (Exception e) {
            throw new RuntimeException("addShop error" + e.getMessage());
        }

        return null;
    }

    /**
     * 存储图片
     * @param shop
     * @param shopImg
     */
    private void addShopImg(Shop shop, File shopImg) {
       //获取shop图片目录的相对路径
        String dest = FileUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg , dest);
        shop.setShopImg(shopImgAddr);

    }
}
