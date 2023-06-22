package com.task.transaction.controller;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.LoginDto;
import com.task.transaction.dto.UserDto;
import com.task.transaction.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signUp(@Valid @RequestBody UserDto userDto) {
        log.info("Request for signing up: {}", userDto.getUsername());
        return ResponseEntity.ok(userService.signUp(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginDto loginDto) {
        log.info("Request for login: {}", loginDto.getUsername());
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @GetMapping("/test")
    public ResponseEntity<String> testMethod() {
        log.info("Testing security");
        return ResponseEntity.ok("It is ok");
    }
}
