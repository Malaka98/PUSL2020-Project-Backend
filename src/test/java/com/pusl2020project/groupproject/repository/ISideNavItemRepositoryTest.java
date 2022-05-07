package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.Role;
import com.pusl2020project.groupproject.entity.SideNavItem;
import com.pusl2020project.groupproject.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest
@Slf4j
class ISideNavItemRepositoryTest {

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private ISideNavItemRepository iSideNavItemRepository;


    @Test
    void addSideNavItem() {
        Collection<Role> roles = new ArrayList<>();
//        Role role = roleRepository.findByName("USER");
        Role role2 = roleRepository.findByName("POLICE_USER");

//        roles.add(role);
        roles.add(role2);

//        SideNavItem sideNavItem = iSideNavItemRepository.save(SideNavItem.builder()
//                .name("Report Accident")
//                .label("Report Accident")
//                .path("/app/accidentreport")
//                .roles(roles)
//                .build());

//        SideNavItem sideNavItem = iSideNavItemRepository.save(SideNavItem.builder()
//                .name("Pending Applications")
//                .label("Pending Applications")
//                .path("/app/application/pending")
//                .roles(roles)
//                .build());

        SideNavItem sideNavItem = iSideNavItemRepository.save(SideNavItem.builder()
                .name("Approved Applications")
                .label("Approved Applications")
                .path("/app/application/approved")
                .roles(roles)
                .build());

        log.info("=============>>>>>>>>>>> " + sideNavItem);
    }

    @Test
    void getSideNavItemByUser() {
        User user = iUserRepository.findUserByUsername("root");
        Collection<SideNavItem> sideNavItems = iSideNavItemRepository.findAllByRolesIn(user.getRole());

        log.info("*********************" + sideNavItems);
    }
}