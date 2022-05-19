package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.entity.enumTypes.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IAccidentRepository extends JpaRepository<Accident, Long> {
    List<Accident> findAllByUser(User user);

    int countAllByVehicleType(String vehicleType);

    int countAccidentByStatusEquals(Status status);

    List<Accident> findAll();

    @Modifying
    @Transactional
    @Query(
        "UPDATE Accident a SET a.status = ?2 WHERE a.id = ?1"
    )
    int updateStatus(Long id, Status status);
}
