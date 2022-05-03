package com.pusl2020project.groupproject.controller;

import com.pusl2020project.groupproject.dto.AccidentDTO;
import com.pusl2020project.groupproject.dto.FileDTO;
import com.pusl2020project.groupproject.dto.ResponseAccidentDTO;
import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.exception.BadRequestException;
import com.pusl2020project.groupproject.service.impl.AccidentService;
import com.pusl2020project.groupproject.service.impl.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AccidentController {

    private final AccidentService accidentService;
    private final FileStorageService fileStorageService;

    @PostMapping("/accident")
    public ResponseEntity<?> saveAccident(@Valid @RequestBody AccidentDTO accidentDTO, HttpServletRequest request) {

        try {
            String userName = (String) request.getSession().getAttribute("USER_NAME");
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/accident").toUriString());

            accidentService.saveAccident(Accident.builder()
                    .vehicleNumber(accidentDTO.getVehicleNumber())
                    .location(accidentDTO.getLocation())
                    .vehicleType(accidentDTO.getVehicleType())
                    .description(accidentDTO.getDescription())
                    .build(), userName);

            return ResponseEntity.created(uri).body(accidentDTO);
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @GetMapping("/accident")
    ResponseEntity<List<ResponseAccidentDTO>> getAccidentByLoginUser(HttpServletRequest request) {
        try {
            String userName = (String) request.getSession().getAttribute("USER_NAME");
            return ResponseEntity.ok().body(accidentService.getAccidentByLoginUser(userName));
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @PostMapping("/accident/upload/{id}")
    public ResponseEntity<FileDTO> uploadPhotos(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        try {
            String fileName = accidentService.storeFile(file, id);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/download/")
                    .path(fileName)
                    .toUriString();

            FileDTO response = FileDTO.builder()
                    .fileName(fileName)
                    .contentType(file.getContentType())
                    .url(url)
                    .build();

            return ResponseEntity.ok().body(response);
        }catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }
}
