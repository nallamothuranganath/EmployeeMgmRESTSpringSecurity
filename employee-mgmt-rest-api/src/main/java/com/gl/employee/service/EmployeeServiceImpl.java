package com.gl.employee.service;

import com.gl.employee.dto.Employee;
import com.gl.employee.dto.Role;
import com.gl.employee.dto.User;
import com.gl.employee.entity.EmployeeEntity;
import com.gl.employee.entity.RoleEntity;
import com.gl.employee.entity.UserEntity;
import com.gl.employee.exception.DuplicateRoleException;
import com.gl.employee.exception.InvalidEmployeeDataException;
import com.gl.employee.exception.InvalidRoleException;
import com.gl.employee.exception.InvalidRoleInputException;
import com.gl.employee.repository.EmployeeRepository;
import com.gl.employee.repository.RoleRepository;
import com.gl.employee.repository.UserRepository;
import com.gl.employee.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    private final UserRoleRepository userRoleRepository;

    private final ModelMapper modelMapper;

    private final EmployeeRepository employeeRepository;

    @Override
    public Role createRole(Role role) {

        if (role.getName().equals("")) {
            throw new InvalidRoleException("Role should not be null.");
        } else {

            if (roleRepository.findByName(role.getName()).isPresent()) {
                throw new DuplicateRoleException("Role name already exists.");
            } else {
                role.setCreationDate(LocalDate.now());
                role.setEnabledFlag('Y');
                RoleEntity roleEntity = modelMapper.map(role, RoleEntity.class);
                RoleEntity save = roleRepository.save(roleEntity);
                Role roleDto = modelMapper.map(roleEntity, Role.class);
                //roleDto.setRoleId(roleEntity.getRoleId());
                return roleDto;
            }
        }
    }

    @Override
    public User createUser(User user) {

        List<Role> roles = user.getRoles();
        Optional<RoleEntity> roleById = null;
        for (Role role : roles) {
            roleById = roleRepository.findByIdAndName(role.getId(), role.getName());
        }


        if (roleById.isPresent()) {
            Optional<UserEntity> userEntityByUser = userRepository.findByUserName(user.getUserName());
            if (userEntityByUser.isPresent()) {
                //UserEntity userEntity = modelMapper.map(user, UserEntity.class);
                UserEntity userEntityByUserName = userEntityByUser.get();
                if (!userRoleRepository.findByUserIdAndRoleId(userEntityByUserName.getUserId(), roleById.get().getId()).isPresent()
                ) {
                    userEntityByUserName.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                    userEntityByUserName.addRoles(roleById.get());
                    UserEntity updatedUserEntity = userRepository.save(userEntityByUserName);
                    User userDto = modelMapper.map(updatedUserEntity, User.class);
                    //User userDto = modelMapper.map(userEntityByUserName, User.class);
                    return userDto;
                } else {
                    User userDto = modelMapper.map(userEntityByUserName, User.class);
                    return userDto;
                }
            } else {
                user.setCreationDate(LocalDate.now());
                user.setEnabledFlag('Y');
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                UserEntity userEntity = modelMapper.map(user, UserEntity.class);
                UserEntity savedUserEntity = userRepository.save(userEntity);
                User userDto = modelMapper.map(savedUserEntity, User.class);
                return userDto;
            }
        } else {
            throw new InvalidRoleInputException("Invalid Role. No role found for given role id.");
        }
    }

    @Override
    public Employee createEmployee(Employee employee) {

        if (employee.getFirstName().equals("") || employee.getLastName().equals("")) {
            throw new InvalidEmployeeDataException("First Name OR Last Name is missing in the input.");
        } else {
            EmployeeEntity empEntity = modelMapper.map(employee, EmployeeEntity.class);

            EmployeeEntity empEntitySaved = employeeRepository.save(empEntity);

            Employee employeeDto = modelMapper.map(empEntitySaved, Employee.class);

            return employeeDto;
        }
    }
}
