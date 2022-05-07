package com.pusl2020project.groupproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SideNavItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            nullable = false,
            unique = true
    )
    private String label;

    @Column(
            nullable = false,
            unique = true
    )
    private String path;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "side_nav_item_id",
                    referencedColumnName = "id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private Collection<Role> roles = new ArrayList<>();
}
