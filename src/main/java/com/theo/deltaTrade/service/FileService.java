package com.theo.deltaTrade.service;


import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile multipartFile);
}
