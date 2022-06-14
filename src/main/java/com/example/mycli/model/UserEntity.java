package com.example.mycli.model;

import lombok.*;
import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String login;

    @Column
    private String password;

    @ManyToOne
    private RoleEntity roleEntity;

}
