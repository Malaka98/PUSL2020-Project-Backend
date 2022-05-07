package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.Role;
import com.pusl2020project.groupproject.entity.SideNavItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface ISideNavItemRepository extends JpaRepository<SideNavItem, Long> {
    Collection<SideNavItem> findAllByRolesIn(Collection<Role> roles);
//    SideNavItem findAllById(Long id);

}
