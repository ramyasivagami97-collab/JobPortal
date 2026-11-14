package com.example.JobPortal.Service;


import com.example.JobPortal.Entity.Users;
import com.example.JobPortal.Exception.userNotFoundExceptions;
import com.example.JobPortal.Repository.UsersRepository;
import com.example.JobPortal.Security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByEmail(username)
                .orElseThrow(() -> new userNotFoundExceptions("User Not Found"));



       return new CustomUserDetails(users);

    }
}
