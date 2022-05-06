package com.pusl2020project.groupproject.controller;

import com.pusl2020project.groupproject.dto.AccidentDTO;
import com.pusl2020project.groupproject.dto.ResponseAccidentDTO;
import com.pusl2020project.groupproject.exception.BadRequestException;
import com.pusl2020project.groupproject.service.impl.AccidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AccidentController {

    private final AccidentService accidentService;

    @GetMapping("/accident")
    ResponseEntity<List<ResponseAccidentDTO>> getAccidentByLoginUser(HttpServletRequest request) {
        try {
            String userName = (String) request.getSession().getAttribute("USER_NAME");
            return ResponseEntity.ok().body(accidentService.getAccidentByLoginUser(userName));
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @PostMapping(value = "/accident", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseAccidentDTO> multiplePhotosUpload(@ModelAttribute("accidentModel") AccidentDTO accidentDTO, HttpServletRequest request) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/accident").toUriString());
        String userName = (String) request.getSession().getAttribute("USER_NAME");
        ResponseAccidentDTO responseAccidentDTO = accidentService.saveAccident(accidentDTO, userName);
        return ResponseEntity.created(uri)
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(responseAccidentDTO);
    }
}
