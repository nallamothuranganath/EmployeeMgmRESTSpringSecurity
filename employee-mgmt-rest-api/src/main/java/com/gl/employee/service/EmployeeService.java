package com.gl.employee.service;

import com.gl.employee.dto.Employee;
import com.gl.employee.dto.Role;
import com.gl.employee.dto.User;

public interface EmployeeService {

    public Role createRole(Role role);

    public User createUser(User user);

    public Employee createEmployee(Employee employee);
}
