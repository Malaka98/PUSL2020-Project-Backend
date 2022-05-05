package com.pusl2020project.groupproject.service;

import org.springframework.core.io.Resource;

public interface IFileStorageService {
    Resource downloadFile(String fileName);
}
