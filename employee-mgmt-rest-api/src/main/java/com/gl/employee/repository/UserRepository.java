package com.gl.employee.repository;

import com.gl.employee.dto.User;
import com.gl.employee.entity.RoleEntity;
import com.gl.employee.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
public Optional<UserEntity> findByUserName(String userName);

}