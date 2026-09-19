package com.example.JobPortal.Controller;

import com.example.JobPortal.Entity.Users;
import com.example.JobPortal.Service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/users")
    public ResponseEntity<Users> addUser(@RequestBody Users users){
        Users newUser = usersService.addUser(users);
        return ResponseEntity.ok(newUser);

    }
    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users>getUserById(@PathVariable Long id){
        Users getById = usersService.getUserById(id);
        return ResponseEntity.ok(getById);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users users){
        Users updatedUser = usersService.updateUser(id, users);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
       usersService.deleteUser(id);
    }
}
