package com.theo.deltaTrade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.theo.deltaTrade.entity.Commodity;
import com.theo.deltaTrade.entity.Order;
import com.theo.deltaTrade.service.CommodityService;
import com.theo.deltaTrade.service.OrderService;
import com.theo.deltaTrade.mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Theo
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-05-29 12:40:33
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Resource
    CommodityService commodityService;

    @Transactional
    @Override
    public boolean save(Order entity) {
        Commodity commodity = new Commodity();
        commodity.setId(entity.getCommodityId());
        commodity.setIsSold(1);
        commodityService.updateById(commodity);
        return super.save(entity);
    }
}




