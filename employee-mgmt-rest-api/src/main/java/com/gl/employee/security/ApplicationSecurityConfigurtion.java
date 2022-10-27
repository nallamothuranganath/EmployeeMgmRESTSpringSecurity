package com.gl.employee.security;

import com.gl.employee.service.DomainUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfigurtion {

    private final DomainUserDetailsService userDetailsService;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            //httpSecurity.csrf().disable()
                httpSecurity.authorizeRequests()
                    //.anyRequest().authenticated().and().formLogin()
                    .antMatchers(HttpMethod.POST, "/v1/api/employees/")
                    .hasRole("ADMIN")
                    /* not required
                    //.access("hasAuthority('ADMIN')")
                    //.antMatchers(HttpMethod.POST, "/v1/api/employees/user/**")
                    //.authenticated()
                    */
                    .antMatchers(HttpMethod.POST, "/v1/api/employees/role/**", "/v1/api/employees/user/**")
                      //  .hasAnyAuthority()
                        .permitAll()
                    .anyRequest().authenticated().and().formLogin()
                    //.anyRequest().permitAll().and().formLogin()
                    //.authenticated().and().formLogin()
                    .and().httpBasic()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and().cors().and().csrf().disable()
                    ;
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/h2-console/**"
                //,"/v1/api/employees/role/**", "/v1/api/employees/user/**"
        );
    }


    /*@Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }*/



    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       //BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       PasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailsService userDetailsService
                                                       ){
        try {
            return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(bCryptPasswordEncoder)
                    //.passwordEncoder(bCryptPasswordEncoder)
                    .and()
                    .build()
                    ;
        } catch (Exception e) {
            System.out.println("Exception in AuthenticationManager: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
