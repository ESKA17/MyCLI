package com.example.mycli.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private int id;

    @Column
    private String login;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "Role_Entity", referencedColumnName = "id")
    private RoleEntity roleEntity;

}
