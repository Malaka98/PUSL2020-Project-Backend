package com.pusl2020project.groupproject.controller;

import com.pusl2020project.groupproject.service.impl.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping("/get_side_nav_item")
    public ResponseEntity<?> getSideNavItems(HttpServletRequest request) {

        String userName = (String)request.getSession().getAttribute("USER_NAME");

        return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(settingsService.getSideNavItemByUser(userName));
    }
}
