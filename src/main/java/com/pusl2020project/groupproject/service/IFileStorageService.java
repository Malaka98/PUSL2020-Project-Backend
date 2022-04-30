package com.pusl2020project.groupproject.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
    Resource downloadFile(String fileName);
}
