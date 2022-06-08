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
    @NonNull
    @NotEmpty
    private String login;
    @NonNull
    @NotEmpty
    private String password;
    @OneToOne
    @NonNull
    @NotEmpty
    private RoleEntity roleEntity;
    @NonNull
    @NotEmpty
    private boolean enabled;

}
