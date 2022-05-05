package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.AccidentDTO;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
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
    public ResponseAccidentDTO saveAccident(AccidentDTO accidentDTO, String userName) {

        try {
//            log.info("****************************** ================= >>>>>>>>>" + accident.getStatus() + " ====== >>>>> " + Status.Pending);
            List<String> urls = new ArrayList<>();
            User user = iUserRepository.findUserByUsername(userName);
            Accident accident = iAccidentRepository.save(Accident.builder()
                    .location(accidentDTO.getLocation())
                    .description(accidentDTO.getDescription())
                    .vehicleType(accidentDTO.getVehicleType())
                    .vehicleNumber(accidentDTO.getVehicleNumber())
                    .status(Status.Pending)
                    .user(user)
                    .build());

            Arrays.stream(accidentDTO.getFiles())
                    .forEach(file -> {

                        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
                        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/download/")
                                .path(fileName)
                                .toUriString();
                        try {
                            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Photos photos = iPhotoRepository.save(Photos.builder()
                                .url(url)
                                .accident(accident)
                                .build());
                        urls.add(url);
                    });
            return ResponseAccidentDTO.builder()
                    .location(accidentDTO.getLocation())
                    .description(accidentDTO.getDescription())
                    .vehicleType(accident.getVehicleType())
                    .vehicleNumber(accident.getVehicleNumber())
                    .approved(accident.getStatus())
                    .url(urls)
                    .user(ResponseUserDTO.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .address(user.getAddress())
                            .role(RoleDtoConverter.roleListToRoleDto(user.getRole()))
                            .build())
                    .build();
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @Override
    public void deleteAccident(Long id) {
        try {
            Accident accident = iAccidentRepository.findById(id).orElse(null);

            if (Objects.nonNull(accident)) {
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
            if (Objects.nonNull(user)) {
                List<ResponseAccidentDTO> responseAccidentDTOS = new ArrayList<>();
                List<Accident> accidentList = iAccidentRepository.findAllByUser(user);
                for (Accident accident : accidentList) {
                    List<Photos> photos = iPhotoRepository.findAllByAccident(accident);
                    List<String> urls = new ArrayList<>();
                    for (Photos photo : photos) {
                        urls.add(photo.getUrl());
                    }
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
