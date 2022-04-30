package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.entity.Photos;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.repository.IAccidentRepository;
import com.pusl2020project.groupproject.repository.IPhotoRepository;
import com.pusl2020project.groupproject.repository.IUserRepository;
import com.pusl2020project.groupproject.service.IAccidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@Slf4j
public class AccidentService implements IAccidentService {

    private final IAccidentRepository iAccidentRepository;
    private final IUserRepository iUserRepository;
    private final IPhotoRepository iPhotoRepository;
    private final Path fileStoragePath;

    public AccidentService(@Value("${spring.servlet.multipart.location:temp}") String fileStorageLocation, IAccidentRepository iAccidentRepository,
    IUserRepository iUserRepository, IPhotoRepository iPhotoRepository) throws Exception {

        this.iAccidentRepository = iAccidentRepository;
        this.iUserRepository = iUserRepository;
        this.iPhotoRepository = iPhotoRepository;

        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        Files.createDirectories(fileStoragePath);
    }

    @Override
    @Transactional
    public void saveAccident(Accident accident, String userName) {

        User user = iUserRepository.findUserByUsername(userName);
        accident.setUser(user);
        iAccidentRepository.save(accident);
    }

    @Override
    @Transactional
    public String storeFile(MultipartFile file, Long accidentId) {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Path filePath = Paths.get(fileStoragePath + "/" + fileName);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/download/")
                    .path(fileName)
                    .toUriString();
            Accident accident = iAccidentRepository.findById(accidentId).orElse(Accident.builder().build());

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            iPhotoRepository.save(Photos.builder()
                    .url(url)
                    .accident(accident)
                    .build());

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
