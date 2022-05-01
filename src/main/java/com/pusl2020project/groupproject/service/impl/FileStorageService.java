package com.pusl2020project.groupproject.service.impl;


import com.pusl2020project.groupproject.exception.UnknownException;
import com.pusl2020project.groupproject.service.IFileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService implements IFileStorageService {

    private final String fileStorageLocation;

    public FileStorageService(@Value("${spring.servlet.multipart.location:temp}") String fileStorageLocation) throws IOException {

        this.fileStorageLocation = fileStorageLocation;
        Path fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        Files.createDirectories(fileStoragePath);
    }


    @Override
    public Resource downloadFile(String fileName) {

        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);
        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new UnknownException(e.getMessage() + " ⚠⚠⚠");
        }

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new UnknownException("The file doesn't exist or not readable ⚠⚠⚠");
        }
    }
}
