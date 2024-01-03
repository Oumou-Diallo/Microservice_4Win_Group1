package com.micro.userservice.service;


import com.micro.userservice.dto.UserRequest;
import com.micro.userservice.dto.UserResponse;
import com.micro.userservice.model.User;
import com.micro.userservice.repository.UserRepo;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepo userRepo;

    public UserResponse getUserById(String userId) {
        // Appel à votre repository ou service pour récupérer l'utilisateur par ID
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        // Conversion de l'entité User en DTO UserResponse (ou autre objet que vous utilisez)
        return mapToDto(user);
    }

    public void createUser(UserRequest userRequest){
        User user = User.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .userRole(userRequest.getUserRole())
                .build();

        userRepo.save(user);

        log.info("User is save");
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepo.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    public void updateUser(String userId, UserRequest userRequest) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setEmail(userRequest.getEmail());
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setUserRole(userRequest.getUserRole());

        userRepo.save(existingUser);

        log.info("User is updated");
    }

    public void updateUserUI(User updatedUser) {
        User existingUser = userRepo.findById(updatedUser.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Mettez à jour les champs de l'utilisateur existant avec les valeurs de l'utilisateur mis à jour
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setUserRole(updatedUser.getUserRole()); // Supposons que votre classe User a un champ role

        userRepo.save(existingUser);
    }


    public void deleteUser(String userId) {
        userRepo.deleteById(userId);

        log.info("User is deleted");
    }

   /* public void blockUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setBlocked(true);
            userRepository.save(user);
        }
    }

    public void confirmAccount(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setConfirmed(true);
            userRepository.save(user);
        }
    }*/


    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .build();
    }

    private UserResponse mapToDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserRole(user.getUserRole());
        // Autres propriétés à mapper...

        return userResponse;
    }

}
