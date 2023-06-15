package com.theo.deltaTrade.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.theo.deltaTrade.common.ApiMsg;
import com.theo.deltaTrade.common.ApiResult;
import com.theo.deltaTrade.entity.Favorite;
import com.theo.deltaTrade.service.CommodityService;
import com.theo.deltaTrade.service.FavoriteService;
import com.theo.deltaTrade.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Resource
    private FavoriteService favoriteService;
    @Resource
    private UserService userService;
    @Resource
    private CommodityService commodityService;


    /**
     * 添加收藏
     *
     * @param favorite 收藏实体
     */
    @PostMapping
    public ApiResult add(@RequestBody Favorite favorite) {
        if (userService.getById(favorite.getUserId()) == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
        if (commodityService.getById(favorite.getCommodityId()) == null) {
            return ApiResult.fail(ApiMsg.COMMODITY_NOT_EXIST);
        }
        if (favoriteService.save(favorite)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail();
        }
    }

    /**
     * 根据id删除收藏
     *
     * @param id 收藏id
     */
    @DeleteMapping("/{id}")
    public ApiResult deleteById(@PathVariable Long id) {
        if (favoriteService.removeById(id)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail(ApiMsg.FAVORITE_NOT_EXIST);
        }
    }

    /**
     * 更新收藏
     *
     * @param favorite 收藏实体
     */
    @PutMapping
    public ApiResult update(@RequestBody Favorite favorite) {
        if (userService.getById(favorite.getUserId()) == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
        if (commodityService.getById(favorite.getCommodityId()) == null) {
            return ApiResult.fail(ApiMsg.COMMODITY_NOT_EXIST);
        }
        if (favoriteService.updateById(favorite)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail();
        }
    }

    /**
     * 根据用户id获取全部收藏
     *
     * @param id 用户id
     */
    @GetMapping("/{id}")
    public ApiResult getAllByUserId(@PathVariable Long id) {
        if (userService.getById(id) == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
        LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorite::getUserId, id).orderByDesc(Favorite::getCreateTime);
        List<Favorite> list = favoriteService.list(wrapper);
        if (list == null) {
            return ApiResult.fail();
        } else {
            return ApiResult.success(list);
        }
    }

    /**
     * 根据用户id分页获取收藏
     *
     * @param id       用户id
     * @param pageNum  页码
     * @param pageSize 页数
     */
    @GetMapping("/{id}/{pageNum}/{pageSize}")
    public ApiResult pageByUserId(@PathVariable Long id, @PathVariable Long pageNum, @PathVariable Long pageSize) {
        if (userService.getById(id) == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
        LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorite::getUserId, id).orderByDesc(Favorite::getCreateTime);
        Page<Favorite> page = new Page<>(pageNum, pageSize);
        favoriteService.page(page, wrapper);
        return ApiResult.success(page);
    }

    /**
     * 根据用户商品id和用户id获取收藏
     *
     * @param commodityId 商品id
     * @param userId      用户id
     */
    @GetMapping("/{commodityId}/{userId}")
    public ApiResult isCollect(@PathVariable Long commodityId, @PathVariable Long userId) {
        LambdaQueryWrapper<Favorite> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favorite::getCommodityId, commodityId);
        queryWrapper.eq(Favorite::getUserId, userId);
        Favorite favorite = favoriteService.getOne(queryWrapper);
        if (favorite == null) {
            return ApiResult.fail(ApiMsg.FAVORITE_NOT_EXIST);
        } else {
            return ApiResult.success(favorite);
        }
    }
}
