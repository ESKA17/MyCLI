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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

}
