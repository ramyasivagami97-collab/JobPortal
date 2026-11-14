package com.example.JobPortal.Controller;

import com.example.JobPortal.DTO.UserRequestDTO;
import com.example.JobPortal.DTO.UserResponseDTO;
import com.example.JobPortal.Entity.Users;
import com.example.JobPortal.Service.UsersService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO request){
        UserResponseDTO newUser = usersService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO>getUserById(@PathVariable Long id){
        UserResponseDTO getById = usersService.getUserById(id);
        return ResponseEntity.ok(getById);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,@Valid @RequestBody UserRequestDTO request){
        UserResponseDTO updatedUser = usersService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/users/paginated")
    public ResponseEntity<Page<UserResponseDTO>>getUserPaginated(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam (defaultValue = "10") int size){
        Page<UserResponseDTO> userResponseDTOPage = usersService.getUserPaginated(page, size);
        return ResponseEntity.ok(userResponseDTOPage);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
       usersService.deleteUser(id);
       return ResponseEntity.ok("User has been deleted!");
    }
}
