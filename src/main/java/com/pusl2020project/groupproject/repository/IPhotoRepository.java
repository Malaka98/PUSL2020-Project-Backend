package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.entity.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhotoRepository extends JpaRepository<Photos, Long> {
    Photos findByAccident(Accident accident);
}
