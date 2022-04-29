package com.pusl2020project.groupproject.controller;

import com.pusl2020project.groupproject.dto.AccidentDTO;
import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.service.impl.AccidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AccidentController {

    private final AccidentService accidentService;

    @PostMapping("/accident")
    public ResponseEntity<?> saveAccident(@Valid @RequestBody AccidentDTO accidentDTO, HttpServletRequest request) {

        String userName = (String) request.getSession().getAttribute("USER_NAME");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/accident").toUriString());

        accidentService.saveAccident(Accident.builder()
                        .vehicleNumber(accidentDTO.getVehicleNumber())
                        .location(accidentDTO.getLocation())
                        .vehicleType(accidentDTO.getVehicleType())
                        .description(accidentDTO.getDescription())
                        .build(), userName);

        return ResponseEntity.created(uri).body(accidentDTO);
    }
}
