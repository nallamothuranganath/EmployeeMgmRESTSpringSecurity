package com.gl.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private long id;

    @Column(name="user_id")
    private long userId;

    @Column(name = "role_id")
    private long roleId;

}
