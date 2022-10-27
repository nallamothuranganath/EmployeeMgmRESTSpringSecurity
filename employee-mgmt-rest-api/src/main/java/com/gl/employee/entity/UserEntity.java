package com.gl.employee.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "user_name")
    private String userName;

    private String password;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "enabled_flag")
    private Character enabledFlag = 'Y';

    @Column(name = "end_date")
    private LocalDate endDate;


    @ManyToMany(
            cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private List<RoleEntity> roles;

    public void addRoles(RoleEntity role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);

    }
}
