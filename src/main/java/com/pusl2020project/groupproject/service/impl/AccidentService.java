package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.ResponseAccidentDTO;
import com.pusl2020project.groupproject.dto.ResponseUserDTO;
import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.entity.Photos;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.entity.enumTypes.Status;
import com.pusl2020project.groupproject.exception.UnknownException;
import com.pusl2020project.groupproject.repository.IAccidentRepository;
import com.pusl2020project.groupproject.repository.IPhotoRepository;
import com.pusl2020project.groupproject.repository.IUserRepository;
import com.pusl2020project.groupproject.service.IAccidentService;
import com.pusl2020project.groupproject.util.DtoConverter.RoleDtoConverter;
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
import java.util.ArrayList;
import java.util.List;
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

        try {
            log.info("****************************** ================= >>>>>>>>>" + accident.getStatus() + " ====== >>>>> " + Status.Pending);
            User user = iUserRepository.findUserByUsername(userName);
            accident.setUser(user);
            accident.setStatus(Status.Pending);
            iAccidentRepository.save(accident);
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage() + " ⚠⚠⚠");
        }
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
            Accident accident = iAccidentRepository.findById(accidentId).orElse(null);

            if(Objects.nonNull(accident)) {
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                iPhotoRepository.save(Photos.builder()
                        .url(url)
                        .accident(accident)
                        .build());

                return fileName;
            } else {
                throw new UnknownException("Accident recode not found in the database ⚠⚠⚠");
            }
        } catch (IOException e) {
            throw new UnknownException(e.getMessage() + " ⚠⚠⚠");
        }
    }

    @Override
    public void deleteAccident(Long id) {
        try {
            Accident accident = iAccidentRepository.findById(id).orElse(null);

            if(Objects.nonNull(accident)) {
                iAccidentRepository.delete(accident);
            } else {
                throw new UnknownException("Recode not found in database / Null Object ⚠⚠⚠");
            }
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @Override
    public List<ResponseAccidentDTO> getAccidentByLoginUser(String userName) {
        try {
            User user = iUserRepository.findUserByUsername(userName);
            if(Objects.nonNull(user)) {
                List<ResponseAccidentDTO> responseAccidentDTOS = new ArrayList<>();
                List<Accident> accidentList = iAccidentRepository.findAllByUser(user);
                for (Accident accident : accidentList) {
                    List<Photos> photos = iPhotoRepository.findAllByAccident(accident);
                    List<String> urls = new ArrayList<>();
                    for(Photos photo : photos) {
                        urls.add(photo.getUrl());
                    }
//                    if(Objects.nonNull(photo)) {
                        responseAccidentDTOS.add(ResponseAccidentDTO.builder()
                                .location(accident.getLocation())
                                .description(accident.getDescription())
                                .vehicleNumber(accident.getVehicleNumber())
                                .vehicleType(accident.getVehicleType())
                                .approved(accident.getStatus())
                                .url(urls)
                                .user(ResponseUserDTO.builder()
                                        .id(accident.getUser().getId())
                                        .name(accident.getUser().getName())
                                        .username(accident.getUser().getUsername())
                                        .address(accident.getUser().getAddress())
                                        .email(accident.getUser().getEmail())
                                        .role(RoleDtoConverter.roleListToRoleDto(accident.getUser().getRole()))
                                        .build())
                                .build());
//                    } else {
//                        throw new UnknownException(userName + " ===> URL not found in the database");
//                    }
                }
                return responseAccidentDTOS;
            } else {
                throw new UnknownException(userName + " ===> user not found in the database");
            }
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage() + " ⚠⚠⚠");
        }

    }
}
