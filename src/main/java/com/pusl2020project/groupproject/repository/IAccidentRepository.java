package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.entity.enumTypes.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccidentRepository extends JpaRepository<Accident, Long> {
    List<Accident> findAllByUser(User user);

    int countAllByVehicleType(String vehicleType);

    int countAccidentByStatusEquals(Status status);
}
