package com.example.JobPortal.Service;

import com.example.JobPortal.Entity.Users;
import com.example.JobPortal.Repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public Users addUser(Users users){
        return usersRepository.save(users);
    }

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users getUserById(Long id){
        return usersRepository.findById(id).orElseThrow(() -> new RuntimeException("No ID is found!"));
    }

    public Users updateUser(Long id, Users users){
       Users existingUser = usersRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("User ID is not available"));

       existingUser.setName(users.getName());
       existingUser.setEmail(users.getEmail());
       existingUser.setPassword(users.getPassword());
       existingUser.setRole(users.getRole());

       return usersRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        if(!usersRepository.existsById(id)){
            throw new RuntimeException("User ID is not available");
        }
        usersRepository.deleteById(id);
    }
}
