package com.gl.employee.service;

import com.gl.employee.dto.DomainUserDetails;
import com.gl.employee.entity.UserEntity;
import com.gl.employee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Inside loadUserByUsername..");
        UserEntity userEntity = userRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Invalid User"));

        try {
            System.out.println("userEntity in the loadUserByUsername: " + userEntity);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        return new DomainUserDetails(userEntity);
    }
}
