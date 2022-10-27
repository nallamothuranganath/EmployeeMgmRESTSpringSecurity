package com.gl.employee.repository;

import com.gl.employee.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    public Optional<UserRoleEntity> findByUserIdAndRoleId(long userId, long roleId);
}
