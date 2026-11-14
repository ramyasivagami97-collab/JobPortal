package com.example.JobPortal.Service;

import com.example.JobPortal.DTO.UserRequestDTO;
import com.example.JobPortal.DTO.UserResponseDTO;
import com.example.JobPortal.Entity.Users;
import com.example.JobPortal.Exception.userNotFoundExceptions;
import com.example.JobPortal.Repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


  public UserResponseDTO addUser(UserRequestDTO request){
        Users user = new Users();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(request.role());

        Users savedUser = usersRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    public List<UserResponseDTO> getAllUsers(){
        List<Users> usersList = usersRepository.findAll();
        List<UserResponseDTO> responseList = new ArrayList<>();

        for(Users users: usersList){
            UserResponseDTO response = new UserResponseDTO(
                    users.getId(),
                    users.getName(),
                    users.getEmail(),
                    users.getRole()
            );

            responseList.add(response);
        }
        return responseList;
    }

    public UserResponseDTO getUserById(Long id){
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new userNotFoundExceptions("User Not Found!"));

        return new UserResponseDTO(
                users.getId(),
                users.getName(),
                users.getEmail(),
                users.getRole()
        );
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO request){
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new userNotFoundExceptions("User Not Found!"));

       existingUser.setName(request.name());
       existingUser.setEmail(request.email());
       existingUser.setPassword(request.password());
       existingUser.setRole(request.role());

       Users updatedUser = usersRepository.save(existingUser);

       return new UserResponseDTO(
               updatedUser.getId(),
               updatedUser.getName(),
               updatedUser.getEmail(),
               updatedUser.getRole()
       );

    }

    public Page<UserResponseDTO> getUserPaginated(int page, int size){
        Sort sort = Sort.by("name").ascending()
                .and(Sort.by("role").descending());
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Users> usersPage = usersRepository.findAll(pageable);

        return usersPage.map(
                users -> new UserResponseDTO(
                        users.getId(),
                        users.getName(),
                        users.getEmail(),
                        users.getRole()
                )
        );

    }

    public void deleteUser(Long id){
        if(!usersRepository.existsById(id)){
            throw new userNotFoundExceptions("User Not Found!");
        }
        usersRepository.deleteById(id);
    }
}
