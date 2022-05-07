package com.pusl2020project.groupproject.service;

import com.pusl2020project.groupproject.entity.SideNavItem;

import java.util.Collection;

public interface ISettingsService {
    Collection<SideNavItem> getSideNavItemByUser(String userName);
}
