package com.gl.employee.repository;

import com.gl.employee.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    public Optional<RoleEntity> findByIdAndName(long id, String name);

    public Optional<RoleEntity> findByName(String name);

}
