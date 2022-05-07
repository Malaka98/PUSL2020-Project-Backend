package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.entity.SideNavItem;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.repository.ISideNavItemRepository;
import com.pusl2020project.groupproject.repository.IUserRepository;
import com.pusl2020project.groupproject.service.ISettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SettingsService implements ISettingsService {

    private final ISideNavItemRepository iSideNavItemRepository;
    private final IUserRepository iUserRepository;

    @Override
    public Collection<SideNavItem> getSideNavItemByUser(String userName) {

        User user = iUserRepository.findUserByUsername(userName);

        return iSideNavItemRepository.findAllByRolesIn(user.getRole());
    }
}
