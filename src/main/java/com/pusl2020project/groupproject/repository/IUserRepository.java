package com.pusl2020project.groupproject.repository;


import com.pusl2020project.groupproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String userName);
}
