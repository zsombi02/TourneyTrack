package org.tourneytrack.dal.entity;


import lombok.Getter;
import lombok.Setter;
import org.tourneytrack.impl.data.UserType;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // később majd hash

    @Enumerated(EnumType.STRING)
    private UserType type;
}