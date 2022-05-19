package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.Role;
import com.pusl2020project.groupproject.entity.SideNavItem;
import com.pusl2020project.groupproject.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Collection;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ISideNavItemRepositoryTest {

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private ISideNavItemRepository iSideNavItemRepository;


    @Test
    @Rollback(value = false)
    void addSideNavItem() {
        Collection<Role> roles = new ArrayList<>();
//        Role role = roleRepository.findByName("USER");
        Role role2 = roleRepository.findByName("POLICE_USER");

//        roles.add(role);
        roles.add(role2);

        SideNavItem sideNavItem = iSideNavItemRepository.save(SideNavItem.builder()
                .name("Add Report Accident")
                .label("Add Report Accident")
                .path("/app/accidentreport")
                .roles(roles)
                .build());

        SideNavItem sideNavItem2 = iSideNavItemRepository.save(SideNavItem.builder()
                .name("All Accident List")
                .label("All Accident List")
                .path("/app/accident_list")
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