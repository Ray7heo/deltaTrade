package com.theo.deltaTrade.controller;

import com.theo.deltaTrade.common.ApiMsg;
import com.theo.deltaTrade.common.ApiResult;
import com.theo.deltaTrade.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileController {

    @Resource
    FileService fileService;

    /**
     * 文件上传
     *
     * @param multipartFile 文件
     */
    @PostMapping("/upload")
    public ApiResult add(@RequestParam MultipartFile multipartFile) {
        String filePath = fileService.upload(multipartFile);
        if (filePath == null) {
            return ApiResult.fail(ApiMsg.FILE_UPLOAD_FAIL);
        } else {
            return ApiResult.success(filePath);
        }
    }

}
