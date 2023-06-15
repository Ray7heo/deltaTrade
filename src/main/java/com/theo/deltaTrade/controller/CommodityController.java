package com.theo.deltaTrade.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theo.deltaTrade.common.ApiMsg;
import com.theo.deltaTrade.common.ApiResult;
import com.theo.deltaTrade.entity.Category;
import com.theo.deltaTrade.entity.Commodity;
import com.theo.deltaTrade.entity.User;
import com.theo.deltaTrade.service.CategoryService;
import com.theo.deltaTrade.service.CommodityService;
import com.theo.deltaTrade.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commodity")
@Slf4j
public class CommodityController {
    @Resource
    private CommodityService commodityService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private UserService userService;

    /**
     * 添加商品
     *
     * @param commodity 商品实体
     */
    @PostMapping
    public ApiResult add(@RequestBody Commodity commodity) {
        User user = userService.getById(commodity.getSellerId());
        if (user == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
        if (commodityService.save(commodity)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail();
        }
    }

    /**
     * 根据id删除商品
     *
     * @param id 商品id
     */
    @DeleteMapping("/{id}")
    public ApiResult deleteById(@PathVariable Long id) {
        if (commodityService.removeById(id)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail(ApiMsg.COMMODITY_NOT_EXIST);
        }
    }

    /**
     * 更新商品
     *
     * @param commodity 商品实体
     */
    @PutMapping
    public ApiResult update(@RequestBody Commodity commodity) {
        User user = userService.getById(commodity.getSellerId());
        if (user == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
        if (commodityService.updateById(commodity)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail();
        }
    }

    /**
     * 根据id获取商品
     *
     * @param id 商品id
     */
    @GetMapping("/{id}")
    public ApiResult getById(@PathVariable Long id) {
        Commodity commodity = commodityService.getById(id);
        if (commodity == null) {
            return ApiResult.fail(ApiMsg.COMMODITY_NOT_EXIST);
        } else {
            return ApiResult.success(commodity);
        }
    }

    /**
     * 分页获取商品
     *
     * @param pageNum  页码
     * @param pageSize 页数
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public ApiResult page(@PathVariable Long pageNum, @PathVariable Long pageSize) {
        LambdaQueryWrapper<Commodity> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(Commodity::getIsSold, 1).orderByDesc(Commodity::getUpdateTime);
        Page<Commodity> page = new Page<>(pageNum, pageSize);
        commodityService.page(page, wrapper);
        return ApiResult.success(page);
    }

    /**
     * 根据分类id分页获取商品
     *
     * @param category 分类实体
     * @param pageNum  页码
     * @param pageSize 页数
     */
    @GetMapping("category/{pageNum}/{pageSize}")
    public ApiResult pageByCategory(@RequestBody Category category, @PathVariable Long pageNum,
                                    @PathVariable Long pageSize) {
        LambdaQueryWrapper<Commodity> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(Commodity::getIsSold, 1).eq(Commodity::getCategoryId, category.getId()).orderByDesc(Commodity::getUpdateTime);
        Page<Commodity> page = new Page<>(pageNum, pageSize);
        commodityService.page(page, wrapper);
        return ApiResult.success(page);
    }

    /**
     * 根据用户id分页获取正在出售的商品
     *
     * @param sellerId 卖家id
     * @param pageNum  页码
     * @param pageSize 页数
     */
    @GetMapping("selling/{sellerId}/{pageNum}/{pageSize}")
    public ApiResult pageSelling(@PathVariable Long sellerId, @PathVariable Long pageNum, @PathVariable Long pageSize) {
        LambdaQueryWrapper<Commodity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Commodity::getSellerId, sellerId).ne(Commodity::getIsSold, 1).orderByDesc(Commodity::getUpdateTime);
        Page<Commodity> page = new Page<>(pageNum, pageSize);
        commodityService.page(page, wrapper);
        return ApiResult.success(page);
    }

    /**
     * 根据用户id分页获取已出售的商品
     *
     * @param sellerId 卖家id
     * @param pageNum  页码
     * @param pageSize 页数
     */
    @GetMapping("sold/{sellerId}/{pageNum}/{pageSize}")
    public ApiResult pageSold(@PathVariable Long sellerId, @PathVariable Long pageNum, @PathVariable Long pageSize) {
        LambdaQueryWrapper<Commodity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Commodity::getSellerId, sellerId).eq(Commodity::getIsSold, 1).orderByDesc(Commodity::getUpdateTime);
        Page<Commodity> page = new Page<>(pageNum, pageSize);
        commodityService.page(page, wrapper);
        return ApiResult.success(page);
    }

    /**
     * 根据商品名或分类名搜索商品
     *
     * @param name     关键词
     * @param pageNum  页码
     * @param pageSize 页数
     */
    @GetMapping("/search/{pageNum}/{pageSize}")
    public ApiResult pageSearch(String name, @PathVariable Long pageNum, @PathVariable Long pageSize) {
        LambdaQueryWrapper<Commodity> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(Commodity::getIsSold, 1);
        Category category = categoryService.getOne(new LambdaQueryWrapper<Category>().like(Category::getName, name));
        if (category != null) {
            wrapper.and(w-> w.eq(Commodity::getCategoryId, category.getId()));
        }
        wrapper.or().like(Commodity::getName, name).orderByDesc(Commodity::getUpdateTime);
        Page<Commodity> page = new Page<>(pageNum, pageSize);
        commodityService.page(page, wrapper);
        return ApiResult.success(page);
    }
}
