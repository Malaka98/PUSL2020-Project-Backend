package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccidentRepository extends JpaRepository<Accident, Long> {
}
