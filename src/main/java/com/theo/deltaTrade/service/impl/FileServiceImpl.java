package com.theo.deltaTrade.service.impl;

import com.theo.deltaTrade.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public String upload(MultipartFile multipartFile) {
        //获取文件名
        String fileName = multipartFile.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID() + suffixName;
        //指定本地文件夹存储图片，写到需要保存的目录下
        try {
            multipartFile.transferTo(new File(fileDir + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return "/" + fileName;
    }
}
