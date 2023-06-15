package com.theo.deltaTrade.controller;

import com.theo.deltaTrade.common.ApiMsg;
import com.theo.deltaTrade.common.ApiResult;
import com.theo.deltaTrade.entity.Category;
import com.theo.deltaTrade.service.CategoryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 添加分类
     *
     * @param category 分类实体
     */
    @PostMapping
    public ApiResult add(@RequestBody Category category) {
        if (categoryService.save(category)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail();
        }
    }

    /**
     * 根据id删除分类
     *
     * @param id 分类id
     */
    @DeleteMapping("/{id}")
    public ApiResult deleteById(@PathVariable Long id) {
        if (categoryService.removeById(id)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail(ApiMsg.CATEGORY_NOT_EXIST);
        }
    }

    /**
     * 更新分类
     *
     * @param category 分类实体
     */
    @PutMapping
    public ApiResult update(@RequestBody Category category) {
        if (categoryService.updateById(category)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail(ApiMsg.CATEGORY_NOT_EXIST);
        }
    }

    /**
     * 根据id获取分类
     *
     * @param id 分类id
     */
    @GetMapping("/{id}")
    public ApiResult getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return ApiResult.fail(ApiMsg.CATEGORY_NOT_EXIST);
        } else {
            return ApiResult.success(category);
        }
    }

    /**
     * 获取全部分类
     *
     */
    @GetMapping
    public ApiResult getAll() {
        return ApiResult.success(categoryService.list());
    }
}
