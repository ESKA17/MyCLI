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
    private String login;
    private String password;
    @OneToOne
    private RoleEntity roleEntity;
    private boolean enabled;

}
