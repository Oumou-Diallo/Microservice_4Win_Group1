package com.micro.userservice.controller;


import com.micro.userservice.dto.UserRequest;
import com.micro.userservice.dto.UserResponse;
import com.micro.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();

    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) {
        userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }


  /*  @PostMapping("/block/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void blockUser(@PathVariable Long userId) {
        userService.blockUser(userId);
    }

    @PostMapping("/confirm/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmAccount(@PathVariable Long userId) {
        userService.confirmAccount(userId);
    }*/

}
