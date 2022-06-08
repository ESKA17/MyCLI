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
public class RoleEntity {
    @Id
    @NonNull
    @NotEmpty
    private String name;
    @NonNull
    @NotEmpty
    private String role;

}
