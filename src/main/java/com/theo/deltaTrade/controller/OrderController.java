package com.theo.deltaTrade.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theo.deltaTrade.common.ApiMsg;
import com.theo.deltaTrade.common.ApiResult;
import com.theo.deltaTrade.entity.Commodity;
import com.theo.deltaTrade.entity.Order;
import com.theo.deltaTrade.entity.User;
import com.theo.deltaTrade.service.CommodityService;
import com.theo.deltaTrade.service.OrderService;
import com.theo.deltaTrade.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private CommodityService commodityService;
    @Resource
    private UserService userService;

    /**
     * 添加订单
     *
     * @param order 订单实体
     */
    @PostMapping
    public ApiResult add(@RequestBody Order order) {
        Commodity commodity = commodityService.getById(order.getCommodityId());
        User seller = userService.getById(order.getSellerId());
        User buyer = userService.getById(order.getBuyerId());
        if (commodity == null) {
            return ApiResult.fail(ApiMsg.COMMODITY_NOT_EXIST);
        }
        if (seller == null || buyer == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
        if (seller.getId().equals(buyer.getId())) {
            return ApiResult.fail(ApiMsg.ORDER_BUYER_WRONG);
        }
        if (orderService.save(order)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail();
        }
    }

    /**
     * 根据id删除订单
     *
     * @param id 订单id
     */
    @DeleteMapping("/{id}")
    public ApiResult deleteById(@PathVariable Long id) {
        if (orderService.removeById(id)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail(ApiMsg.ORDER_NOT_EXIST);
        }
    }

    /**
     * 更新订单
     *
     * @param order 订单实体
     */
    @PutMapping
    public ApiResult update(@RequestBody Order order) {
        Commodity commodity = commodityService.getById(order.getCommodityId());
        User seller = userService.getById(order.getSellerId());
        User buyer = userService.getById(order.getBuyerId());
        if (commodity == null) {
            return ApiResult.fail(ApiMsg.COMMODITY_NOT_EXIST);
        }
        if (seller == null || buyer == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
        if (orderService.updateById(order)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail(ApiMsg.ORDER_NOT_EXIST);
        }
    }

    /**
     * 根据id获取订单
     *
     * @param id 订单id
     */
    @GetMapping("/{id}")
    public ApiResult getById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        if (order == null) {
            return ApiResult.fail(ApiMsg.ORDER_NOT_EXIST);
        } else {
            return ApiResult.success(order);
        }
    }

    /**
     * 根据用户id分页获取订单
     *
     * @param id       用户id
     * @param pageNum  页码
     * @param pageSize 页数
     */
    @GetMapping("/{id}/{pageNum}/{pageSize}")
    public ApiResult page(@PathVariable Long id, @PathVariable Long pageNum, @PathVariable Long pageSize) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getBuyerId, id).orderByDesc(Order::getUpdateTime);
        Page<Order> page = new Page<>(pageNum, pageSize);
        orderService.page(page, wrapper);
        return ApiResult.success(page);
    }

    /**
     * 获取买家全部购买订单
     *
     * @param id 用户id
     */
    @GetMapping("/buy/{id}")
    public ApiResult getAll(@PathVariable Long id) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getBuyerId, id);
        wrapper.orderByDesc(Order::getUpdateTime);
        List<Order> list = orderService.list(wrapper);
        if (list == null) {
            return ApiResult.fail();
        } else {
            return ApiResult.success(list);
        }
    }
}
