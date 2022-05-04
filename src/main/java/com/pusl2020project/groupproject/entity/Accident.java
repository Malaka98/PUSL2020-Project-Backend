package com.pusl2020project.groupproject.entity;

import com.pusl2020project.groupproject.entity.enumTypes.Status;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Accident {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(
            name = "vehicle_number",
            nullable = false
    )
    private String vehicleNumber;

    @Column(
            name = "vehicle_type",
            nullable = false
    )
    private String vehicleType;

    @Column(
            nullable = false
    )
    private String location;

    @Column(
            nullable = false
    )
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "is_approved",
            nullable = false
    )
    private Status status = Status.Pending;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private User user;
}
