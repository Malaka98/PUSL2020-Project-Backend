package com.pusl2020project.groupproject.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Photos {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private String url;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "accident_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Accident accident;
}
